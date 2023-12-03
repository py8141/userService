package com.shopkart.userService.service.impl;

import com.shopkart.userService.entity.User;
import com.shopkart.userService.repository.UserRepository;
import com.shopkart.userService.service.UserAuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;

@Service
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SecretKey secretKey;


    @Value("${jwt.expirationMillis}") // You need to define this property in your application.properties or application.yml
    private long expirationMillis;

    @Override
    public String authorizeUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return generateJwtToken(username);
        }
        return null;
    }

    public String generateJwtToken(String username) {
        return JwtUtils.generateJwtToken(username, secretKey, expirationMillis);
    }

    @Override
    public boolean validateJwtToken(String token) {
        try {
            return JwtUtils.validateJwtToken(token, secretKey);
        } catch (SignatureException e) {

            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }
    }

//    public Claims getClaimsFromToken(String token) {
//        return JwtUtils.getClaimsFromToken(token, secretKey);
//    }


//    private String generateJwtToken(User user) {
//
//        long expirationTimeMillis = System.currentTimeMillis() + 3600000;
//
//        String tokenPayload = user.getUsername() + ":" + expirationTimeMillis;
//
//        byte[] encodedBytes = Base64.getEncoder().encode(tokenPayload.getBytes());
//
//        return new String(encodedBytes);
//
//    }

}
