package com.example.authentificationservice.security;



import com.example.authentificationservice.entity.User;
import com.example.authentificationservice.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service

public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository utilisateurRepository;

    public CustomUserDetailService(UserRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userApp = utilisateurRepository.findByUsername(username).get();
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("user"));
        return new org.springframework.security.core.userdetails.User(userApp.getUsername(), userApp.getPassword(),authorities);
    }
}
