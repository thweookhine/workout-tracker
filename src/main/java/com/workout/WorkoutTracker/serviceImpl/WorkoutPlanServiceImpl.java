package com.workout.WorkoutTracker.serviceImpl;

import com.workout.WorkoutTracker.Repository.ExerciseRepository;
import com.workout.WorkoutTracker.Repository.WorkoutPlanRepository;
import com.workout.WorkoutTracker.commons.ErrorMessage;
import com.workout.WorkoutTracker.dto.WorkoutExerciseReqDto;
import com.workout.WorkoutTracker.dto.WorkoutPlanReqDto;
import com.workout.WorkoutTracker.dto.WorkoutPlanResDto;
import com.workout.WorkoutTracker.entity.Exercise;
import com.workout.WorkoutTracker.entity.User;
import com.workout.WorkoutTracker.entity.WorkoutExercise;
import com.workout.WorkoutTracker.entity.WorkoutPlan;
import com.workout.WorkoutTracker.exceptions.ResourceNotFoundException;
import com.workout.WorkoutTracker.mapper.WorkoutPlanMapper;
import com.workout.WorkoutTracker.service.WorkoutPlanService;
import com.workout.WorkoutTracker.util.PlanStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkoutPlanServiceImpl implements WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;
    private final WorkoutPlanMapper workoutMapper;
    private final ExerciseRepository exerciseRepository;

    public WorkoutPlanServiceImpl(WorkoutPlanRepository workoutPlanRepository,
                                  WorkoutPlanMapper workoutMapper,
                                  ExerciseRepository exerciseRepository) {
        this.workoutPlanRepository = workoutPlanRepository;
        this.workoutMapper = workoutMapper;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public WorkoutPlanResDto createWorkoutPlan(WorkoutPlanReqDto dto, User user) {
        WorkoutPlan workoutPlan = workoutMapper.toEntity(dto);

        // Set WorkoutExercise
        List<WorkoutExercise> workoutExerciseList = new ArrayList<>();
        List<WorkoutExerciseReqDto> workoutExerciseDtoList = dto.getWorkoutExercises();
        for(WorkoutExerciseReqDto exerciseReqDto: workoutExerciseDtoList) {
            WorkoutExercise workoutExercise = new WorkoutExercise();
            Exercise exercise = exerciseRepository.findById(exerciseReqDto.getExerciseId())
                    .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.EXERCISE_NOT_FOUND_MSG));

            workoutExercise.setExercise(exercise);
            workoutExercise.setSets(exerciseReqDto.getSets());
            workoutExercise.setRepetitions(exerciseReqDto.getRepetitions());
            workoutExercise.setWeight(exerciseReqDto.getWeight());
            workoutExercise.setWorkoutPlan(workoutPlan);
            workoutExerciseList.add(workoutExercise);
        }

        workoutPlan.setWorkoutExercises(workoutExerciseList);
        workoutPlan.setUser(user);
        workoutPlan.setStatus(PlanStatus.PENDING);
        workoutPlan = workoutPlanRepository.save(workoutPlan);
        return workoutMapper.toDto(workoutPlan);
    }

}
