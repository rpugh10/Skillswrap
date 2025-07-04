package com.example.skillswap.controller;

import com.example.skillswap.dto.AuthResponse;
import com.example.skillswap.dto.LoginRequest;
import com.example.skillswap.dto.RegisterRequest;
import com.example.skillswap.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/register")
    public AuthResponse register(@RequestBody RegisterRequest request)
    {
        return authService.register(request);
    }

    @PostMapping(path = "login")
    public AuthResponse login(@RequestBody LoginRequest request)
    {
        return authService.login(request);
    }
}
