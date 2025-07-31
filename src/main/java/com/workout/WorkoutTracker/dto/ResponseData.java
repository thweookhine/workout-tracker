package com.workout.WorkoutTracker.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseData<T> {
    private int status;
    private String message;
    private T data;
}
