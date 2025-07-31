package com.workout.WorkoutTracker.service;

import com.workout.WorkoutTracker.dto.ExerciseDto;
import com.workout.WorkoutTracker.entity.Exercise;

import java.util.List;

public interface ExerciseService {
    Exercise createExercise(ExerciseDto dto);
    Exercise updateExercise(Long id, ExerciseDto dto);
    List<ExerciseDto> getAllExercises();
}
