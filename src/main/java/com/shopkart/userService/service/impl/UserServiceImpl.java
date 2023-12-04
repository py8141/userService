package com.shopkart.userService.service.impl;

import com.shopkart.userService.entity.User;
import com.shopkart.userService.repository.UserRepository;
import com.shopkart.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean createUser(User user) {
        if(userRepository.findByUsername(user.getUsername()) != null
        || userRepository.findByUserEmail(user.getUserEmail())!=null){

            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public String retrieveUserEmail(String userId) {
        return userRepository.findById(userId).get().getUserEmail();
    }


}
