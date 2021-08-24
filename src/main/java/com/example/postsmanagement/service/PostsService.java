package com.example.postsmanagement.service;

import com.example.postsmanagement.model.Post;
import com.example.postsmanagement.model.dto.PostDto;
import com.example.postsmanagement.repo.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface PostsService {
    public Post create(Post post);
    public void delete(Integer postId);
    public List<Post> readAll(Integer pageNumber, Integer pageLimit);
    public Long countAll();
    public Post readById(Integer postId);
    public void update(Integer postId, PostDto dto);
}
