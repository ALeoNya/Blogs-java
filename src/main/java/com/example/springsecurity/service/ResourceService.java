package com.example.springsecurity.service;

import com.example.springsecurity.pojo.Resource;
import com.example.springsecurity.pojo.Response;

public interface ResourceService {
    public Response addResource(Resource resource);
    public Response delResource(Resource resource);
    public Response selResourceById(Resource resource);
    public Response allResource();
    public Response allResourceByType();
    public Response allResourceByTree();
    public Response updResource(Resource resource);
}
