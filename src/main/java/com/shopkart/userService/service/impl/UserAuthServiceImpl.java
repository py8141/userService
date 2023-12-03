package com.shopkart.userService.service.impl;

import com.shopkart.userService.entity.User;
import com.shopkart.userService.repository.UserRepository;
import com.shopkart.userService.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Override
    public String authorizeUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return generateJwtToken(user);
        }
        return null;
    }

    private String generateJwtToken(User user) {

        long expirationTimeMillis = System.currentTimeMillis() + 3600000;

        String tokenPayload = user.getUsername() + ":" + expirationTimeMillis;

        byte[] encodedBytes = Base64.getEncoder().encode(tokenPayload.getBytes());

        return new String(encodedBytes);

    }
}
