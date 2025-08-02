package com.workout.WorkoutTracker.util;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PlanStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    MISSED,
    CANCELLED;

    @JsonCreator
    public static PlanStatus fromString(String key) {
        return key == null? null: PlanStatus.valueOf(key.trim().toUpperCase());
    }
}
