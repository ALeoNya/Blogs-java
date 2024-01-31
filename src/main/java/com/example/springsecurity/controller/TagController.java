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
    @PostMapping("/tag/addTag")
    public Response addTag(@RequestBody Tag tag) {
        return tagService.addTag(tag);
    }

    @PostMapping("/tag/delTag")
    public Response delTag(@RequestBody Tag Tag) {
        return tagService.delTag(Tag);
    }

    @PostMapping("/tag/selTagById")
    public Response selTagById(@RequestBody Tag Tag) {
        return tagService.selTagById(Tag);
    }

    @PostMapping("/tag/allTag")
    public Response allTag() {
        return tagService.allTag();
    }

    @PostMapping("/tag/updTag")
    public Response updTag(@RequestBody Tag Tag) {
        return tagService .updTag(Tag);
    }
}
