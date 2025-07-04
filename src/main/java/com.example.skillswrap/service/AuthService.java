package com.example.skillswap.service;

import com.example.skillswap.configuration.JWTUtil;
import com.example.skillswap.dto.AuthResponse;
import com.example.skillswap.dto.LoginRequest;
import com.example.skillswap.dto.RegisterRequest;
import com.example.skillswap.entity.Role;
import com.example.skillswap.entity.User;
import com.example.skillswap.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public AuthResponse register(RegisterRequest registerRequest){
        if(userRepository.existsByUsername(registerRequest.getUsername()) ||
        userRepository.existsByEmail(registerRequest.getEmail())){
            throw new RuntimeException(("User already exists"));
        }

        User user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password((passwordEncoder.encode(registerRequest.getPassword())))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request){
        User user = userRepository.findByUserName(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }
}
