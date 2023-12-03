package com.example.springsecurity.service;

import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.pojo.Tag;

public interface TagService {
    public Response addTag(Tag tag);
    public Response delTag(Tag tag);
    public Response selTagById(Tag tag);
    public Response allTag(Tag tag);
    public Response updTag(Tag tag);
}
