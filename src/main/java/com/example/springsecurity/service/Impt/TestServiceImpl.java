package com.example.springsecurity.service.Impt;

import com.example.springsecurity.service.RedisService;
import com.example.springsecurity.service.TestService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TestService")
public class TestServiceImpl implements TestService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Override
    @RabbitListener(queues = "my_first_topic_queue")
    public void testConsumer(Message message) {
        byte[] body = message.getBody();
//        System.out.println("<<<topic模式>>> [ 接收到的消息为 ] : " + new String(body));
    }
}
