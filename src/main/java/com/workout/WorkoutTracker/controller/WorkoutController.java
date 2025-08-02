package com.workout.WorkoutTracker.controller;

import com.workout.WorkoutTracker.repository.UserRepository;
import com.workout.WorkoutTracker.commons.ErrorMessage;
import com.workout.WorkoutTracker.dto.ChangeStatusDto;
import com.workout.WorkoutTracker.dto.ResponseData;
import com.workout.WorkoutTracker.dto.WorkoutPlanReqDto;
import com.workout.WorkoutTracker.dto.WorkoutPlanResDto;
import com.workout.WorkoutTracker.entity.User;
import com.workout.WorkoutTracker.exceptions.ResourceNotFoundException;
import com.workout.WorkoutTracker.service.WorkoutPlanService;
import com.workout.WorkoutTracker.util.PlanStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/workouts")
@Validated
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutPlanService workoutPlanService;
    private final UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createWorkout(@Valid @RequestBody WorkoutPlanReqDto dto,
                                           Authentication authentication) {
        WorkoutPlanResDto res = workoutPlanService.createWorkoutPlan(dto, getCurrentUser(authentication));
        ResponseData<WorkoutPlanResDto> apiResponse = new ResponseData<>(200, "Workout plan has been created successfully",
                res);
        return ResponseEntity.ok(apiResponse);
    }

    @PatchMapping("/changeStatus/{id}")
    @PreAuthorize("hasRole('ADMIN') or @workoutPlanSecurity.isOwner(#id, authentication)")
    public ResponseEntity<?> changeStatus(@PathVariable Long id, @Valid @RequestBody ChangeStatusDto req) {
        ResponseData<Map<String, PlanStatus>> apiResponse = new ResponseData<>(200, "Status has been changed successfully",
                workoutPlanService.changeStatus(id, req.getStatus()));
        return ResponseEntity.ok(apiResponse);
    }

    private User getCurrentUser(Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND_MSG));
        return user;
    }
}
