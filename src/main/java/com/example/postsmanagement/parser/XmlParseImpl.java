package com.example.postsmanagement.parser;

import com.example.postsmanagement.news.NewsSource;
import com.example.postsmanagement.post.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.lang.Class;

@Transactional
public class XmlParseImpl <SourceClass extends NewsSource> implements ParseBehavior{

    private Class<SourceClass> sourceClass;
    public XmlParseImpl(Class<SourceClass> sourceClass) {
        this.sourceClass = sourceClass;
    }

    @Override
    public List<Post> parse(String fileName) throws IOException {
        String xmlString = readXmlFile(fileName);
        NewsSource source = mapXmlToSource(xmlString);
        return source.mapNewsToPosts();
    }

    private String readXmlFile(String fileName) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(fileName));
        return new String(bytes);
    }

    private NewsSource mapXmlToSource(String xmlString) throws JsonProcessingException {
        XmlMapper xmlMapper = createXmlMapper();
        NewsSource source = xmlMapper.readValue(xmlString, sourceClass);
        return source;
    }

    private XmlMapper createXmlMapper() {
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return xmlMapper;
    }
}
