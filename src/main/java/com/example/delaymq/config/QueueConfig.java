package com.example.delaymq.config;



import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class QueueConfig {

    public static final String DIRECT_EXCHANGE = "direct_exchange";
    public static final String DELAY_EXCHANGE = "delay_exchange";
    public static final String DIRECT_ROOT_KEY = "direct_root_key";
    public static final String DELAY_ROOT_KEY = "delay_root_key";
    public static final String DIRECT_QUEUE = "direct_queue";
    public static final String DELAY_QUEUE = "delay_queue";

    @Bean
    public List<CustomExchange> exchangeList() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");

        return Arrays.asList(
                new CustomExchange(DELAY_EXCHANGE, "x-delayed-message",true, false,args),
                new CustomExchange(DIRECT_EXCHANGE, "topic", true, false)
        );
    }

//    @Bean
//    public TopicExchange directExchange() {
//        return new TopicExchange(DIRECT_EXCHANGE);
//    }

    @Bean
    public List<Queue> queueList() {
        return Arrays.asList(
                new Queue(DELAY_QUEUE, true),
                new Queue(DIRECT_QUEUE, true)
        );
    }

    @Bean
    public List<Binding> bindingList() {
        return Arrays.asList(
                BindingBuilder.bind(queueList().get(0)).to(exchangeList().get(0)).with(DELAY_QUEUE).noargs(),
                BindingBuilder.bind(queueList().get(1)).to(exchangeList().get(1)).with(DIRECT_ROOT_KEY).noargs()
        );
    }

//    @Bean
//    public Queue queue() {
//        return new Queue(DELAY_QUEUE, true);
//    }
//
//    @Bean
//    public Binding binding() {
//        return BindingBuilder.bind(queue()).to(delayExchange()).with(DELAY_QUEUE).noargs();
//    }
}

