package com.workout.WorkoutTracker.controller;

import com.workout.WorkoutTracker.dto.ExerciseDto;
import com.workout.WorkoutTracker.dto.ExerciseIdAndNameDto;
import com.workout.WorkoutTracker.dto.ResponseData;
import com.workout.WorkoutTracker.entity.Exercise;
import com.workout.WorkoutTracker.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@Validated
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createExercise(@Valid @RequestBody ExerciseDto dto) {
        Exercise exercise = exerciseService.createExercise(dto);
        ResponseData<Exercise> apiResponse = new ResponseData<>(200, "Exercise has been created successfully",
                exercise);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateExercise(@PathVariable Long id, @Valid @RequestBody ExerciseDto dto) {
        Exercise exercise = exerciseService.updateExercise(id, dto);
        ResponseData<Exercise> apiResponse = new ResponseData<>(200, "Exercise has been updated successfully",
                exercise);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchExercises(@RequestParam String keyword,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size
                                             ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ExerciseDto> exerciseList = exerciseService.searchExercises(keyword, pageable);
        ResponseData<Page<ExerciseDto>> apiResponse = new ResponseData<>(200, "Exercises have been fetched",
                exerciseList);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        ResponseData<String> apiResponse = new ResponseData<>(200, "Exercise has been deleted successfully!",
                null);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getNames")
    public ResponseEntity<?> getNames() {
        ResponseData<List<ExerciseIdAndNameDto>> apiResponse = new ResponseData<>(200, "Exercises names and ids have been fetched!",
                exerciseService.getIdsAndNames());
        return ResponseEntity.ok(apiResponse);
    }
}
