package com.example.springsecurity.mq.topic_subscribe;

import com.example.springsecurity.mq.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class topicConsumer {
    @RabbitListener(queues = "topic.demo")
    public void topicMessage(Message message) {
        byte[] body = message.getBody();
        System.out.println("<<<topic模式>>> [ topic.demo队列:接收到的消息为 ] : " + new String(body));
    }

    @RabbitListener(queues = "topic.demo2")
    public void topicMessage2(Message message) {
        byte[] body = message.getBody();
        System.out.println("<<<topic模式>>> [ topic.demo2队列:接收到的消息为 ] : " + new String(body));
    }


}
