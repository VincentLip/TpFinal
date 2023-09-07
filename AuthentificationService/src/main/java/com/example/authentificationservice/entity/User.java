package com.example.authentificationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private boolean driver;

    public User(String username, String encodedPassword,boolean driver) {
        this.username = username;
        this.password = encodedPassword;
        this.driver = driver;
    }

}
