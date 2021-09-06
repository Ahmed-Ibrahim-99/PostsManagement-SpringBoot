package com.example.postsmanagement.service;

import com.example.postsmanagement.model.Post;
import com.example.postsmanagement.service.dataSources.NewsSource;
import com.example.postsmanagement.service.dataSources.SourceFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonParserServiceImpl implements ParserService{

    @Override
    public List<Post> parse(String fileName, String newsItemKey, Class<? extends NewsSource> newsSourceClass, SourceFormat sourceFormat) throws IOException, TransformerException {
        JsonNode newsItemsJson = getFileJsonObject(fileName, newsItemKey);
        return getNewsList(newsItemsJson, newsSourceClass);
    }
    public JsonNode getFileJsonObject(String fileName, String newsItemKey) throws IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        JsonNode jsonNode = jsonMapper.readTree(new File(fileName));
        return jsonNode.get("rss").get("channel").get(newsItemKey);
    }

    public List<Post> getNewsList(JsonNode newsItemsJson, Class<? extends NewsSource> newsSourceClass) {
        ObjectMapper jsonMapper = new ObjectMapper();
        List<Post> newsList = new ArrayList<>();
        newsItemsJson.forEach(newsItem -> {
            try {
                newsList.add(jsonMapper.treeToValue(newsItem, newsSourceClass).mapToPost());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        return newsList;
    }

}
