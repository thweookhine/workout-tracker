package com.workout.WorkoutTracker.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.workout.WorkoutTracker.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({
		ResourceNotFoundException.class,
	})
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = getErrorResponse(404, List.of(ex.getMessage()));
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            DataDuplicationException.class
    })
    public  ResponseEntity<ErrorResponse> handleDataDuplication(DataDuplicationException exception) {
        ErrorResponse error = getErrorResponse(409, List.of(exception.getMessage()));
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({
            BusinessException.class
    })
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException exception) {
        ErrorResponse error = getErrorResponse(400, List.of(exception.getMessage()));
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.add(error.getDefaultMessage())
        );
        ErrorResponse error = getErrorResponse(400, errors);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AuthorizationDeniedException ex) {
        ErrorResponse error = getErrorResponse(403, List.of(ex.getMessage()));
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex) {
        ErrorResponse error = getErrorResponse(500, List.of(ex.getMessage()));
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse getErrorResponse(int status, List<String> messages) {
        ErrorResponse error = new ErrorResponse();
        error.setStatus(status);
        error.setMessageList(messages);
        error.setTimestamp(LocalDateTime.now());

        return error;
    }
	
}
