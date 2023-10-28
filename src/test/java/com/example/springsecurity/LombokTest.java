package com.example.springsecurity;

import com.example.springsecurity.pojo.Article;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LombokTest {
    @Test
    public void LombokBuilder() {
        Article article = Article.builder().id(1).userId(1)
                        .articleTitle("Hello world")
                        .articleContent("First article")
                    .build();
        System.out.println(article);
    }
}
