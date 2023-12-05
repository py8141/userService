package com.shopkart.userService.controller;

import com.shopkart.userService.dto.TokenResponse;
import com.shopkart.userService.dto.UserAuthDto;
import com.shopkart.userService.service.UserAuthService;
import com.shopkart.userService.service.UserService;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

    @Autowired
    UserAuthService userAuthService;

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserAuthDto userAuthDto) {
        String token = userAuthService.authorizeUser(userAuthDto.getUsername(), userAuthDto.getPassword());
        String userId = userService.getUserIdFromUsername(userAuthDto.getUsername());
//        System.out.println(token);
        if (token != null) {
            TokenResponse tokenResponse = new TokenResponse(token,userId);
            return ResponseEntity.ok(tokenResponse);
        } else {
            String errorMessage = "Invalid username or password";
            return  ResponseEntity.ok(errorMessage);
        }


    }

    @PostMapping("/verify-user")
    public ResponseEntity<Boolean> verifyUser(@RequestBody String token) {
        try {
            boolean isValid = userAuthService.validateJwtToken(token);
            return new ResponseEntity<>(isValid, HttpStatus.OK);
        } catch (SignatureException e) {
            // Handle JWT signature mismatch exception
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace(); // Log the exception for debugging purposes
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
