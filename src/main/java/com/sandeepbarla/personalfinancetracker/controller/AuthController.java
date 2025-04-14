package com.sandeepbarla.personalfinancetracker.controller;

import com.sandeepbarla.personalfinancetracker.dto.request.LoginRequest;
import com.sandeepbarla.personalfinancetracker.dto.response.AuthResponse;
import com.sandeepbarla.personalfinancetracker.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String token = authService.authenticate(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}