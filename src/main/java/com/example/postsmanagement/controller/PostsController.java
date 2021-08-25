package com.example.postsmanagement.controller;

import com.example.postsmanagement.advice.errorModel.ApiError;
import com.example.postsmanagement.controller.responseModel.PaginationResponse;
import com.example.postsmanagement.exception.ConstraintValidationException;
import com.example.postsmanagement.exception.InvalidRequestParameterException;
import com.example.postsmanagement.model.Post;
import com.example.postsmanagement.model.dto.PostDto;
import com.example.postsmanagement.repo.PostsRepository;
import com.example.postsmanagement.service.PostsService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
    private ResponseEntity<PaginationResponse<Post>> GetPosts(
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "5") Integer pageLimit)
    {

        List<String> invalidParams = checkRequestParams(pageNumber, pageLimit);
        if(!invalidParams.isEmpty()) {
            throw new InvalidRequestParameterException(invalidParams);
        }

        Integer currentPageNumber = pageNumber > 0 ? pageNumber-1 : 0;
        Integer currentPageLimit = pageNumber > 0 ? pageLimit : Integer.MAX_VALUE;

        PaginationResponse<Post> paginationResponse = postService.getPage(currentPageNumber, currentPageLimit);
        return new ResponseEntity<>(paginationResponse, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    private Post GetPostById(@PathVariable Integer postId) {
        return postService.getPost(postId);
    }

    @PostMapping("/posts")
    private Post CreatePost(@RequestBody @Valid Post post, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            throw new ConstraintValidationException(errors);
        }
        return postService.addNewPost(post);
    }

    @DeleteMapping("/posts/{postId}")
    private void DeletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
    }

    @PatchMapping("/posts/{postId}")
    private void UpdatePost(@PathVariable Integer postId, @RequestBody PostDto dto) {
        postService.updatePost(postId, dto);
    }
}
