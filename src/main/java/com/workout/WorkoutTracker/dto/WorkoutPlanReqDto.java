package com.workout.WorkoutTracker.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.workout.WorkoutTracker.util.PlanStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class WorkoutPlanReqDto {

    @NotBlank(message = "name is required!")
    private String name;

    @NotNull(message = "scheduled date time is required!")
    @FutureOrPresent(message = "scheduledDateTime must be now or in the future")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime scheduledDateTime;

    private String description;

    private String comment;

    @Valid
    private List<WorkoutExerciseReqDto> workoutExercises;
}
