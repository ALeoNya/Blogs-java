package com.example.springsecurity.util.minio;

import com.example.springsecurity.pojo.Image;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.response.Code;
import com.example.springsecurity.response.Msg;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
public class MinioController {
    @Autowired
    private MinioUtil minioUtil;

    @PostMapping("/minio/exists")
    public void exists() throws Exception {
        System.out.println(minioUtil.bucketExists("image"));
    }

    @ApiOperation("上传图片")
    @PostMapping("/minio/uploadFile")
    public Response upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        String name = file.getOriginalFilename();
        String type = file.getContentType();
        minioUtil.upload(file, "image");
        System.out.println("the image name is " + name);
        Image image = new Image();
        image.setUrl("http://149.88.95.15:9000/image/"+name);
        image.setDesc(name);
        return new Response(Code.SUCCESS, Msg.UPLOAD_PICTURE_SUCCESS,image);
    }

    @ApiOperation("根据名字删除minio图片")
    @PostMapping("/minio/deleteImage")
    public Response delete() {
        String objectName = "";
        String bucketName = "";
        minioUtil.deleteImage(objectName,bucketName);
        return new Response(Code.FAILED,Msg.DELETE_SUCCESS_MSG,"删除成功");
    }

    @ApiOperation("下载图片")
    @PostMapping("/minio/download/{imageNum}")
    public Response downloadFromMinio(@PathVariable("imageNum") String imageNum, HttpServletResponse response) {
        minioUtil.download("image", "1283372.jpg", response);
        return new Response(Code.SUCCESS, "下载成功", "null");
    }
}
