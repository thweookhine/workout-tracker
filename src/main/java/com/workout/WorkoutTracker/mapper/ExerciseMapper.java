package com.workout.WorkoutTracker.mapper;

import com.workout.WorkoutTracker.dto.ExerciseDto;
import com.workout.WorkoutTracker.entity.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {
    Exercise toEntity(ExerciseDto dto);
    ExerciseDto toDto(Exercise exercise);
    void updateExerciseFromDto(ExerciseDto dto, @MappingTarget Exercise entity);

}
