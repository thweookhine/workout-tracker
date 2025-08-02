package com.workout.WorkoutTracker.service;

import com.workout.WorkoutTracker.dto.ExerciseDto;
import com.workout.WorkoutTracker.dto.ExerciseIdAndNameDto;
import com.workout.WorkoutTracker.entity.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExerciseService {
    ExerciseDto createExercise(ExerciseDto dto);
    ExerciseDto updateExercise(Long id, ExerciseDto dto);
    Page<ExerciseDto> searchExercises(String keyword, Pageable pageable);
    void deleteExercise(Long id);
    List<ExerciseIdAndNameDto> getIdsAndNames();
}
