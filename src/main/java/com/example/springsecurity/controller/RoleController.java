package com.example.springsecurity.controller;

import com.example.springsecurity.pojo.Role;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/role/addRole")
    public Response addRole(@RequestBody Role Role) {
        return roleService.addRole(Role);
    }

    @PostMapping("/role/delRole")
    public Response delRole(@RequestBody Role Role) {
        return roleService.delRole(Role);
    }

    @PostMapping("/role/selRoleById")
    public Response selRoleById(@RequestBody Role Role) {
        return roleService.selRoleById(Role);
    }

    @PostMapping("/role/allRole")
    public Response allRole() {
        return roleService.allRole();
    }

    @PostMapping("/role/updRole")
    public Response updRole(@RequestBody Role Role) {
        return roleService.updRole(Role);
    }
}
