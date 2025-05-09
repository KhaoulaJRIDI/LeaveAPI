package com.example.leaveapi.service;

import com.example.leaveapi.dto.*;
import com.example.leaveapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponseDTO register(RegisterRequestDTO dto) {

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        user = userService.create(user);
//-ici où on doit definir les paramètres d'authentification -dans ce cas j'ai choisi username
        return new AuthResponseDTO(jwtService.generateToken(user.getUsername()));
    }

    public AuthResponseDTO authenticate(AuthRequestDTO dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(

                        dto.geUsername(),
                        dto.getPassword()));

        final User user = userService.findByUsername(dto.geUsername());
        return new AuthResponseDTO(jwtService.generateToken(user.getUsername()));
    }


}