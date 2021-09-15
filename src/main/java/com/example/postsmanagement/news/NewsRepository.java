package com.example.postsmanagement.news;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NewsRepository {
    public String getNewsStream(String fileName) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(fileName));
        return new String(bytes);
    }
}
