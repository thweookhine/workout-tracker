package com.workout.WorkoutTracker.controller;

import com.workout.WorkoutTracker.dto.ResponseData;
import com.workout.WorkoutTracker.dto.UserDto;
import com.workout.WorkoutTracker.security.UserDetailsImpl;
import com.workout.WorkoutTracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/currentUser")
    public ResponseEntity<?> getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseData<UserDto> apiResponse = new ResponseData<>(200, "Successfully Fetch current user",
                userService.getCurrentUser(user.getUsername()));
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/ban/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> banUser(@PathVariable Long id, @RequestBody Map<String, Boolean> body) {
        Boolean banned = body.get("banned");
        userService.banUser(id, banned);
        ResponseData<String> apiResponse = new ResponseData<>(200, "User has been banned successfully!", null);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsers() {
        List<UserDto> userList = userService.getAllUsers();
        ResponseData<List<UserDto>> apiResponse = new ResponseData<>(200, "Users has been fetched!", userList);
        return ResponseEntity.ok(apiResponse);
    }

}
