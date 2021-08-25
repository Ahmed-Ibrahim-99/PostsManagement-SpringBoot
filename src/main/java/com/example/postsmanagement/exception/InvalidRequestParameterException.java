package com.example.postsmanagement.exception;

import java.util.List;

public class InvalidRequestParameterException extends RuntimeException{
    List<String> invalidParams;
    public InvalidRequestParameterException(List<String> invalidParams) {
        super("Invalid Request Parameter");
        this.invalidParams = invalidParams;
    }

    public List<String> getInvalidParams() {
        return invalidParams;
    }
}
