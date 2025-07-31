package com.workout.WorkoutTracker.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
@Table(name = "workout_exercises", schema = "workout_schema")
public class WorkoutExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private WorkoutPlan workoutPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    private Exercise exercise;

    private int sets;
    private int repetitions;
    private double weight;
}
