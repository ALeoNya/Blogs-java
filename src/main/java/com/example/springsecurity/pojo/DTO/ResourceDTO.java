package com.example.springsecurity.pojo.DTO;

import com.example.springsecurity.pojo.Resource;
import lombok.Data;

import java.util.List;

@Data
public class ResourceDTO {
    private Resource resource;
    private List<Resource> family;
}
