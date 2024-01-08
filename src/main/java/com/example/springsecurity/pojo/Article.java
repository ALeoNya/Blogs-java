package com.example.springsecurity.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder  //构造器,提供一个构造器方法
@Data  //提供类的get、set、equals、hashCode、canEqual、toString方法
@NoArgsConstructor  //生成无参构造方法
@AllArgsConstructor  //生成全参数的构造器
@TableName("k_article")
public class Article {
    @TableId(value = "id", type = IdType.AUTO)  //主键标识
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

    @TableField(fill = FieldFill.INSERT)  //插入时自动填充日期（
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)  //更新时自动填充日期（
    private LocalDateTime updateTime;
}
