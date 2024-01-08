//package com.example.springsecurity.mq;
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitConfig {
//    public static String EXCHANGE_NAME = "my_first_exchange";
//    public static String QUEUE_NAME = "my_first_queue";
//    public static String TOPIC_EXCHANGE_NAME = "my_first_topic_exchange";
//    public static String TOPIC_QUEUE_NAME = "topic.demo";
//    public static String TOPIC_QUEUE_NAME2 = "topic.demo2";
//
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
//
//    /**
//     * 声明队列 Topic
//     */
//    @Bean
//    public Queue topic_queue() {
//        return new Queue(TOPIC_QUEUE_NAME, true, false, false);
//    }
//
//    @Bean
//    public Queue topic_queue2() {
//        return new Queue(TOPIC_QUEUE_NAME2, true, false, false);
//    }
//
//
//    /**
//     * 声明交换机 Topic
//     */
//    @Bean
//    public TopicExchange topic_Exchange() {
//        return new TopicExchange(TOPIC_EXCHANGE_NAME, true, false);
//    }
//
//    /**
//     * 声明交换机和队列的绑定关系 Topic
//     */
//    @Bean
//    public Binding topic_queue_Binding() {
//        return BindingBuilder.bind(topic_queue()).to(topic_Exchange()).with(TOPIC_QUEUE_NAME);
//    }
//    @Bean
//    public Binding topic_queue_Binding2() {
//            return BindingBuilder.bind(topic_queue2()).to(topic_Exchange()).with(TOPIC_QUEUE_NAME2);
//    }
//}