package com.workout.WorkoutTracker.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class WorkoutPlanUpdateReqDto {
    private String name;
    private LocalDateTime scheduledDateTime;
    private String description;
    private String comment;
    @Valid
    private List<WorkoutExerciseReqDto> workoutExercises;
}
