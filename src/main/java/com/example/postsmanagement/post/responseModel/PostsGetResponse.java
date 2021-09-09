package com.example.postsmanagement.post.responseModel;

import com.example.postsmanagement.post.dto.PostDto;

public class PostsGetResponse extends PostsResponse{
    PostDto requiredPost;

    public PostsGetResponse(PostDto requiredPost) {
        this.requiredPost = requiredPost;
    }

    public PostDto getRequiredPost() {
        return requiredPost;
    }
}
