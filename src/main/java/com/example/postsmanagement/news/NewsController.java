package com.example.postsmanagement.news;

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
    private Parser NasaParser;

    public NewsController() {
        XmlParseImpl<NasaNewsSourceImpl> xmlParse = new XmlParseImpl<>(NasaNewsSourceImpl.class);
        NasaParser = new Parser(xmlParse);
    }

    @GetMapping("/nasa")
    private ResponseEntity<List<Post>> GetNasa() throws IOException {
        String filename="src/main/java/com/example/postsmanagement/feedSample/news.xml";
        return new ResponseEntity<>(NasaParser.getRssPosts(filename), HttpStatus.OK);
    }
}
