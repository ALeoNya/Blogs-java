package com.example.springsecurity.controller;

import com.example.springsecurity.pojo.Category;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    public Response addCategory(@RequestBody Category Category) {
        return categoryService.addCategory(Category);
    }

    @PostMapping("/delCategory")
    public Response delCategory(@RequestBody Category Category) {
        return categoryService.delCategory(Category);
    }

    @PostMapping("/selCategoryById")
    public Response selCategoryById(@RequestBody Category Category) {
        return categoryService.selCategoryById(Category);
    }

    @PostMapping("/allCategory")
    public Response allCategory(@RequestBody Category Category) {
        return categoryService.allCategory(Category);
    }

    @PostMapping("/updCategory")
    public Response updCategory(@RequestBody Category Category) {
        return categoryService.updCategory(Category);
    }
}
