package com.example.springsecurity.mq.publish_subscribe;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class firstConsumer {
    /**
     * 监听队列
     */
//    @RabbitListener(queues = "my_first_queue")
//    public void process(Message message) {
//        byte[] body = message.getBody();
//        System.out.println("<<<fanuot>>> [ 接收到的消息为 ] : " + new String(body));
//    }
}
