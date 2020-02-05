package com.cjd.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjd.pojo.MyOrderParam;
import com.cjd.service.RabbitService;

@Service
public class RabbitServiceImpl implements RabbitService {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Override
	public void sendOrderMsg(MyOrderParam param) {
		rabbitTemplate.convertAndSend("order-queue",param);
	}

}
