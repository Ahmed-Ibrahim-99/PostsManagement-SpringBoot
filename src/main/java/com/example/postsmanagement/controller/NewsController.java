package com.example.postsmanagement.controller;

import com.example.postsmanagement.model.Post;
import com.example.postsmanagement.service.JsonParserServiceImpl;
import com.example.postsmanagement.service.XmlParserServiceImpl;
import com.example.postsmanagement.service.dataSources.JsonNasaNewsSourceImpl;
import com.example.postsmanagement.service.dataSources.XmlNasaNewsSourceImpl;
import com.example.postsmanagement.service.dataSources.SourceFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

@RestController
public class NewsController {
    private XmlParserServiceImpl parserXml;
    private JsonParserServiceImpl parserJson;

    public NewsController(XmlParserServiceImpl parserXml, JsonParserServiceImpl parserJson) {
        this.parserXml = parserXml;
        this.parserJson = parserJson;
    }

    @GetMapping("/JsonNews")
    private ResponseEntity<List<Post>> GetJsonNews() throws IOException, TransformerException {
        String filename="src/main/java/com/example/postsmanagement/feedSample/news.json";
        return new ResponseEntity<>(parserJson.parse(filename, JsonNasaNewsSourceImpl.newsItemKey, JsonNasaNewsSourceImpl.class, SourceFormat.JSON), HttpStatus.OK);
    }

    @GetMapping("/XmlNews")
    private ResponseEntity<List<Post>> GetXmlNews() throws JsonProcessingException, TransformerException {
                String filename="src/main/java/com/example/postsmanagement/feedSample/news.xml";
        return new ResponseEntity<>(parserXml.parse(filename, XmlNasaNewsSourceImpl.newsItemKey, XmlNasaNewsSourceImpl.class, SourceFormat.XML), HttpStatus.OK);
    }
}
