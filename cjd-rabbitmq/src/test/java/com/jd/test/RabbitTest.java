package com.jd.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cjd.RabbitMQServiceApp;
import com.cjd.pojo.Order;
import com.cjd.service.RabbitService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=RabbitMQServiceApp.class)
public class RabbitTest {

	@Autowired
	private RabbitService rabbitService;
	
	@Test
	public void testSendMsg(){
	
		//rabbitService.sendOrderMsg(order);
	}

}
