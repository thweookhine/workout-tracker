package com.workout.WorkoutTracker.Repository;

import com.workout.WorkoutTracker.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
