package com.example.postsmanagement.parser;

import com.example.postsmanagement.news.NewsSource;

public interface ParserFactory {
    Parser createParser(NewsSource newsSource);
}
