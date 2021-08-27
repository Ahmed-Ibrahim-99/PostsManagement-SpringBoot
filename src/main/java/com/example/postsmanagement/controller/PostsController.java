package com.example.postsmanagement.controller;

import com.example.postsmanagement.controller.utils.PostsControllerUtils;
import com.example.postsmanagement.model.responseModel.*;
import com.example.postsmanagement.exception.ConstraintValidationException;
import com.example.postsmanagement.exception.InvalidRequestParameterException;
import com.example.postsmanagement.model.Post;
import com.example.postsmanagement.model.dto.PostDto;
import com.example.postsmanagement.model.utils.PostUtils;
import com.example.postsmanagement.service.PostsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PostsController {
    private PostsService postService;
    public PostsController(PostsService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    private ResponseEntity<Object> GetPage(
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "5") Integer pageLimit)
    {
        List<String> invalidParams = PostsControllerUtils.checkRequestParams(pageNumber, pageLimit);
        if(!invalidParams.isEmpty()) {
            throw new InvalidRequestParameterException(invalidParams);
        }
        PostsResponse postsPaginationResponse = postService.getPage(pageNumber, pageLimit);
        return new ResponseEntity<>(postsPaginationResponse, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    private ResponseEntity<Object> GetPost(@PathVariable Integer postId) {
        Post requiredPost = postService.getPost(postId);
        PostDto requiredPostFormatted = PostUtils.mapToDto(requiredPost);
        PostsGetResponse getResponse = new PostsGetResponse(requiredPostFormatted);
        return new ResponseEntity<>(getResponse, HttpStatus.OK);
    }

    @PostMapping("/posts")
    private ResponseEntity<Object> AddNewPost(@RequestBody @Valid Post newPost, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            throw new ConstraintValidationException(errors);
        }
        Post createdPost = postService.addNewPost(newPost);
        PostDto createdPostFormatted = PostUtils.mapToDto(createdPost);
        PostsResponse createResponse = new PostsCreateResponse(newPost.getPostId(), createdPostFormatted);
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/posts/{postId}")
    private ResponseEntity<Object> DeletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/posts/{postId}")
    private ResponseEntity<Object> UpdatePost(@PathVariable Integer postId, @RequestBody @Valid PostDto newPost, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            throw new ConstraintValidationException(errors);
        }
        postService.updatePost(postId, newPost);
        PostDto updatedPostFormatted = PostUtils.mapToDto(postService.getPost(postId));
        PostsResponse updateResponse = new PostsUpdateResponse(updatedPostFormatted);
        return new ResponseEntity<>(updateResponse, HttpStatus.OK);
    }
}
