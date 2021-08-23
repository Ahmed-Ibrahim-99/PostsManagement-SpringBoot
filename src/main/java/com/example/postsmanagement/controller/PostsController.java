package com.example.postsmanagement.controller;

import com.example.postsmanagement.model.Post;
import com.example.postsmanagement.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostsController {
    private PostsService postService;

    public PostsController(PostsService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    private boolean CreateBook(@RequestBody Post post) {
        postService.create(post);
        return true;
    }
}
