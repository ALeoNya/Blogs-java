package com.example.springsecurity.controller;

import com.example.springsecurity.pojo.Tag;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.service.TagService;
import com.example.springsecurity.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TagController {
    @Autowired
    private TagService tagService;
    @PostMapping("/addTag")
    public Response addTag(Tag Tag) {
        return tagService.addTag(Tag);
    }

    @PostMapping("/delTag")
    public Response delTag(@RequestBody Tag Tag) {
        return tagService.delTag(Tag);
    }

    @PostMapping("/selTagById")
    public Response selTagById(@RequestBody Tag Tag) {
        return tagService.selTagById(Tag);
    }

    @PostMapping("/allTag")
    public Response allTag(@RequestBody Tag Tag) {
        return tagService.allTag(Tag);
    }

    @PostMapping("/updTag")
    public Response updTag(@RequestBody Tag Tag) {
        return tagService .updTag(Tag);
    }
}
