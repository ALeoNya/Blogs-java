package com.example.springsecurity.controller;

import com.example.springsecurity.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;


}
