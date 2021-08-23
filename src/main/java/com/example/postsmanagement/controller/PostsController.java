package com.example.postsmanagement.controller;

import com.example.postsmanagement.controller.responseModel.PaginationResponse;
import com.example.postsmanagement.model.Post;
import com.example.postsmanagement.repo.PostsRepository;
import com.example.postsmanagement.service.PostsService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class PostsController {
    private PostsService postService;
    public PostsController(PostsService postService, PostsRepository postsRepository) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    private ResponseEntity<PaginationResponse<Post>> GetAllPosts(
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "5") Integer pageLimit)
    {
        if(pageNumber < 0) {
            throw new IllegalArgumentException("pageNumber cannot be less than 0");
        }
        if(pageLimit < 1) {
            throw new IllegalArgumentException("pageLimit cannot be less than 1");
        }
        Integer currentPageNumber = pageNumber > 0 ? pageNumber-1 : 0;
        Integer currentPageLimit = pageNumber > 0 ? pageLimit : Integer.MAX_VALUE;
        List<Post> posts = postService.readAll(currentPageNumber, currentPageLimit);
        Long entitiesCount = postService.countAll();
        boolean nextPage = entitiesCount/currentPageLimit - 1 > currentPageNumber;
        PaginationResponse<Post> paginationResponse = new PaginationResponse<Post>(entitiesCount,nextPage,posts);
        return new ResponseEntity<PaginationResponse<Post>>(paginationResponse, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    private Post GetPostById(@PathVariable Integer postId) {
        return postService.readById(postId);
    }

    @PostMapping("/posts")
    private Post CreatePost(@RequestBody Post post) {
        return postService.create(post);
    }

    @DeleteMapping("/posts/{postId}")
    private void DeletePost(@PathVariable Integer postId) {
        postService.delete(postId);
    }
}
