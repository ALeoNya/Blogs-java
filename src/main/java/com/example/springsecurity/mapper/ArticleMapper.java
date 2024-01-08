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
//    UPDATE kotori.k_article SET is_delete = '1' WHERE id = #{id}
    @Update("update k_article set is_delete = '1' where id = #{id}")
    void fakeDelArticle(Article article);
}
