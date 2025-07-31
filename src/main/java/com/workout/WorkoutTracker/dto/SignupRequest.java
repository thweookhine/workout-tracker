package com.workout.WorkoutTracker.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest  {
    
	@NotBlank(message = "Name is required!")
	@NotNull
	private String name;

    @Email(message = "Invalid email format!")
    @NotBlank(message = "Email is required!")
    @NotNull
    private String email;
    
    @NotBlank(message = "Password is required!")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

}
