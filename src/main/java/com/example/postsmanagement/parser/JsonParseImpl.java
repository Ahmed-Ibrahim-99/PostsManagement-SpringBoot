package com.example.postsmanagement.parser;

import com.example.postsmanagement.news.NewsSource;
import com.example.postsmanagement.post.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JsonParseImpl implements ParseBehavior{
    private Class<? extends NewsSource> sourceClass;
    public JsonParseImpl(Class<? extends NewsSource> sourceClass) {
        this.sourceClass = sourceClass;
    }

    @Override
    public List<Post> parse(String jsonRssContent) throws IOException {
        NewsSource source = mapJsonToSource(jsonRssContent);
        return source.mapNewsToPosts();
    }

    private NewsSource mapJsonToSource(String jsonRssContent) throws JsonProcessingException {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        NewsSource source = jsonMapper.readValue(jsonRssContent, sourceClass);
        return source;
    }
}
