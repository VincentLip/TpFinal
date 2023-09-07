package com.example.authentificationservice.service;


import com.example.authentificationservice.entity.User;
import com.example.authentificationservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User enregistrerUtilisateur(String username, String password,boolean driver) {
        User user = new User(username, password,driver);
        return userRepository.save(user);
    }

    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }
}
