//package com.example.springsecurity.util.minio;
//
//import com.example.springsecurity.util.response.Code;
//import com.example.springsecurity.util.response.Msg;
//import com.example.springsecurity.pojo.Response;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@CrossOrigin
//@RestController
//public class MinioController {
//    @Autowired
//    private MinioUtil minioUtil;
//
//    @PostMapping("/minio/exists")
//    public void exists() throws Exception {
//        System.out.println(minioUtil.bucketExists("image"));
//    }
//
//    @PostMapping("/minio/uploadFile")
//    public Response upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
//        String name = file.getOriginalFilename();
//        String type = file.getContentType();
//        minioUtil.upload(file, "image");
//        System.out.println("the image name is " + name);
//        Image image = new Image();
//        image.setUrl("http://43.142.32.223:9000/image/"+name);
//        image.setDesc(name);
//        return new Response(Code.UPLOAD_PICTURE_SUCCESS, Msg.UPLOAD_PICTURE_SUCCESS,image);
//    }
//
//    @PostMapping("/minio/deleteImage")
//    public Response delete() {
//        String objectName = "";
//        String bucketName = "";
//        minioUtil.deleteImage(objectName,bucketName);
//        return new Response(Code.DELETE_SUCCESS,Msg.DELETE_SUCCESS_MSG,"删除成功");
//    }
//}
