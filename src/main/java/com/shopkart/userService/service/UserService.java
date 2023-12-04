package com.shopkart.userService.service;

import com.shopkart.userService.entity.User;

public interface UserService {

    boolean createUser(User user);
    String retrieveUserEmail(String userId);

}