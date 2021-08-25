package com.example.postsmanagement.advice;

import com.example.postsmanagement.advice.errorModel.ApiError;
import com.example.postsmanagement.exception.EntityAlreadyExistsException;
import com.example.postsmanagement.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalAdvice {
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException (ConstraintViolationException  ex, WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setMessage("Constraint Validation Error");
        apiError.addValidationErrors(ex.getConstraintViolations());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({EntityNotFoundException.class, IllegalArgumentException.class})
    public ResponseEntity<Object> handleInvalidInput(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({EntityAlreadyExistsException.class})
    public ResponseEntity<Object> handleEntityAlreadyExists(EntityAlreadyExistsException ex, WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        apiError.addValidationErrors(ex.getRejectedParams());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
