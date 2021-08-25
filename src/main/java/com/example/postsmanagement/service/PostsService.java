package com.example.postsmanagement.service;

import com.example.postsmanagement.controller.responseModel.PaginationResponse;
import com.example.postsmanagement.model.Post;
import com.example.postsmanagement.model.dto.PostDto;

public interface PostsService {
    public Post addNewPost(Post post);
    public void deletePost(Integer postId);
    public PaginationResponse<Post> getPage(Integer pageNumber, Integer pageLimit);
    public Post getPost(Integer postId);
    public void updatePost(Integer postId, PostDto dto);
}
