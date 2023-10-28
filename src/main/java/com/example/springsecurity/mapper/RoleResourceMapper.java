package com.example.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springsecurity.pojo.RoleResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RoleResourceMapper extends BaseMapper<RoleResource> {
    /**
     * 通过角色ID获取角色对应的权限ID
     * @param user_id
     * @return
     */
//    @Select("SELECT resource_id FROM kotori.k_role_resource WHERE role_id = #{role_id}")
//    List<String> listPermsByUserId(int role_id);

    @Select("SELECT url \n" +
            "\tFROM k_user_role ur\n" +
            "\t\tLEFT JOIN k_role r ON ur.role_id = r.id\n" +
            "        LEFT JOIN k_role_resource rr ON ur.role_id = rr.role_id\n" +
            "        LEFT JOIN k_resource res ON rr.resource_id = res.id\n" +
            "WHERE user_id= #{user_id}")
    List<String> listPermsByUserId(int user_id);
}
