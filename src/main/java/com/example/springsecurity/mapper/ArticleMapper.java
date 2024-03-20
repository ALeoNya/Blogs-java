package com.example.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springsecurity.pojo.Article;
import com.example.springsecurity.pojo.DTO.ArticleDTO;
import com.example.springsecurity.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    @Update("update k_article set is_delete = '1' where id = #{id}")
    void fakeDelArticle(Article article);

    @Update("update k_article set is_delete = '0' where id = #{id}")
    void recoverArticle(Article article);

    @Update("alter table `kotori`.`k_article` auto_increment = 1")
    void autoIncrement();

    @Select("SELECT * FROM kotori.k_article")
    List<ArticleDTO> allArticleAsDTO();

    @Select("SELECT * FROM `kotori`.`k_article` LIMIT #{offset},4;\n")
    List<Article> paging(int offset);

    // 转换时间戳格式datatime=>YYYY.MM.DD
    @Select("SELECT DATE_FORMAT(create_time, '%Y.%m.%d') AS createTime FROM `kotori`.`k_article`;")
    List<Article> dateFormat();

}
