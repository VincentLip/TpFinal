package com.example.authentificationservice.service;


import com.example.authentificationservice.entity.Utilisateur;
import com.example.authentificationservice.repository.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UtilisateurService  {

    private final UtilisateurRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurService(UtilisateurRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Utilisateur enregistrerUtilisateur(String username, String password) {
        Utilisateur user = new Utilisateur(username, password);
        return userRepository.save(user);
    }

    public Optional<Utilisateur> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<Utilisateur> getById(Long id) {
        return userRepository.findById(id);
    }
}
