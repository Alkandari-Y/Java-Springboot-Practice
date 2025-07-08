package com.project.kfhpractice.config;

import com.project.kfhpractice.utils.errors.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception,
            WebRequest request
    ) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
            .getFieldErrors()
            .forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
            );

        String errorMessage = "Validation failed for one or more fields.";

        ApiErrorResponse response = new ApiErrorResponse(
                "Invalid request",
                errorMessage,
                HttpStatus.BAD_REQUEST.value(),
                request.getDescription(false),
                LocalDateTime.now(),
                new HashMap<>(errors)
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}