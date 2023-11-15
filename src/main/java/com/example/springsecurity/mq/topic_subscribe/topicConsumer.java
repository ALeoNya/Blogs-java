package com.example.springsecurity.mq.topic_subscribe;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class topicConsumer {
    @RabbitListener(queues = "my_first_topic_queue")
    public void topicMessage(Message message) {
        byte[] body = message.getBody();
        System.out.println("<<<topic模式>>> [ 接收到的消息为 ] : " + new String(body));
    }
}
