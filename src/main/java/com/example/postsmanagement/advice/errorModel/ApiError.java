package com.example.postsmanagement.advice.errorModel;

import com.example.postsmanagement.advice.utils.LowerCaseClassNameResolver;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
public class ApiError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private List<ApiSubError> subErrors = new ArrayList<>();

    public ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(Throwable ex) {
        this();
        this.message = "Unexpected error";
    }

    public ApiError(String message, Throwable ex) {
        this();
        this.message = message;
    }

    private void addSubError(ApiSubError subError) {
        subErrors.add(subError);
    }

    private void addValidationError(String field, String message) {
        addSubError(new ApiValidationError(field, message));
    }

    private void addValidationError(String message) {
        addSubError(new ApiValidationError(message));
    }

    public void addValidationErrors(List<ObjectError> errors) {
        errors.forEach(error -> {
            FieldError fieldError = (FieldError) error;
            addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        });
    }

    public void addDuplicatedFields(List<String> duplicatedFields) {
        duplicatedFields.forEach(field -> {
            addValidationError(field, field + " shouldn't be duplicated");
        });
    }

    public void addInvalidRequestParams(List<String> invalidParams) {
        invalidParams.forEach(param -> {
            addValidationError(param, param + "'s value is invalid");
        });
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public List<ApiSubError> getSubErrors() {
        return subErrors;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
