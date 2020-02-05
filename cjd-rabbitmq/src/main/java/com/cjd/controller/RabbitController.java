package com.cjd.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjd.pojo.MyOrderParam;
import com.cjd.service.OrderService;
import com.cjd.service.RabbitService;

@RestController
public class RabbitController {

	@Autowired
	private RabbitService rabbitService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/rabbit/sendOrderMsg")
	public void sendOrderMsg(@RequestBody(required = false) MyOrderParam param) {
		rabbitService.sendOrderMsg(param);
	}
	
	@RabbitListener(queues="order-queue")
	public void receiveOrderMsg(MyOrderParam param){
		System.out.println("save param....."+param);
		orderService.createOrder(param);
	}
}
