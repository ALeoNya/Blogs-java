package com.example.springsecurity.mq;

import org.checkerframework.checker.units.qual.A;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Autowired
    private CachingConnectionFactory connectionFactory;

    public static final String EXCHANGE_NAME_DEMO = "exchange_first_demo";
    public static final String QUEUE_NAME_DEMO = "queue_first_demo";
    public static final String ROUTINGKEY_DEMO = "routing_demo";

    //声明交换机EXCHANGE_NAME_DEMO
    @Bean(EXCHANGE_NAME_DEMO)
    public Exchange EXCHANGE_TOPICS_INFORM(){
        //durable(true) 持久化，mq重启之后交换机还在
        return ExchangeBuilder.directExchange(EXCHANGE_NAME_DEMO).durable(true).build();
    }

    //声明QUEUE_NAME_DEMO队列
    @Bean(QUEUE_NAME_DEMO)
    public Queue QUEUE_INFORM_EMAIL(){
        return new Queue(QUEUE_NAME_DEMO);
    }

    //QUEUE_NAME_DEMO队列绑定交换机EXCHANGE_NAME_DEMO
    @Bean
    public Binding BINDING_QUEUE_EXHANGE_DEMO(@Qualifier(QUEUE_NAME_DEMO) Queue queue,
                                              @Qualifier(EXCHANGE_NAME_DEMO) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_DEMO).noargs();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

}
