package com.example.springsecurity.service;

import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.pojo.Role;
import com.example.springsecurity.pojo.UserRole;

public interface UserRoleService {
    public Response addUserRole(UserRole userRole);
    public Response delUserRole(UserRole userRole);
    public Response allUserRole(UserRole userRole);
    public Response selUserRoleById(UserRole userRole);
    public Response updUserRole(UserRole userRole);

}
