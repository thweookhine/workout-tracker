package com.workout.WorkoutTracker.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WorkoutExerciseReqDto {

    @NotNull(message = "exerciseId is required!")
    private Long exerciseId;

    @Min(value = 1, message = "sets must be at least 1!")
    private int sets;

    @Min(value = 1, message = "repetitions must be at least 1!")
    private int repetitions;

    @DecimalMin(value = "0.0", inclusive = true, message = "Weight cannot be negative")
    private double weight;
}
