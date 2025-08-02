package com.workout.WorkoutTracker.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WorkoutExerciseResDto {

    private Long id;
    private ExerciseDto exercise;
    private int sets;
    private int repetitions;
    private double weight;
}
