package com.workout.WorkoutTracker.mapper;

import com.workout.WorkoutTracker.dto.WorkoutExerciseReqDto;
import com.workout.WorkoutTracker.dto.WorkoutExerciseResDto;
import com.workout.WorkoutTracker.entity.WorkoutExercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ExerciseMapper.class})
public interface WorkoutExerciseMapper {
    @Mapping(source = "exercise", target = "exercise")
    WorkoutExerciseResDto toDto(WorkoutExercise workoutExercise);
}
