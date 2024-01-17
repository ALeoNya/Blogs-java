package com.example.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springsecurity.pojo.Article;
import com.example.springsecurity.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    @Update("update k_article set is_delete = '1' where id = #{id}")
    void fakeDelArticle(Article article);

    @Update("update k_article set is_delete = '0' where id = #{id}")
    void recoverArticle(Article article);

    @Update("alter table `kotori`.`k_article` auto_increment = 1")
    void autoIncrement();
}
