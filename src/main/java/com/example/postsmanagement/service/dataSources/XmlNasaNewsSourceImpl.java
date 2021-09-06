package com.example.postsmanagement.service.dataSources;

import com.example.postsmanagement.model.Post;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlNasaNewsSourceImpl implements NewsSource {
    static public String newsItemKey = "item";

    @JacksonXmlProperty(localName = "title")
    public String title;
    @JacksonXmlProperty(localName = "link")
    public String link;
    @JacksonXmlProperty(localName = "description")
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
