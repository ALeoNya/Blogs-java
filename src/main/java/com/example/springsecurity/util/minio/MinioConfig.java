//package com.example.springsecurity.util.minio;
//
//import io.minio.MinioClient;
//import lombok.Data;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//
//@Data
//@Configuration
//public class MinioConfig {
//    @Value("${minio.endpoint}")
//    private String endpoint;
//    @Value("${minio.accessKey}")
//    private String accessKey;
//    @Value("${minio.secretKey}")
//    private String secretKey;
//    @Value("${minio.bucketName}")
//    private String bucketName;
//    @Bean
//    public MinioClient generateMinioClient() {
//        try {
//            //MinIO注入到spring管理
//            return
//                MinioClient.builder()
//                    .endpoint(endpoint)
//                    .credentials(accessKey, secretKey)
//                    .build();
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//}
