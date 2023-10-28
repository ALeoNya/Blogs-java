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

    /**
     * 保存角色权限
     *
     * @param roleId 角色id
     * @param menus 权限表
     * @return 是否成功
     */
//    Boolean savePermission(Long roleId,Set<Long> menus);
}
