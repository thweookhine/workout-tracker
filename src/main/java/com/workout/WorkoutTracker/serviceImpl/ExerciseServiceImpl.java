package com.workout.WorkoutTracker.serviceImpl;

import com.workout.WorkoutTracker.Repository.ExerciseRepository;
import com.workout.WorkoutTracker.dto.ExerciseDto;
import com.workout.WorkoutTracker.entity.Exercise;
import com.workout.WorkoutTracker.mapper.ExerciseMapper;
import com.workout.WorkoutTracker.service.ExerciseService;
import org.springframework.stereotype.Service;

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


}
