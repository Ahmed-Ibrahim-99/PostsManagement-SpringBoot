package com.example.postsmanagement.controller;

import com.example.postsmanagement.model.Post;
import com.example.postsmanagement.repo.PostsRepository;
import com.example.postsmanagement.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostsController {
    private PostsService postService;
    private PostsRepository postsRepository;
    public PostsController(PostsService postService, PostsRepository postsRepository) {
        this.postService = postService;
        this.postsRepository = postsRepository;
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
