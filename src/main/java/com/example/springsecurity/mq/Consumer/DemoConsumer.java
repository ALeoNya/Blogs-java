//package com.example.springsecurity.mq.Consumer;
//
//import com.example.springsecurity.mq.RabbitConfig;
//import com.rabbitmq.client.AMQP;
//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DemoConsumer {
//    //监听Demo队列
//    @RabbitListener(queues = {RabbitConfig.QUEUE_NAME_DEMO})
//    public void Listen_Demo(Object msg, Message message, Channel channel) {
//        System.out.println("消费者监听到的队列消息是: " + msg);
//    }
//}
