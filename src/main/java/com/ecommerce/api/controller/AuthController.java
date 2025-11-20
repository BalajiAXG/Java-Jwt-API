// src/main/java/com/ecommerce/api/controller/AuthController.java
package com.ecommerce.api.controller;

import com.ecommerce.api.dto.*;
import com.ecommerce.api.entity.User;
import com.ecommerce.api.repository.UserRepository;
import com.ecommerce.api.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (userRepo.existsByUsername(req.getUsername())) {
            return ResponseEntity.badRequest().body("Username exists");
        }
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setEmail(req.getEmail());
        userRepo.save(user);
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        return userRepo.findByUsername(req.getUsername())
                .filter(u -> encoder.matches(req.getPassword(), u.getPassword()))
                .map(u -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(u.getUsername()), u.getUsername())))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}