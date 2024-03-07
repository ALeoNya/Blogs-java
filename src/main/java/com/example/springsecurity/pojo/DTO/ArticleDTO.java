package com.example.springsecurity.pojo.DTO;
import lombok.Data;

@Data
public class ArticleDTO {
    private Integer id;

    private Integer userId;

    private Integer categoryId;

    private String articleCover;

    private String articleTitle;

    private String articleAbstract;

    private String articleContent;

    private Integer isTop;

    private Integer isFeatured;

    private Integer isDelete;

    private Integer status;

    private Integer type;

    private String password;

    private String originalUrl;

    private String createTime;

    private String updateTime;
}
