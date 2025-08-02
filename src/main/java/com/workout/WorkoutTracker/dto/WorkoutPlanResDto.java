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
public class WorkoutPlanResDto {

    private Long id;
    private String name;
    private LocalDateTime scheduledDateTime;
    private Long userId;
    private PlanStatus status;
    private String description;
    private String comment;
    private List<WorkoutExerciseResDto> workoutExerciseList;
}
