package com.workout.WorkoutTracker.service;

import com.workout.WorkoutTracker.dto.WorkoutPlanReqDto;
import com.workout.WorkoutTracker.dto.WorkoutPlanResDto;
import com.workout.WorkoutTracker.entity.User;
import com.workout.WorkoutTracker.util.PlanStatus;

import java.util.Map;

public interface WorkoutPlanService {
    WorkoutPlanResDto createWorkoutPlan(WorkoutPlanReqDto dto, User user);
    Map<String, PlanStatus> changeStatus(Long id, PlanStatus status);
}
