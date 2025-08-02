package com.workout.WorkoutTracker.repository;

import com.workout.WorkoutTracker.dto.ExerciseIdAndNameDto;
import com.workout.WorkoutTracker.entity.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query("SELECT e FROM Exercise e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "OR LOWER(e.description) LIKE LOWER(CONCAT('%', :keyword, '%' ))")
    Page<Exercise> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT new com.workout.WorkoutTracker.dto.ExerciseIdAndNameDto(e.id, e.name) from Exercise e")
    List<ExerciseIdAndNameDto> getIdsAndNames();
}
