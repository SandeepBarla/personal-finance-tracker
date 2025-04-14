package com.sandeepbarla.personalfinancetracker.controller;

import com.sandeepbarla.personalfinancetracker.dto.request.RegisterRequest;
import com.sandeepbarla.personalfinancetracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterRequest request) {
        userService.registerUser(request);
    }
}