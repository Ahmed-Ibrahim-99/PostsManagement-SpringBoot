package com.example.postsmanagement.news;

import com.example.postsmanagement.post.Post;

import java.util.List;


public interface NewsSource {
    public List<Post> mapNewsToPosts();
    public NewsFormat getFormat();
}
