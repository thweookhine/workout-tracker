package com.workout.WorkoutTracker.mapper;

import com.workout.WorkoutTracker.dto.WorkoutPlanReqDto;
import com.workout.WorkoutTracker.dto.WorkoutPlanResDto;
import com.workout.WorkoutTracker.entity.WorkoutPlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {WorkoutExerciseMapper.class})
public interface WorkoutPlanMapper {

    WorkoutPlan toEntity(WorkoutPlanReqDto dto);

    @Mapping(source = "workoutExercises", target = "workoutExerciseList")
    @Mapping(source = "user.id", target = "userId")
    WorkoutPlanResDto toDto(WorkoutPlan entity);
}
