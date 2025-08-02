package com.workout.WorkoutTracker.service;

import com.workout.WorkoutTracker.dto.WorkoutPlanReqDto;
import com.workout.WorkoutTracker.dto.WorkoutPlanResDto;
import com.workout.WorkoutTracker.entity.User;

public interface WorkoutPlanService {
    WorkoutPlanResDto createWorkoutPlan(WorkoutPlanReqDto dto, User user);
}
