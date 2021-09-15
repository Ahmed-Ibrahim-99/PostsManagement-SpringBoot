package com.example.postsmanagement.news;

import com.example.postsmanagement.parser.JsonParseImpl;
import com.example.postsmanagement.parser.Parser;
import com.example.postsmanagement.parser.XmlParseImpl;
import com.example.postsmanagement.post.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class NewsController {
    NewsService newsService = new NewsService();
    @GetMapping("/news")
    private ResponseEntity<List<Post>> GetNasaXml() throws IOException {
        return new ResponseEntity<>(newsService.updateNewsPosts(), HttpStatus.OK);
    }
}
