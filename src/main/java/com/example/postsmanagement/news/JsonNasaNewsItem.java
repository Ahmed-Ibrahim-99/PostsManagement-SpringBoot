package com.example.postsmanagement.news;

import com.example.postsmanagement.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class JsonNasaNewsItem implements NewsItem{
    private String title;
    private String link;
    private String description;

    public JsonNasaNewsItem() {
    }

    public JsonNasaNewsItem(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;
    }

    @Override
    public Post mapItemToPost() {
        Post post = new Post();
        post.setTitleEn(title);
        post.setUrl(link);
        post.setBodyEn(description);
        return post;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "JsonNasaNewsItem{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonNasaNewsItem that = (JsonNasaNewsItem) o;
        return Objects.equals(title, that.title) && Objects.equals(link, that.link) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, link, description);
    }
}
