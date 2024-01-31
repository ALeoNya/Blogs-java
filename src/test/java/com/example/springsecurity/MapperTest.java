package com.example.springsecurity;

import com.example.springsecurity.mapper.ArticleMapper;
import com.example.springsecurity.mapper.ArticleTagMapper;
import com.example.springsecurity.mapper.TagMapper;
import com.example.springsecurity.mapper.UserRoleMapper;
import com.example.springsecurity.pojo.Article;
import com.example.springsecurity.pojo.ArticleTag;
import com.example.springsecurity.pojo.Tag;
import com.example.springsecurity.service.UserRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.time.LocalDateTime;

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
}
