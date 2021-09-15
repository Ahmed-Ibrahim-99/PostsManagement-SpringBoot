package com.example.postsmanagement.news;

import com.example.postsmanagement.post.Post;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "rss")
public class XmlNasaNewsSourceImpl implements NewsSource {
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

    @Override
    public NewsFormat getFormat() {
        return NewsFormat.XML;
    }

    static class Channel {
        @JacksonXmlProperty(localName = "item")
        public List<XmlNasaNewsItem> newsItems = new ArrayList<>();
    }
}
