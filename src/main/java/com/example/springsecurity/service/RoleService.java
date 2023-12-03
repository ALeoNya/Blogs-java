package com.example.springsecurity.service;

import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.pojo.Role;

public interface RoleService {
    public Response addRole(Role role);
    public Response delRole(Role role);
    public Response allRole(Role role);
    public Response selRoleById(Role role);
    public Response updRole(Role role);

}
