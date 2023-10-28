//package com.example.springsecurity;
//
//import com.example.springsecurity.mq.RabbitConfig;
//import org.checkerframework.checker.units.qual.A;
//import org.junit.jupiter.api.Test;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class RabbitTest {
//    @Autowired
//    RabbitTemplate rabbitTemplate;
//
//    /**
//     * 生产者
//     * 发送message
//     */
//    @Test
//    public void RabbitMQ_Demo() {
//        String message = "Hello Rabbit！";
//        /**
//         * params
//         * 1.Exchange
//         * 2.RoutingKEY
//         * 3.DATA SOURCE
//         */
//        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME_DEMO,RabbitConfig.ROUTINGKEY_DEMO,message);
//    }
//}
