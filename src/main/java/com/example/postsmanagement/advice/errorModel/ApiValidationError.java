package com.example.postsmanagement.advice.errorModel;

import java.util.Objects;

public class ApiValidationError extends ApiSubError{
    private String field;
    private Object rejectedValue;
    private String message;

    public ApiValidationError(String field, Object rejectedValue, String message) {
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }

    public ApiValidationError(String message) {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiValidationError that = (ApiValidationError) o;
        return Objects.equals(field, that.field) && Objects.equals(rejectedValue, that.rejectedValue) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, rejectedValue, message);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
