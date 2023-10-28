package com.example.springsecurity.security.service;

import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.pojo.UserAuth;

public interface LoginService {
    Response login(UserAuth user);
}
