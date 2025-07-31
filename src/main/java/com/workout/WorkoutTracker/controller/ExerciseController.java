package com.workout.WorkoutTracker.controller;

import com.workout.WorkoutTracker.dto.ExerciseDto;
import com.workout.WorkoutTracker.dto.ResponseData;
import com.workout.WorkoutTracker.dto.UserDto;
import com.workout.WorkoutTracker.entity.Exercise;
import com.workout.WorkoutTracker.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exercises")
@PreAuthorize("hasRole('ADMIN')")
@Validated
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createExercise(@Valid @RequestBody ExerciseDto dto) {
        Exercise exercise = exerciseService.createExercise(dto);
        ResponseData<Exercise> apiResponse = new ResponseData<>(200, "Exercise has been created successfully",
                exercise);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateExercise(@PathVariable Long id, @Valid @RequestBody ExerciseDto dto) {
        Exercise exercise = exerciseService.updateExercise(id, dto);
        ResponseData<Exercise> apiResponse = new ResponseData<>(200, "Exercise has been updated successfully",
                exercise);
        return ResponseEntity.ok(apiResponse);
    }
}
