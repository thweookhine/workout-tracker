package com.workout.WorkoutTracker.service;

import com.workout.WorkoutTracker.dto.LoginRequest;
import com.workout.WorkoutTracker.dto.SignupRequest;
import com.workout.WorkoutTracker.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    void createUser(SignupRequest request);
    String loginUser(LoginRequest request);
    UserDto getCurrentUser(String email);
    void banUser(Long id, Boolean banned);

    List<UserDto> getAllUsers();
}
