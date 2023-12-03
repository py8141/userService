package com.shopkart.userService.controller;

import com.shopkart.userService.dto.UserAuthDto;
import com.shopkart.userService.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

    @Autowired
    UserAuthService userAuthService;


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserAuthDto userAuthDto) {
        String token = userAuthService.authorizeUser(userAuthDto.getUsername(), userAuthDto.getPassword());
//        System.out.println(token);

        if (token != null) {
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            String errorMessage = "Invalid username or password";
            return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
        }
    }
}
