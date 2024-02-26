package com.example.springsecurity.controller;

import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.pojo.RoleResource;
import com.example.springsecurity.pojo.RoleResource;
import com.example.springsecurity.service.RoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class RoleResourceController {
    @Autowired
    private RoleResourceService resourceService;
    @PostMapping("/addRoleResource")
    public Response addRoleResource(@RequestBody RoleResource RoleResource) {
        return resourceService.addRoleResource(RoleResource);
    }

    @PostMapping("/delRoleResource")
    public Response delRoleResource(@RequestBody RoleResource RoleResource) {
        return resourceService.delRoleResource(RoleResource);
    }

    @PostMapping("/selRoleResourceById")
    public Response selRoleResourceById(@RequestBody RoleResource RoleResource) {
        return resourceService.selRoleResourceById(RoleResource);
    }

    @PostMapping("/roleResource/selAllResourceIdById")
    public Response selAllResourceIdById(@RequestBody RoleResource RoleResource) {
        return resourceService.selAllResourceIdById(RoleResource);
    }

    @PostMapping("/allRoleResource")
    public Response allRoleResource(@RequestBody RoleResource RoleResource) {
        return resourceService.allRoleResource(RoleResource);
    }

    @PostMapping("/updRoleResource")
    public Response updRoleResource(@RequestBody RoleResource RoleResource) {
        return resourceService.updRoleResource(RoleResource);
    }
}
