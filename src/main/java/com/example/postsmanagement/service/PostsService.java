package com.example.postsmanagement.service;

import com.example.postsmanagement.model.Post;
import com.example.postsmanagement.repo.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface PostsService {
    public void create(Post post);
}
