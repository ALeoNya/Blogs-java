package com.example.springsecurity.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapToJson {
    public static void mapToJson(Map<String,Object> map, HttpServletResponse response) throws IOException {
        map.put("state", false);
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);  //把报错信息添加到键值对中去
        System.out.println(json);
    }
}
