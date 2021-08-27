package com.example.postsmanagement.model.responseModel;

import com.example.postsmanagement.model.dto.PostDto;

import java.util.Objects;

public class PostsCreateResponse extends PostsResponse{
    String message;
    Integer postId;
    PostDto createdPost;

    public PostsCreateResponse() {
        this.message = "Created Successfully";
    }

    public PostsCreateResponse(Integer postId, PostDto createdPost) {
        this();
        this.postId = postId;
        this.createdPost = createdPost;
    }

    public String getMessage() {
        return message;
    }

    public Integer getPostId() {
        return postId;
    }

    public PostDto getCreatedPost() {
        return createdPost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostsCreateResponse that = (PostsCreateResponse) o;
        return Objects.equals(message, that.message) && Objects.equals(postId, that.postId) && Objects.equals(createdPost, that.createdPost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, postId, createdPost);
    }
}
