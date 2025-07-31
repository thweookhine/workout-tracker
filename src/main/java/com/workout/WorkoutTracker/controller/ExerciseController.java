package com.workout.WorkoutTracker.controller;

import com.workout.WorkoutTracker.dto.ExerciseDto;
import com.workout.WorkoutTracker.dto.ResponseData;
import com.workout.WorkoutTracker.dto.UserDto;
import com.workout.WorkoutTracker.entity.Exercise;
import com.workout.WorkoutTracker.service.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exercises")
@PreAuthorize("hasRole('ADMIN')")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createExercise(@RequestBody ExerciseDto dto) {
        Exercise exercise = exerciseService.createExercise(dto);
        ResponseData<Exercise> apiResponse = new ResponseData<>(200, "Exercise has been created successfully",
                exercise);
        return ResponseEntity.ok(apiResponse);
    }
}
