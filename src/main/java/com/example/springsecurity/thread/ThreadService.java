package com.example.springsecurity.thread;

import com.example.springsecurity.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadService {
    @Async("firstThread")
    public void updateArticleViewCount(Article article) {
        try {
            
        } catch(RuntimeException e) {
            e.printStackTrace();
        }
    }
}
