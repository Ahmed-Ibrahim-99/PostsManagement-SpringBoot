package com.example.postsmanagement.advice;

import com.example.postsmanagement.advice.errorModel.ApiError;
import com.example.postsmanagement.advice.utils.ResponseEntityBuilder;
import com.example.postsmanagement.exception.EntityAlreadyExistsException;
import com.example.postsmanagement.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalAdvice extends ResponseEntityBuilder {
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException (ConstraintViolationException  ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Constraint Validation Error");
        apiError.addValidationErrors(ex.getConstraintViolations());
        return ResponseEntityBuilder.build(apiError);
    }
    @ExceptionHandler({EntityAlreadyExistsException.class, EntityNotFoundException.class, IllegalArgumentException.class})
    public ResponseEntity<Object> handleInvalidInput(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return ResponseEntityBuilder.build(apiError);
    }
}
