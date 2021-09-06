package com.example.postsmanagement.service.dataSources;

import com.example.postsmanagement.model.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlTransient;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NasaNewsSourceImpl implements NewsSource {
    static public String newsItemKey = "item";

    @JacksonXmlProperty(localName = "title")
    public String title;
    @JacksonXmlProperty(localName = "link")
    public String link;
    @JacksonXmlProperty(localName = "description")
    public String description;
//    @XmlTransient
//    public String enclosure;
//    @XmlTransient
//    public String guid;
//    @XmlTransient
//    public String pubDate;
//    @XmlTransient
//    public String source;
    @XmlTransient
    @JacksonXmlProperty(namespace = "identifier")
    public String identifier;

    @Override
    public Post mapToPost() {
        Post newPost = new Post();
        newPost.setTitleEn(title);
        newPost.setUrl(link);
        newPost.setBodyEn(description);
        return newPost;
    }
}
