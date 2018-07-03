package com.newedu.attnms.service;

import com.newedu.attnms.entity.User;

public interface UserService {

    User login(User user);

    boolean isExistBy(String username);
}
