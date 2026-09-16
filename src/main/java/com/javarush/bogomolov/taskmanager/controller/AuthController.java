package com.javarush.bogomolov.taskmanager.controller;

import com.javarush.bogomolov.taskmanager.dto.LoginRequest;
import com.javarush.bogomolov.taskmanager.dto.LoginResponse;
import com.javarush.bogomolov.taskmanager.security.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.login(), request.password())
        );

        String token = jwtService.generateToken((UserDetails) authentication.getPrincipal());
        return new LoginResponse(token);
    }
}


