package com.example.postsmanagement.service;

import com.example.postsmanagement.model.Post;
import com.example.postsmanagement.repo.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface PostsService {
    public Post create(Post post);
    public void delete(Integer postId);
}
