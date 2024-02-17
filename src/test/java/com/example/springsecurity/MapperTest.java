package com.example.springsecurity;

import com.example.springsecurity.mapper.*;
import com.example.springsecurity.pojo.Article;
import com.example.springsecurity.pojo.ArticleTag;
import com.example.springsecurity.pojo.DTO.ResourceDTO;
import com.example.springsecurity.pojo.Tag;
import com.example.springsecurity.service.UserRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MapperTest {
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    ArticleTagMapper articleTagMapper;
    @Test
    public void userRoleMapperTest() {
        System.out.println(userRoleMapper.selectById(3));
        System.out.println(userRoleMapper.selectByUserId(3));
    }
    @Autowired
    ArticleMapper articleMapper;
    @Test
    public void addArticleTest() {
        Article newArticle = new Article();
        newArticle.setUserId(1);
        newArticle.setArticleContent("bbb");
        newArticle.setArticleTitle("bbb");
        System.out.println(newArticle);
        articleMapper.insert(newArticle);
    }


    @Test
    public void delArticleTag() {
        ArticleTag articleTag = new ArticleTag();
        articleTag.setId(85);
        articleTagMapper.deleteById(articleTag);
        System.out.println(articleTagMapper.selectList(null));
    }
    @Test
    public void delArticle() {
        Article article = new Article();
        article.setId(15);
        articleMapper.deleteById(article.getId());
    }

    @Resource
    TagMapper tagMapper;
    @Test
    public void addTag() {
        Tag tag = new Tag();
        tag.setTagName("测试用例");
        tagMapper.insert(tag);
        System.out.println(tagMapper.selectList(null));
    }

    @Resource
    ResourceMapper resourceMapper;
    @Test
    public void ResourceMapperTest() {
        // 获取所有模块名
        List<com.example.springsecurity.pojo.Resource> resourceList = resourceMapper.getFamilyName();
        List<ResourceDTO> resourceDTOList = new ArrayList<>();
        // 模块名称+family数据结构
        for(int i=0; i<resourceList.size(); i++) {
            // 设置DTO的resource
//            System.out.println(i);
            ResourceDTO resourceDTO = new ResourceDTO();
            resourceDTO.setResource(resourceList.get(i));
//            System.out.println(resourceDTO.getResource());
            // 设置DTO的family
            resourceDTO.setFamily(resourceMapper.getFamily(resourceList.get(i).getId()));
//            System.out.println(resourceDTO.getFamily());
            // 添加到resourceDTOList
            resourceDTOList.add(i,resourceDTO);
//            System.out.println(resourceDTOList);
        }
        System.out.println("the end is: "+resourceDTOList);
    }
}
