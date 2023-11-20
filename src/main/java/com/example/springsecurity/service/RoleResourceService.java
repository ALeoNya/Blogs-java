package com.example.springsecurity.service;

import com.example.springsecurity.pojo.RoleResource;

import java.util.List;

public interface RoleResourceService {
    /**
     * 获取角色权限
     *
     * @param role_id 角色id
     * @return 角色权限列表
     */
    List<RoleResource> getPermission(int role_id);

    RoleResource getRoleResource(RoleResource roleResource);
}
