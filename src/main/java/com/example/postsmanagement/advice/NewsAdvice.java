package com.example.postsmanagement.advice;

import com.example.postsmanagement.advice.errorModel.ApiError;
import com.example.postsmanagement.exception.InvalidRssFormat;
import com.example.postsmanagement.news.NewsController;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.xml.transform.TransformerException;

@ControllerAdvice(assignableTypes = {NewsController.class})
public class NewsAdvice {
    @ExceptionHandler({InvalidRssFormat.class})
    public ResponseEntity<Object> handleInvalidRssFormat(InvalidRssFormat ex, WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
