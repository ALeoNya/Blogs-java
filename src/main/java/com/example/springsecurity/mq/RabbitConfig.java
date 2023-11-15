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
    private static String EXCHANGE_NAME = "my_first_exchange";
    private static String QUEUE_NAME = "my_first_queue";
    private static String TOPIC_EXCHANGE_NAME = "my_first_topic_exchange";
    private static String TOPIC_QUEUE_NAME = "my_first_topic_queue";

//    /**
//     * 声明交换机 fanuot
//     */
//    @Bean
//    public FanoutExchange exchange() {
//        return new FanoutExchange(EXCHANGE_NAME, true, false);
//    }
//
//
//
//    /**
//     * 声明队列 fanuot
//     */
//    @Bean
//    public Queue queue() {
//        return new Queue(QUEUE_NAME, true, false, false);
//    }
//
//    /**
//     * 声明交换机和队列的绑定关系 fanuot
//     */
//    @Bean
//    public Binding queueBinding(Queue queue, FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(queue).to(fanoutExchange);
//    }

    /**
     * 声明队列 Topic
     */
    @Bean
    public Queue topic_queue() {
        return new Queue(TOPIC_QUEUE_NAME, true, false, false);
    }


    /**
     * 声明交换机 Topic
     */
    @Bean
    public TopicExchange topic_Exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME, true, false);
    }

    /**
     * 声明交换机和队列的绑定关系 Topic
     */
    @Bean
    public Binding topic_queue_Binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("topic.*");
    }
}
