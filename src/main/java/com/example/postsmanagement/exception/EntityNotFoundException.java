package com.example.postsmanagement.exception;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException() {
        super("Entity Not Found");
    }
}
