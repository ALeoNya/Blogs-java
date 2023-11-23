package com.example.springsecurity.service.Impt;

import com.example.springsecurity.pojo.RoleResource;
import com.example.springsecurity.service.RedisService;
import com.example.springsecurity.service.RoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RoleResourceService")
public class RoleResourceServiceImpl implements RoleResourceService {
    @Autowired
    private RedisService redisService;

    @Override
    public List<RoleResource> getPermission(int role_id) {
        return null;
    }

    @Override
    public RoleResource getRoleResource(RoleResource roleResource) {

        return null;
    }
}
