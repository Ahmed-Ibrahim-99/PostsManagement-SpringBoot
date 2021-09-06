package com.example.postsmanagement.service;

import com.example.postsmanagement.model.Post;
import com.example.postsmanagement.service.dataSources.NewsSource;
import com.example.postsmanagement.service.dataSources.SourceFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class XmlParserServiceImpl implements ParserService{
    @Override
    public List<Post> parse(String fileName, String newsItemKey, Class<? extends NewsSource> newsSourceClass, SourceFormat sourceFormat) throws JsonProcessingException, TransformerException {
        Document xmlDocument = getXmlDocument(fileName);
        NodeList newsNodes = getNewsNodes(newsItemKey, xmlDocument);
        return convertNewsNodesToPosts(newsNodes, newsSourceClass);
    }

    @SneakyThrows
    private Document getXmlDocument(String fileName) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new File(fileName));
    }

    private NodeList getNewsNodes(String newsItemKey, Document xmlDocument) {
        return xmlDocument.getElementsByTagName(newsItemKey);
    }

    private List<Post> convertNewsNodesToPosts(NodeList newsNodes, Class<? extends NewsSource> newsSourceClass) throws TransformerException, JsonProcessingException {
        List<Post> parsedNews = new ArrayList<>();
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        for(int i = 0; i < newsNodes.getLength(); i++) {
            Node currentNewsNodeXml = newsNodes.item(i);
            String currentNewsNodeString = convertXmlNodeToString(currentNewsNodeXml);
            NewsSource news = xmlMapper.readValue(currentNewsNodeString, newsSourceClass);
            parsedNews.add(news.mapToPost());
        }
        return parsedNews;
    }

    private String convertXmlNodeToString(Node xmlNode) throws TransformerException {
        StringWriter writer = new StringWriter();

        Transformer trans = TransformerFactory.newInstance().newTransformer();
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        trans.transform(new DOMSource(xmlNode), new StreamResult(writer));

        return writer.toString();
    }

}
