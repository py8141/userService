package com.shopkart.userService.controller;

import com.shopkart.userService.dto.UserDto;
import com.shopkart.userService.entity.User;
import com.shopkart.userService.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            User user = new User();
            BeanUtils.copyProperties(userDto, user);

            boolean created = userService.createUser(user);

            return new ResponseEntity<>(created, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            String errorMessage = "An error occurred during user registration. Please try again later.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
