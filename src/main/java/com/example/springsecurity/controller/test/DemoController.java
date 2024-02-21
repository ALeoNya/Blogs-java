package com.example.springsecurity.controller.test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController  {

    private Map<String, PaymentTypeService> paymentTypeServices;

    /**
     * 构造函数初始化不同支付方式类型和实现类引用map
     * @param services (将实例注入也就是Object类型
     */
        public DemoController(@Autowired List<PaymentTypeService> services){
            //将接口按照type的类型存入paymentTypeServices中
        paymentTypeServices = services.stream().collect(Collectors.toMap(PaymentTypeService::type, i->i));
    }

    /**
     * 请求某个支付方式
     * @date: 2018年4月23日 下午2:21:28
     * @param type
     */
    @GetMapping("/test/{type}")
    public void test(@PathVariable("type") String type){
        // 获取该支付方式实现类
        PaymentTypeService service = paymentTypeServices.get(type);
        service.methodA();
        service.methodB();
    }
}