package com.example.delaymq;

import com.example.delaymq.config.QueueConfig;
import com.example.delaymq.controller.MessageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
class DelaymqApplicationTests {

	@Autowired
	private MessageServiceImpl messageService;

	private String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

	@Test
	public void send() {
		messageService.sendDelayMsg(QueueConfig.DELAY_QUEUE,
				"KEZIA MAULIDA, sent on : " + time);
	}

	@Test
	public void sendDirect() {
		messageService.sendDirectMsg("ini direct message, sent on : " + time);
	}


}
