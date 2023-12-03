package com.example.springsecurity.service;

import com.example.springsecurity.pojo.Resource;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.pojo.RoleResource;

import java.util.List;

public interface RoleResourceService {
    public Response addRoleResource(RoleResource roleResource);
    public Response delRoleResource(RoleResource roleResource);
    public Response selRoleResourceById(RoleResource roleResource);
    public Response allRoleResource(RoleResource roleResource);
    public Response updRoleResource(RoleResource roleResource);
}
