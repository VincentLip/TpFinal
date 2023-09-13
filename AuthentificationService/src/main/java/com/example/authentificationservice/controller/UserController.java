package com.example.authentificationservice.controller;



import com.example.authentificationservice.dto.LoginRequestDTO;
import com.example.authentificationservice.dto.LoginResponseDTO;
import com.example.authentificationservice.dto.RegisterRequestDTO;
import com.example.authentificationservice.dto.RegisterResponseDTO;
import com.example.authentificationservice.entity.User;
import com.example.authentificationservice.security.JWTGenerator;
import com.example.authentificationservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/auth")
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST} )
public class UserController {
    private final AuthenticationManager authenticationManager;

    private final UserService utilisateurService;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator generator;

    public UserController(AuthenticationManager authenticationManager, UserService utilisateurService, PasswordEncoder passwordEncoder, JWTGenerator generator) {
        this.authenticationManager = authenticationManager;
        this.utilisateurService = utilisateurService;
        this.passwordEncoder = passwordEncoder;
        this.generator = generator;
    }


    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(LoginResponseDTO.builder().token(generator.generateToken(authentication)).build());
        }catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @PostMapping("register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        try {
            User userApp = utilisateurService.enregistrerUtilisateur(registerRequestDTO.getUsername(), passwordEncoder.encode(registerRequestDTO.getPassword()), registerRequestDTO.isDriver());
            return ResponseEntity.ok(RegisterResponseDTO.builder().id(userApp.getId()).message("User created").build());
        }catch (Exception ex) {
            throw new RuntimeException();
        }
    }
}
