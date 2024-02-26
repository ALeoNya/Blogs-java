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

    @PostMapping("/resource/addResource")
    public Response addArticle(@RequestBody Resource resource) {
        System.out.println(resource);
        return resourceService.addResource(resource);
    }

    @PostMapping("/resource/delResource")
    public Response delArticle(@RequestBody Resource resource) {
        return resourceService.delResource(resource);
    }

    @PostMapping("/resource/selResourceById")
    public Response selResourceById(@RequestBody Resource resource) {
        return resourceService.selResourceById(resource);
    }

    @PostMapping("/resource/allResource")
    public Response allArticle() {
        return resourceService.allResourceByType();
    }

    @PostMapping("/resource/treeResource")
    public Response treeResource() {
        return resourceService.allResourceByTree();
    }

    @PostMapping("/resource/updResource")
    public Response updArticle(@RequestBody Resource resource) {
        System.out.println(resource);
        return resourceService.updResource(resource);
    }
}
