package com.workout.WorkoutTracker.dto;

import com.workout.WorkoutTracker.util.Category;
import com.workout.WorkoutTracker.util.MuscleGroup;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExerciseDto {

    @NotBlank(message = "Name is required!")
    @NotNull
    private String name;

    private String description;

    private Category category;

    private MuscleGroup muscleGroup;
}
