package com.example.postsmanagement.model.utils;

import com.example.postsmanagement.model.Post;
import com.example.postsmanagement.model.dto.PostDto;

public class PostUtils {
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
