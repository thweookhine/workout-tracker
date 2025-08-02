package com.workout.WorkoutTracker.serviceImpl;

import com.workout.WorkoutTracker.Repository.ExerciseRepository;
import com.workout.WorkoutTracker.commons.ErrorMessage;
import com.workout.WorkoutTracker.dto.ExerciseDto;
import com.workout.WorkoutTracker.dto.ExerciseIdAndNameDto;
import com.workout.WorkoutTracker.entity.Exercise;
import com.workout.WorkoutTracker.exceptions.ResourceNotFoundException;
import com.workout.WorkoutTracker.mapper.ExerciseMapper;
import com.workout.WorkoutTracker.service.ExerciseService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public ExerciseDto createExercise(ExerciseDto dto) {
        Exercise exercise = exerciseRepository.save(exerciseMapper.toEntity(dto));
        return exerciseMapper.toDto(exercise);
    }

    @Override
    public ExerciseDto updateExercise(Long id, ExerciseDto dto) {
        // Find exercise by id
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.EXERCISE_NOT_FOUND_MSG));
        // update exercise entity
        exerciseMapper.updateExerciseFromDto(dto, exercise);

        // Save to database
        exerciseRepository.save(exercise);

        // return updated exercise
        return exerciseMapper.toDto(exercise);
    }

    @Override
    public Page<ExerciseDto> searchExercises(String keyword, Pageable pageable) {
        Page<Exercise> exercisePage = null;
        if (keyword == null || keyword.isBlank()) {
            exercisePage = exerciseRepository.findAll(pageable);
        }else {
            exercisePage = exerciseRepository.searchByKeyword(keyword, pageable);
        }

        List<ExerciseDto> dtoList = exercisePage.stream()
                .map(exerciseMapper::toDto)
                .toList();

        return new PageImpl<>(dtoList, pageable, exercisePage.getTotalElements());
    }

    @Override
    public void deleteExercise(Long id) {
        try {
            exerciseRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Exercise not found with id " + id);
        }
    }

    @Override
    public List<ExerciseIdAndNameDto> getIdsAndNames() {
        return exerciseRepository.getIdsAndNames();
    }

}
