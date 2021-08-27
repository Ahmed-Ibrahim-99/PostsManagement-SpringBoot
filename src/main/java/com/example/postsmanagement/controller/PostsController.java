package com.example.postsmanagement.controller;

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

    private List<String> checkRequestParams(int pageNumber, int pageLimit) {
        List<String> invalidParams = new ArrayList<>();
        if(pageNumber < 0) {
            invalidParams.add("pageNumber");
        }
        if(pageLimit < 1) {
            invalidParams.add("pageLimit");
        }
        return invalidParams;
    }
    @GetMapping("/posts")
    private ResponseEntity<Object> GetPosts(
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "5") Integer pageLimit)
    {

        List<String> invalidParams = checkRequestParams(pageNumber, pageLimit);
        if(!invalidParams.isEmpty()) {
            throw new InvalidRequestParameterException(invalidParams);
        }

        Integer currentPageNumber = pageNumber > 0 ? pageNumber-1 : 0;
        Integer currentPageLimit = pageNumber > 0 ? pageLimit : Integer.MAX_VALUE;

        PostsResponse postsPaginationResponse = postService.getPage(currentPageNumber, currentPageLimit);
        return new ResponseEntity<>(postsPaginationResponse, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    private ResponseEntity<Object> GetPostById(@PathVariable Integer postId) {
        Post requiredPost = postService.getPost(postId);
        PostDto responseDto = PostUtils.mapToDto(requiredPost);
        PostsGetResponse getResponse = new PostsGetResponse(responseDto);
        return new ResponseEntity<>(getResponse, HttpStatus.OK);
    }

    @PostMapping("/posts")
    private ResponseEntity<Object> CreatePost(@RequestBody @Valid Post post, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            throw new ConstraintValidationException(errors);
        }
        Post createdPost = postService.addNewPost(post);
        PostDto responseDto = PostUtils.mapToDto(createdPost);
        PostsResponse createResponse = new PostsCreateResponse(post.getPostId(), responseDto);
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/posts/{postId}")
    private ResponseEntity<Object> DeletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/posts/{postId}")
    private ResponseEntity<Object> UpdatePost(@PathVariable Integer postId, @RequestBody PostDto dto) {
        postService.updatePost(postId, dto);
        PostDto responseDto = PostUtils.mapToDto(postService.getPost(postId));
        PostsResponse updateResponse = new PostsUpdateResponse(responseDto);
        return new ResponseEntity<>(updateResponse, HttpStatus.OK);
    }
}
