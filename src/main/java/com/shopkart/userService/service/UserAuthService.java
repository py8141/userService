package com.shopkart.userService.service;

public interface UserAuthService {

    String authorizeUser(String username, String password);
    boolean validateJwtToken(String token);


}
