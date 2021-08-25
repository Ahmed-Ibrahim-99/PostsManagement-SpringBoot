package com.example.postsmanagement.exception;

import org.springframework.validation.ObjectError;

import java.util.List;

public class ConstraintValidationException extends RuntimeException{
    private List<ObjectError> errors;

    public ConstraintValidationException(List<ObjectError> errors) {
        super("Constraint Violation");
        this.errors = errors;
    }

    public List<ObjectError> getErrors() {
        return this.errors;
    }
}
