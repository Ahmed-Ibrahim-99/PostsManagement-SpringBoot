package com.example.postsmanagement.service.dataSources;

import com.example.postsmanagement.model.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonNasaNewsSourceImpl implements NewsSource{
    static public String newsItemKey = "item";

    public String title;
    public String link;
    public String description;

    @Override
    public Post mapToPost() {
        Post newPost = new Post();
        newPost.setTitleEn(title);
        newPost.setUrl(link);
        newPost.setBodyEn(description);
        return newPost;
    }
}
