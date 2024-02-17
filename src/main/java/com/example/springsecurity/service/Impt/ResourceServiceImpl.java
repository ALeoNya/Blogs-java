package com.example.springsecurity.service.Impt;

import com.example.springsecurity.mapper.ResourceMapper;
import com.example.springsecurity.pojo.DTO.ResourceDTO;
import com.example.springsecurity.pojo.Resource;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.response.Code;
import com.example.springsecurity.response.Msg;
import com.example.springsecurity.util.redis.service.RedisService;
import com.example.springsecurity.service.ResourceService;
import com.example.springsecurity.util.redis.config.InitRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("ResourceService")
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RedisService redisService;

    // 传入哪几个属性？not null 的属性

    /**
     * 属性：resource_name，url，request_method，parent_id，is_anonymous
     * @param resource
     * @return
     */
    @Override
    public Response addResource(Resource resource) {
        System.out.println(resource);
        try {
            if(resource == null) {
                return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, "插入数据为空");
            }
            System.out.println(resource);

            resourceMapper.autoIncrement();
            resourceMapper.insert(resource);
            return new Response(Code.SUCCESS, Msg.ADD_SUCCESS_MSG, resource);
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response delResource(Resource resource) {
//        int key = resource.getId();
        try {
            resourceMapper.deleteById(resource.getId());
            return new Response(Code.SUCCESS, Msg.DEL_SUCCESS_MSG, null);
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response selResourceById(Resource resource) {
        int key = resource.getId();
        try {
            if(redisService.containsKey(InitRedis.KEY_RESOURCE_LIST, key)) {
                return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.getObject(InitRedis.KEY_RESOURCE_LIST, key));
            } else {
                Resource get = resourceMapper.selectById(resource.getId());
                //查到null值缓存到redis设置过期时间为6min
                if(get == null) {
                    redisService.cacheValue(InitRedis.KEY_RESOURCE_LIST, resource.getId(), get, 360);
                    return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, "你查询的是一个空值");
                }
                redisService.cacheValue(InitRedis.KEY_RESOURCE_LIST, resource.getId(), get, 3600);
                return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.getObject(InitRedis.KEY_RESOURCE_LIST, key));
            }

        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response allResource() {
        try {
            return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, resourceMapper.selectList(null));
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response allResourceByType() {
        try {
            // 查询“模块”字段获取其id，再用id查询父节点=id的数据，最后返回数据
            // 获取所有模块名
            List<com.example.springsecurity.pojo.Resource> resourceList = resourceMapper.getFamilyName();
            List<ResourceDTO> resourceDTOList = new ArrayList<>();
            // 模块名称+family数据结构
            for(int i=0; i<resourceList.size(); i++) {
                ResourceDTO resourceDTO = new ResourceDTO();  // 数组对象存储对象的引用而不是值，对象名字相同但引索不同
                // 设置DTO的resource
                resourceDTO.setResource(resourceList.get(i));
                // 设置DTO的family
                resourceDTO.setFamily(resourceMapper.getFamily(resourceList.get(i).getId()));
                // 添加到resourceDTOList
                resourceDTOList.add(resourceDTO);
            }
            return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, resourceDTOList);
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response updResource(Resource resource) {
//        int key = resource.getId();
        try {
//            redisService.expire(InitRedis.KEY_RESOURCE_LIST, key, 3, TimeUnit.SECONDS);
            System.out.println(resource.getRequestMethod());
            resourceMapper.updateById(resource);
            return new Response(Code.SUCCESS, Msg.UPD_SUCCESS_MSG, null);
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.UPD_FAIL_MSG, e);
        }
    }
}