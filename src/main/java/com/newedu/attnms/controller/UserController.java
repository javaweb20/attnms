package com.newedu.attnms.controller;

import com.newedu.attnms.component.MyLocaleResolver;
import com.newedu.attnms.entity.LoginRecord;
import com.newedu.attnms.entity.User;
import com.newedu.attnms.service.LoginRecordService;
import com.newedu.attnms.service.UserService;
import com.newedu.attnms.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Map;

@Slf4j
@Controller()
public class UserController {

    @Autowired
    UserService userService;


    @Autowired
    LoginRecordService loginRecordService;

    /**
     * 接收用户的登录信息，并判断是否是登录用户
     * 5分钟内密码连续3次输入错误就将用户锁定，24小时后自动解锁。
     * @param user
     * @param map
     * @return
     */
    @RequestMapping(value="/user/login",method = {RequestMethod.POST,RequestMethod.GET})
    public String login3(User user,Map<String,Object> map){
        log.info("enter /user/login");

        log.info(user.toString());

        //开始业务逻辑处理
        String resultMsg = "";
        if(user != null){
            /**
             * 1：判断用户名是否存在数据库
             */
            boolean isExist = userService.isExistBy(user.getUsername());
            if(isExist){
                /**
                 * 用户是否有过登录记录
                 */
                boolean isLogined =  loginRecordService.isExistLoginRecordBy(user.getUsername());
                if(isLogined) { //有登录记录

                    /**
                     * 获取最后一次登录记录
                     */

                    LoginRecord latestRecord = loginRecordService.getLatestRecordBy(user.getUsername());

                    log.info("i'm here");//用户被锁定
                    if( latestRecord.getLocked().equals("1")){
                        try {
                            /**
                             *  锁定时间超过一天;则删除登录记录
                             */
                            if(DateUtil.gtOneDayOf(latestRecord.getLogindate())){

                                loginRecordService.delete(user.getUsername());


                                /**
                                 * 使用用户名和密码验证用户登录信息
                                 */

                                //出数据库查询出来的数据
                                User userFromDB = userService.login(user);

                                LoginRecord record = new LoginRecord();

                                //跟数据库的数据再次校验，相等算是注册用户
                                if(userFromDB.getUsername().equals(user.getUsername())&&
                                        userFromDB.getPwd().equals(user.getPwd())){

                                    /**
                                     * 如果是注册用户：
                                     * 1：需要记录日志到tbl_login_records表结构
                                     * 2：将用户的登录状态，记录到session中
                                     * 3：重定向到主页
                                     */

                                    record.setTimes(0);

                                }else{//用户名或者密码错误，给用户显示错误提示信息。

                                    /**
                                     * 1:需要记录日志到tbl_login_records表结构
                                     * 2:将用户的登录状态，记录到request中
                                     * 3:重定向到登录页面
                                     */

                                    record.setTimes(1);

                                    resultMsg = "用户名或者密码错误，请重新输入。";
                                }
                                record.setUsername(user.getUsername());
                                record.setLocked("0");
                                loginRecordService.insert(record);

                            }
                            else{
                                //锁定时间未满1天
                                        resultMsg = "用户处于锁定状态,锁定时间超过24小时自动解锁！";
                                map.put("msg",resultMsg);
                                return "forword:/login";
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        //用户未被锁定
                        //距离上次登录失败超过5分钟
                        try {
                            if(DateUtil.gt5MinusOf(latestRecord.getLogindate())){

                                LoginRecord record = new LoginRecord();
                                record.setUsername(user.getUsername());
                                record.setLocked("0");
                                //判断用户名密码是否正确
                                User userFromDB = userService.login(user);
                                if(userFromDB.getUsername().equals(user.getUsername())
                                        && userFromDB.getPwd().equals(user.getPwd())){

                                    /**
                                     * 登录成功后
                                     * 1：删除登录记录
                                     * 2：插入一笔新登录记录
                                     * 3：将用户的登录状态添加到session中
                                     * 4：重定向到主页
                                     */

                                    //删除记录
                                    loginRecordService.delete(user.getUsername());
                                    record.setTimes(0);
                                    loginRecordService.insert(record);
                                }else{
                                    //用户名密码错误，分超过5分钟和没有超过5分钟

                                    if(DateUtil.gt5MinusOf(latestRecord.getLogindate())){

                                        //距离上次登录失败超过5分钟
                                         //       删除记录
                                        loginRecordService.delete(user.getUsername());
                                        record.setTimes(0);
                                        loginRecordService.insert(record);

                                        //记录错误信息
                                                resultMsg = "用户名或者密码错误，请重新输入。";

                                        //转发到登录页面
                                        map.put("msg",resultMsg);
                                        return "forword:/login";
                                    }else{
                                        //未超过5分钟

                                                //如果登录的次数=2
                                        //上次登录失败时已错误两次
                                        if("2".equals(latestRecord.getTimes())){
                                            LoginRecord lRecord =loginRecordService.getLogin2TimesBy(user.getUsername());
                                            if(DateUtil.gt5MinusOf(lRecord.getLogindate())){
                                                //距第一次登录错误时间大于5分钟
                                                loginRecordService.delete(user.getUsername());
                                                loginRecordService.insert(new LoginRecord(user.getUsername(),"0",1));

                                                //记录错误信息
                                                        resultMsg = "用户名或者密码错误，请重新输入。";
                                                //转发到登录页面
                                                map.put("msg",resultMsg);
                                                return "forword:/login";
                                            }else{
                                                //在5分钟以内
                                                loginRecordService.insert(new LoginRecord(user.getUsername(),"1",3));

                                                //记录错误信息
                                                        resultMsg = "密码连续3次输入错误，用户将被锁定24小时!";
                                                //转发到登录页面
                                                map.put("msg",resultMsg);
                                                return "forword:/login";
                                            }
                                        }else{
                                            //上次登录失败时没超过两次
                                            //登录的次数就 +1
                                            loginRecordService.insert(new LoginRecord(user.getUsername(),"0",latestRecord.getTimes()+1));
                                            //记录错误信息
                                                    resultMsg = "密码连续3次输入错误，用户将被锁定24小时!";
                                            //转发到登录页面
                                            map.put("msg",resultMsg);
                                            return "forword:/login";

                                        }
                                    }
                                }

                                //转发到登录页面

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                }
                else{
                    //用户未有过登录记录
                    User userFromDB = userService.login(user);

                    if(userFromDB.getUsername().equals(user.getUsername())
                            &&userFromDB.getPwd().equals(user.getPwd())){
                        //用户名密码匹配

                        /**
                         * 1：记录登录记录
                         * 2：记录用户登录状态到session中
                         * 3：重定向到主页
                         */
                        loginRecordService.insert(new LoginRecord(user.getUsername(),"0",0));


                    }else{
                        //用户名密码不匹配

                        /**
                         * 1：记录登录记录
                         * 2：记录错误信息到日志
                         * 3：重定向到登录页面
                         */
                        loginRecordService.insert(new LoginRecord(user.getUsername(),"0",1));
                        resultMsg = "用户名或者密码错误！请重试！";
                        map.put("msg",resultMsg);
                        return "forword:/login";
                    }
                }

            }else{//用户名不存在
                    resultMsg = "用户名不存在";

                /**
                 * 1：记录错误信息到日志
                 * 2：重定向到登录页面
                 */

                map.put("msg",resultMsg);
                return "forword:/login";

            }



        }

        return "dashboard";
    }



    @GetMapping("/user/index")
    public String index(Map<String,Object> map){

        map.put("username","peter");

        log.info("/user/index");



        return "login";
    }
}
