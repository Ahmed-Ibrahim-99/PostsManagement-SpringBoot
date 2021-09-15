package com.example.postsmanagement.parser;

import com.example.postsmanagement.exception.InvalidRssFormat;
import com.example.postsmanagement.news.NewsSource;

public class NewsParseBehaviorFactoryImpl implements ParseBehaviorFactory{
    @Override
    public ParseBehavior createParseBehavior(NewsSource newsSource) {
        ParseBehavior parseBehavior;
        switch(newsSource.getFormat()) {
            case JSON:
                parseBehavior = new JsonParseImpl(newsSource.getClass());
                break;
            case XML:
                parseBehavior = new XmlParseImpl(newsSource.getClass());
                break;
            default:
                throw new InvalidRssFormat();
        }
        return parseBehavior;
    }
}
