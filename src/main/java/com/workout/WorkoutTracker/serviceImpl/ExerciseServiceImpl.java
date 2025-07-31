package com.workout.WorkoutTracker.serviceImpl;

import com.workout.WorkoutTracker.Repository.ExerciseRepository;
import com.workout.WorkoutTracker.commons.ErrorMessage;
import com.workout.WorkoutTracker.dto.ExerciseDto;
import com.workout.WorkoutTracker.entity.Exercise;
import com.workout.WorkoutTracker.exceptions.ResourceNotFoundException;
import com.workout.WorkoutTracker.mapper.ExerciseMapper;
import com.workout.WorkoutTracker.service.ExerciseService;
import com.workout.WorkoutTracker.util.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ExerciseMapper exerciseMapper){
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = exerciseMapper;
    }

    @Override
    public Exercise createExercise(ExerciseDto dto) {
        return exerciseRepository.save(exerciseMapper.toEntity(dto));
    }

    @Override
    public Exercise updateExercise(Long id, ExerciseDto dto) {
        // Find exercise by id
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.EXERCISE_NOT_FOUND_MSG));
        // update exercise entity
        exerciseMapper.updateExerciseFromDto(dto, exercise);

        // Save to database
        exerciseRepository.save(exercise);

        // return updated exercise
        return exercise;
    }

    @Override
    public List<ExerciseDto> getAllExercises() {
        return List.of();
    }


}
