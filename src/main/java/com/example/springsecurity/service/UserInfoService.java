package com.example.springsecurity.service;

import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.pojo.Role;
import com.example.springsecurity.pojo.UserInfo;

public interface UserInfoService {
    public Response addUserInfo(UserInfo userInfo);
    public Response delUserInfo(UserInfo userInfo);
    public Response allUserInfo(UserInfo userInfo);
    public Response selUserInfoById(UserInfo userInfo);
    public Response updUserInfo(UserInfo userInfo);

}
