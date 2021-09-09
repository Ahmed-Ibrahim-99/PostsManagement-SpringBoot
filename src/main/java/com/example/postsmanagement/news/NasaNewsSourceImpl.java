package com.example.postsmanagement.news;

import com.example.postsmanagement.post.Post;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "rss")
public class NasaNewsSourceImpl implements NewsSource{
    @JacksonXmlProperty(localName = "channel")
    public Channel channel;
    @Override
    public List<Post> mapNewsToPosts() {
        List<Post> posts = new ArrayList<>();
        channel.newsItems.forEach(newsItem -> {
            posts.add(newsItem.mapItemToPost());
        });
        return posts;
    }
}

class Channel {
    @JacksonXmlProperty(localName = "item")
    public List<NasaNewsItem> newsItems = new ArrayList<>();
}
