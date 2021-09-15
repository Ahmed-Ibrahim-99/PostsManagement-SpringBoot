package com.example.postsmanagement.parser;

import com.example.postsmanagement.news.NewsSource;

public class NewsParserFactoryImpl implements ParserFactory{
    NewsParseBehaviorFactoryImpl parseBehaviorFactory = new NewsParseBehaviorFactoryImpl();
    @Override
    public Parser createParser(NewsSource newsSource) {
        ParseBehavior parseBehavior = parseBehaviorFactory.createParseBehavior(newsSource);
        return new Parser(parseBehavior);
    }
}
