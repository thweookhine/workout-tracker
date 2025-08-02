package com.workout.WorkoutTracker.serviceImpl;

import com.workout.WorkoutTracker.repository.ExerciseRepository;
import com.workout.WorkoutTracker.repository.WorkoutPlanRepository;
import com.workout.WorkoutTracker.commons.ErrorMessage;
import com.workout.WorkoutTracker.dto.WorkoutExerciseReqDto;
import com.workout.WorkoutTracker.dto.WorkoutPlanReqDto;
import com.workout.WorkoutTracker.dto.WorkoutPlanResDto;
import com.workout.WorkoutTracker.entity.Exercise;
import com.workout.WorkoutTracker.entity.User;
import com.workout.WorkoutTracker.entity.WorkoutExercise;
import com.workout.WorkoutTracker.entity.WorkoutPlan;
import com.workout.WorkoutTracker.exceptions.BusinessException;
import com.workout.WorkoutTracker.exceptions.ResourceNotFoundException;
import com.workout.WorkoutTracker.mapper.WorkoutPlanMapper;
import com.workout.WorkoutTracker.service.WorkoutPlanService;
import com.workout.WorkoutTracker.util.PlanStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WorkoutPlanServiceImpl implements WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;
    private final WorkoutPlanMapper workoutMapper;
    private final ExerciseRepository exerciseRepository;

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

    @Override
    @Transactional
    public Map<String, PlanStatus> changeStatus(Long id, PlanStatus status) {
        // Get workout plan by ID
        WorkoutPlan workoutPlan = workoutPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.WORKOUT_PLAN_NOT_FOUND_MSG));

        PlanStatus oldStatus =workoutPlan.getStatus();
        if(oldStatus == status) {
            throw new BusinessException("You have already changed to "+ status);
        }

        if((PlanStatus.COMPLETED).equals(oldStatus)) {
            throw new BusinessException("You cannot change already Completed Plans");
        }

        if((PlanStatus.MISSED).equals(status)) {
            throw new BusinessException("You cannot change to MISSED Status!");
        }

        workoutPlan.setStatus(status);
        workoutPlanRepository.save(workoutPlan);

        return Map.of("previous-status", oldStatus, "updated-status", status);
    }

}
