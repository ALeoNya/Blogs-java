package com.example.springsecurity.service;

import org.springframework.amqp.core.Message;

public interface TestService {
    void testConsumer(Message message);
}
