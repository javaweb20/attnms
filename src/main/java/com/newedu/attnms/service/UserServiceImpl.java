package com.newedu.attnms.service;

import com.newedu.attnms.dao.UserDao;
import com.newedu.attnms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Override
    public User login(User user) {
        return  userDao.login(user);
    }

    @Override
    public boolean isExistBy(String username) {
        int cnt = userDao.isExistBy(username);

        boolean isExist = false;
        if(cnt>=1){//表示用户名存在

            isExist = true;
        }
        return isExist;
    }
}
