package com.example.postsmanagement.news;

import com.example.postsmanagement.parser.NewsParserFactoryImpl;
import com.example.postsmanagement.parser.Parser;
import com.example.postsmanagement.post.Post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsService {
    NewsRepository newsRepository = new NewsRepository();
    NewsParserFactoryImpl newsParserFactory = new NewsParserFactoryImpl();

    public List<Post> updateNewsPosts() throws IOException {
        List<Post> newsPosts = new ArrayList<>();
        newsPosts.addAll(getJsonNasaNews());
        newsPosts.addAll(getXmlNasaNews());
        return newsPosts;
    }

    private List<Post> getJsonNasaNews() throws IOException {
        String filename="src/main/java/com/example/postsmanagement/feedSample/news.json";
        String rssContent = newsRepository.getNewsStream(filename);
        Parser JsonNasaParser = newsParserFactory.createParser(new JsonNasaNewsSourceImpl());
        return JsonNasaParser.getRssPosts(rssContent);
    }

    private List<Post> getXmlNasaNews() throws IOException {
        String filename="src/main/java/com/example/postsmanagement/feedSample/news.xml";
        String rssContent = newsRepository.getNewsStream(filename);
        Parser XmlNasaParser = newsParserFactory.createParser(new XmlNasaNewsSourceImpl());
        return XmlNasaParser.getRssPosts(rssContent);
    }
}
