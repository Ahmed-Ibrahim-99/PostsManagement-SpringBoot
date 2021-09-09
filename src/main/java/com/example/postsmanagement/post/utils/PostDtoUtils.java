package com.example.postsmanagement.post.utils;

import com.example.postsmanagement.post.Post;
import com.example.postsmanagement.post.dto.PostDto;

public class PostDtoUtils {
    public static PostDto mapToDto(Post post) {
        PostDto dto = new PostDto(post.getTitleEn(),
                post.getTitleAr(),
                post.getBodyEn(),
                post.getBodyAr(),
                post.getImageUrl(),
                post.getUrl(),
                post.getInterestId());
        return dto;
    }
}
