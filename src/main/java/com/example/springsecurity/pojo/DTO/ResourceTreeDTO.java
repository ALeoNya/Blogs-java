package com.example.springsecurity.pojo.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.springsecurity.pojo.Resource;
import lombok.Data;

import java.util.List;

@Data
public class ResourceTreeDTO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String resourceName;
    private String url;
    private List<Resource> family;
}