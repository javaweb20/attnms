package com.newedu.attnms.dao;

import com.newedu.attnms.entity.LoginRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginRecordDao {

    /**
     * 插入登录记录
     * @param loginRecord
     */
    void insert(LoginRecord loginRecord);

    /**
     * 删除用户的登录记录
     * @param userName
     */
    void delete(String userName);

    /**
     * 获取用户最近的一条登录记录
     * @param username
     * @return
     */
    LoginRecord getLatestRecordBy(String username);

    /**
     * 获取用户登录失败次数为2的登录登录时间
     * @param userName
     * @return
     */
    LoginRecord getLogin2TimesBy(String userName);

    /**
     * 根据用户名判断是否有过登录记录
     * @param username
     * @return
     */
    int isExistLoginRecordBy(String username);
}
