package com.example.springsecurity.controller;

import com.example.springsecurity.pojo.Article;
import com.example.springsecurity.pojo.Resource;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @PostMapping("/addResource")
    public Response addArticle(@RequestBody Resource resource) {
        return resourceService.addResource(resource);
    }

    @PostMapping("/delResource")
    public Response delArticle(@RequestBody Resource resource) {
        return resourceService.delResource(resource);
    }

    @PostMapping("/selResourceById")
    public Response selResourceById(@RequestBody Resource resource) {
        return resourceService.selResourceById(resource);
    }

    @PostMapping("/allResource")
    public Response allArticle(@RequestBody Resource resource) {
        return resourceService.allResource(resource);
    }

    @PostMapping("/updResource")
    public Response updArticle(@RequestBody Resource resource) {
        return resourceService.updResource(resource);
    }
}
