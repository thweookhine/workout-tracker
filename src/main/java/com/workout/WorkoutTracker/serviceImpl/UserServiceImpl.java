package com.workout.WorkoutTracker.serviceImpl;

import com.workout.WorkoutTracker.repository.UserRepository;
import com.workout.WorkoutTracker.commons.ErrorMessage;
import com.workout.WorkoutTracker.dto.LoginRequest;
import com.workout.WorkoutTracker.dto.SignupRequest;
import com.workout.WorkoutTracker.dto.UserDto;
import com.workout.WorkoutTracker.entity.User;
import com.workout.WorkoutTracker.exceptions.BusinessException;
import com.workout.WorkoutTracker.exceptions.DataDuplicationException;
import com.workout.WorkoutTracker.exceptions.ResourceNotFoundException;
import com.workout.WorkoutTracker.mapper.UserMapper;
import com.workout.WorkoutTracker.service.UserService;
import com.workout.WorkoutTracker.util.JwtUtil;
import com.workout.WorkoutTracker.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    @Override
    public void createUser(SignupRequest request) {
        // Check whether email exists or not
        Optional<User> existingData = userRepo.findByEmail(request.getEmail());
        if(existingData.isPresent()) {
            throw new DataDuplicationException("Your Email is already used!");
        }

        // Create User Entity
        User user = userMapper.toEntity(request);
        user.setRole(Role.USER);
        user.setIsValid(true);
        user.setPassword(encoder.encode(request.getPassword()));

        // Save User
        userRepo.save(user);
    }

    @Override
    public String loginUser(LoginRequest request) {

        // Check user exists or not
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException(ErrorMessage.INVALID_CREDENTIALS_MSG));

        if(!user.getIsValid()) {
            throw new BusinessException(ErrorMessage.INVALID_USER_MSG);
        }

        // Match Password
        if(!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException(ErrorMessage.INVALID_CREDENTIALS_MSG);
        }

        // Generate Token
        return jwtUtil.generateToken(user.getEmail(), user.getId());
    }

    @Override
    public UserDto getCurrentUser(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND_MSG));
        if(!user.getIsValid()) {
            throw new BusinessException(ErrorMessage.INVALID_USER_MSG);
        }
        return userMapper.toUserDto(user);
    }

    @Override
    public void banUser(Long id, Boolean banned) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND_MSG));
        user.setIsValid(!banned);
        userRepo.save(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepo.findAll().stream().map(userMapper::toUserDto).toList();
    }
}
