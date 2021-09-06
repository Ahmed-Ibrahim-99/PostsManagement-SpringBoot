package com.example.postsmanagement.service;

import com.example.postsmanagement.model.Post;
import com.example.postsmanagement.service.dataSources.NewsSource;
import com.example.postsmanagement.service.dataSources.SourceFormat;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.xml.transform.TransformerException;
import java.util.List;

// ************************* three tier - discussion
public interface ParserService {
    public List<Post> parse(String fileName, String newsItemKey, Class<? extends NewsSource> newsSourceClass, SourceFormat sourceFormat) throws JsonProcessingException, TransformerException;
}
