package com.workout.WorkoutTracker.mapper;

import com.workout.WorkoutTracker.dto.SignupRequest;
import com.workout.WorkoutTracker.dto.UserDto;
import com.workout.WorkoutTracker.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(SignupRequest dto);
    UserDto toUserDto(User user);
}
