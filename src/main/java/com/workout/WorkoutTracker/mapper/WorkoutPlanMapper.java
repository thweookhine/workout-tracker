package com.workout.WorkoutTracker.mapper;

import com.workout.WorkoutTracker.dto.WorkoutPlanReqDto;
import com.workout.WorkoutTracker.dto.WorkoutPlanResDto;
import com.workout.WorkoutTracker.dto.WorkoutPlanUpdateReqDto;
import com.workout.WorkoutTracker.entity.WorkoutPlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {WorkoutExerciseMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface WorkoutPlanMapper {

    WorkoutPlan toEntity(WorkoutPlanReqDto dto);

    @Mapping(source = "workoutExercises", target = "workoutExerciseList")
    @Mapping(source = "user.id", target = "userId")
    WorkoutPlanResDto toDto(WorkoutPlan entity);

    void updateFromReq(WorkoutPlanUpdateReqDto dto, @MappingTarget WorkoutPlan existingPlan);
}
