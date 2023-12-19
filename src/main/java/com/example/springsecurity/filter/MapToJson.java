package com.example.springsecurity.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MapToJson {
    public static void mapToJson(Map<String,Object> map, HttpServletResponse response) throws IOException {
        map.put("state", false);
        String json = JSON.toJSONString(map);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);  //把报错信息添加到键值对中去
    }
}
