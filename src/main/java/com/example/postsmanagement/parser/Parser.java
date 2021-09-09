package com.example.postsmanagement.parser;

import com.example.postsmanagement.news.NewsSource;
import com.example.postsmanagement.post.Post;

import java.io.IOException;
import java.util.List;

public class Parser {
    public ParseBehavior parseBehavior;

    public Parser(ParseBehavior parseBehavior) {
        this.parseBehavior = parseBehavior;
    }

    public List<Post> getRssPosts(String fileName) throws IOException {
        return parseBehavior.parse(fileName);
    }
}
