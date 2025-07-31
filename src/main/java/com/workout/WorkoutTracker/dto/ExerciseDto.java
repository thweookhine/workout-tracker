package com.workout.WorkoutTracker.dto;

import com.workout.WorkoutTracker.util.Category;
import com.workout.WorkoutTracker.util.MuscleGroup;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseDto {

    @NotBlank(message = "Name is required!")
    private String name;

    private String description;

    private Category category;

    private MuscleGroup muscleGroup;
}
