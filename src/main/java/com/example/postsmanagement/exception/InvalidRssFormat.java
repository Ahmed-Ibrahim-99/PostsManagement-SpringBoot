package com.example.postsmanagement.exception;

public class InvalidRssFormat extends RuntimeException{
    public InvalidRssFormat() {
        super("Invalid RSS Format");
    }
}
