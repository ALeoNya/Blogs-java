package com.example.springsecurity.controller.test;

import org.springframework.stereotype.Service;

/**
 * 支付方式B实现类
 * @date: 2018年4月23日 下午2:20:27
 */
@Service
public class BPaymentTypeServiceImpl implements PaymentTypeService {

    private final String type = "B";

    @Override
    public void methodA() {
        // TODO Auto-generated method stub
        System.out.println("PaymentType B invoke methodA");
    }

    @Override
    public void methodB() {
        // TODO Auto-generated method stub
        System.out.println("PaymentType B invoke methodB");
    }

    @Override
    public String type() {
        return type;
    }

}