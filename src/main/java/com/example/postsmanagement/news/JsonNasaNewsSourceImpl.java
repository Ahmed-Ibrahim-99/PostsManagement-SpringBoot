package com.example.postsmanagement.news;

import com.example.postsmanagement.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;

public class JsonNasaNewsSourceImpl implements NewsSource{
    public Rss rss;
    @Override
    public List<Post> mapNewsToPosts() {
        List<Post> posts = new ArrayList<>();
        rss.channel.newsItems.forEach(newsItem -> {
            posts.add(newsItem.mapItemToPost());
        });
        return posts;
    }

    @Override
    public NewsFormat getFormat() {
        return NewsFormat.JSON;
    }

    static class Rss {
        public Channel channel;
    }
    static class Channel {
        @JsonProperty("item")
        public List<JsonNasaNewsItem> newsItems = new ArrayList<>();
    }
}