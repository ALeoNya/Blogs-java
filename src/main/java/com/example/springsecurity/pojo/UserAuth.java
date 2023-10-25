package com.example.springsecurity.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("K_user_auth")
public class UserAuth implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userInfoId;

    private String username;

    private String password;

    private Integer loginType;

    private String ipAddress;

    private String ipSource;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    private LocalDateTime lastLoginTime;

}
