package com.example.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springsecurity.pojo.ArticleTag;
import com.example.springsecurity.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {
    @Update("alter table `kotori`.`k_article_tag` auto_increment = 1")
    void autoIncrement();
}
