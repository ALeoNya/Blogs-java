package com.example.springsecurity.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("K_talk")
public class Talk {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String content;

    private String images;

    private Integer isTop;

    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

}