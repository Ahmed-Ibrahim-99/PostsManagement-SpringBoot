package com.example.postsmanagement.parser;

import com.example.postsmanagement.news.NewsSource;

public interface ParseBehaviorFactory {
    ParseBehavior createParseBehavior(NewsSource newsSource);
}
