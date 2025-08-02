package com.workout.WorkoutTracker.dto;

import com.workout.WorkoutTracker.util.PlanStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangeStatusDto {
    @NotNull(message = "status is required")
    private PlanStatus status;
}
