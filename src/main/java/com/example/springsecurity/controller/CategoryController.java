package com.example.springsecurity.controller;

import com.example.springsecurity.pojo.Category;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.service.CategoryService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("增加文章分类")
    @PostMapping("/article/addCategory")
    public Response addCategory(@RequestBody Category category) {
        System.out.println(category);
        return categoryService.addCategory(category);
    }

    @ApiOperation("根据分类id删除文章分类")
    @PostMapping("/article/delCategory")
    public Response delCategory(@RequestBody Category Category) {
        return categoryService.delCategory(Category);
    }

    @ApiOperation("根据分类id查询文章分类")
    @PostMapping("/article/selCategoryById")
    public Response selCategoryById(@RequestBody Category Category) {
        return categoryService.selCategoryById(Category);
    }

    @ApiOperation("查询所有文章分类")
    @PostMapping("/article/allCategory")
    public Response allCategory() {
        return categoryService.allCategory();
    }

    @ApiOperation("根据分类id更新文章分类")
    @PostMapping("/article/updCategory")
    public Response updCategory(@RequestBody Category Category) {
        return categoryService.updCategory(Category);
    }
}
