package com.example.postsmanagement.parser;

import com.example.postsmanagement.news.NewsSource;
import com.example.postsmanagement.post.Post;

import java.io.IOException;
import java.util.List;

public interface ParseBehavior {
    public List<Post> parse(String fileName) throws IOException;
}
