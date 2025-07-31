package com.workout.WorkoutTracker.dto;

import com.workout.WorkoutTracker.entity.WorkoutPlan;
import com.workout.WorkoutTracker.util.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isValid;
}
