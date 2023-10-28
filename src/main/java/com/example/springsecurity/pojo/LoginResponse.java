package com.example.springsecurity.pojo;

import lombok.Data;

@Data
public class LoginResponse {
    private Integer code;
    private String msg;
    private String token;
    private String auth;
    private Object data;

    public LoginResponse(Integer code, String msg , String token, String auth) {
        this.code = code;
        this.msg = msg;
        this.token = token;
        this.auth = auth;
    }
}
