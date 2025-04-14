package com.sandeepbarla.personalfinancetracker.service;

import com.sandeepbarla.personalfinancetracker.dto.request.RegisterRequest;
import com.sandeepbarla.personalfinancetracker.exception.EmailAlreadyInUseException;
import com.sandeepbarla.personalfinancetracker.model.User;
import com.sandeepbarla.personalfinancetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyInUseException("Email already in use");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace(); // log the actual error
            throw new RuntimeException("Failed to save user: " + e.getMessage());
        }
    }
}