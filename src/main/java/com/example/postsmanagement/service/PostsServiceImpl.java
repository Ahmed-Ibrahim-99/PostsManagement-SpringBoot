package com.example.postsmanagement.service;

import com.example.postsmanagement.model.Post;
import com.example.postsmanagement.repo.PostsRepository;
import org.springframework.stereotype.Service;

@Service
public class PostsServiceImpl implements PostsService{
    private PostsRepository postsRepository;

    public PostsServiceImpl(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public void create(Post post)
    {
        postsRepository.save(post);
    }
}
