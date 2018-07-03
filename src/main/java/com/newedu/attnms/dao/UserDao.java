package com.newedu.attnms.dao;

import com.newedu.attnms.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User login(User user);

    int isExistBy(String username);
}
