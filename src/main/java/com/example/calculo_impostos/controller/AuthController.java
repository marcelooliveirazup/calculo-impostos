package com.example.calculo_impostos.controller;

import com.example.calculo_impostos.dto.RegisterRequest;
import com.example.calculo_impostos.dto.LoginRequest;
import com.example.calculo_impostos.dto.AuthResponse;

import com.example.calculo_impostos.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthController {

    // public final AuthService AuthService;
    private AuthController authService;

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.status(201).body(authService.register(request).getBody());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request).getBody());
    }
}

