package com.example.postsmanagement.model.responseModel;

import com.example.postsmanagement.model.dto.PostDto;

public class PostsGetResponse extends PostsResponse{
    PostDto requiredPost;

    public PostsGetResponse(PostDto requiredPost) {
        this.requiredPost = requiredPost;
    }

    public PostDto getRequiredPost() {
        return requiredPost;
    }
}
