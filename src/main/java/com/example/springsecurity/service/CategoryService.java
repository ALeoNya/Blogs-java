package com.example.springsecurity.service;

import com.example.springsecurity.pojo.Category;
import com.example.springsecurity.pojo.Response;

public interface CategoryService {
    public Response addCategory(Category category);
    public Response delCategory(Category category);
    public Response selCategoryById(Category category);
    public Response allCategory();
    public Response updCategory(Category category);
}
