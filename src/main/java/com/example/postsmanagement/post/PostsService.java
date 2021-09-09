package com.example.postsmanagement.post;

import com.example.postsmanagement.post.responseModel.PostsPaginationResponse;
import com.example.postsmanagement.post.Post;
import com.example.postsmanagement.post.dto.PostDto;

public interface PostsService {
    public Post addNewPost(Post newPost);
    public void deletePost(Integer postId);
    public PostsPaginationResponse<Post> getPage(Integer pageNumber, Integer pageLimit);
    public Post getPost(Integer postId);
    public void updatePost(Integer postId, PostDto newPost);
}
