package com.example.postsmanagement.advice.errorModel;

import java.util.Objects;

public class ApiValidationError extends ApiSubError{
    private String field;
    private String message;

    public ApiValidationError(String field, String message) {
        this.field = field;
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
        return Objects.equals(field, that.field) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, message);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
