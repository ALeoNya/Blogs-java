package com.example.springsecurity;


import com.example.springsecurity.util.minio.MinioUtil;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

public class MinioTest {
    @Resource
    private MinioUtil minioUtil;

    @Test
    void testDownLoad() {
//        minioUtil.download("image","1283372.jpg", response);
    }
}
