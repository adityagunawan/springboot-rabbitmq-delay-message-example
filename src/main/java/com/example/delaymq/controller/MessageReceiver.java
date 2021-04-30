package com.example.delaymq.controller;

import com.example.delaymq.config.QueueConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MessageReceiver {

    @RabbitListener(queues = QueueConfig.DELAY_QUEUE)
    public void receive(String msg) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Message received time:"+sdf.format(new Date()));
        System.out.println("Received message:"+msg);
    }
}
