package com.workout.WorkoutTracker.security;

import com.workout.WorkoutTracker.repository.UserRepository;
import com.workout.WorkoutTracker.repository.WorkoutPlanRepository;
import com.workout.WorkoutTracker.entity.User;
import com.workout.WorkoutTracker.entity.WorkoutPlan;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component("workoutPlanSecurity")
@RequiredArgsConstructor
public class WorkoutPlanSecurity {

    private final WorkoutPlanRepository workoutPlanRepository;
    private final UserRepository userRepository;

    public boolean isOwner(Long planId, Authentication authentication) {
        Optional<WorkoutPlan> planData = workoutPlanRepository.findById(planId);

        if(planData.isEmpty()) {
            return false;
        }

        Optional<User> userData = userRepository.findByEmail(authentication.getName());
        if(userData.isEmpty()) {
            return false;
        }

        return Objects.equals(planData.get().getUser().getId(), userData.get().getId());
    }
}
