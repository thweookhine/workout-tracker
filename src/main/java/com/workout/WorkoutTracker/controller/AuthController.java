package com.workout.WorkoutTracker.controller;

import com.workout.WorkoutTracker.dto.LoginRequest;
import com.workout.WorkoutTracker.dto.ResponseData;
import com.workout.WorkoutTracker.dto.SignupRequest;
import com.workout.WorkoutTracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest req) {
        userService.createUser(req);
        ResponseData<String> apiResponse = new ResponseData<>(200, "Registered successfully!",null);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        Map<String, String> tokenData = Map.of("token", userService.loginUser(req));
        ResponseData<Map<String, String>> apiResponse = new ResponseData<>(200, "Login Success!", tokenData);
        return ResponseEntity.ok(apiResponse);
    }
}
