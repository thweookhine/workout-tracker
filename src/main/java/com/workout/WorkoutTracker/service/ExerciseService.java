package com.workout.WorkoutTracker.service;

import com.workout.WorkoutTracker.dto.ExerciseDto;
import com.workout.WorkoutTracker.entity.Exercise;

public interface ExerciseService {
    Exercise createExercise(ExerciseDto dto);

}
