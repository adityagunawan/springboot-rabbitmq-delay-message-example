package com.example.delaymq.controller;


import com.example.delaymq.config.QueueConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MessageServiceImpl {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDelayMsg(String queueName, String msg) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Message sent time:"+sdf.format(new Date()));
        rabbitTemplate.convertAndSend(QueueConfig.DELAY_EXCHANGE, queueName, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay",5000); // 5 second
                return message;
            }
        });
    }

    public void sendDirectMsg(String msg) {
        rabbitTemplate.convertAndSend(QueueConfig.DIRECT_EXCHANGE, QueueConfig.DIRECT_ROOT_KEY, msg);
    }
}
