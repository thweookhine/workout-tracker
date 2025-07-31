package com.workout.WorkoutTracker.mapper;

import com.workout.WorkoutTracker.dto.ExerciseDto;
import com.workout.WorkoutTracker.entity.Exercise;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {
    Exercise toEntity(ExerciseDto dto);
    ExerciseDto toDto(Exercise exercise);
}
