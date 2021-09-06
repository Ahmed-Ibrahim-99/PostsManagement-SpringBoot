package com.example.postsmanagement.controller;

import com.example.postsmanagement.model.Post;
import com.example.postsmanagement.service.XmlParserServiceImpl;
import com.example.postsmanagement.service.dataSources.NasaNewsSourceImpl;
import com.example.postsmanagement.service.dataSources.SourceFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.TransformerException;
import java.util.Arrays;
import java.util.List;

@RestController
public class NewsController {
    private XmlParserServiceImpl parser;

    public NewsController(XmlParserServiceImpl parser) {
        this.parser = parser;
    }

    @GetMapping("/news")
    private ResponseEntity<List<Post>> GetNews() throws JsonProcessingException, TransformerException {
        String filename="src/main/java/com/example/postsmanagement/feedSample/news.xml";
        return new ResponseEntity<>(parser.parse(filename, NasaNewsSourceImpl.newsItemKey, NasaNewsSourceImpl.class, SourceFormat.JSON), HttpStatus.OK);
    }
}
