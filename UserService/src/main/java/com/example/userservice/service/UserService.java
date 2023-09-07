package com.example.userservice.service;

import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public User createUser(String username,boolean driver) {
//        User user = User.builder().username(username).driver(false).build();
//        userRepository.save(user);
//        return user;
//    }

    public User getUserById(int id) {
        Optional<User> userAppOptional = userRepository.findById(id);
        if(userAppOptional.isPresent()) {
            return userAppOptional.get();
        }
        throw new RuntimeException("Not found");
    }
}
