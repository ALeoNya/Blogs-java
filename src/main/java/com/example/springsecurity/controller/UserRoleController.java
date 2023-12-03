package com.example.springsecurity.controller;

import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.pojo.UserRole;
import com.example.springsecurity.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserRoleController {
    @Autowired
    private UserRoleService UserRoleService;

    @PostMapping("/addUserRole")
    public Response addUserRole(@RequestBody UserRole UserRole) {
        return UserRoleService.addUserRole(UserRole);
    }

    @PostMapping("/delUserR+ole")
    public Response delUserRole(@RequestBody UserRole UserRole) {
        return UserRoleService.delUserRole(UserRole);
    }

    @PostMapping("/selUserRoleById")
    public Response selUserRoleById(@RequestBody UserRole UserRole) {
        return UserRoleService.selUserRoleById(UserRole);
    }

    @PostMapping("/allUserRole")
    public Response allUserRole(@RequestBody UserRole UserRole) {
        return UserRoleService.allUserRole(UserRole);
    }

    @PostMapping("/updUserRole")
    public Response updUserRole(@RequestBody UserRole UserRole) {
        return UserRoleService.updUserRole(UserRole);
    }
}
