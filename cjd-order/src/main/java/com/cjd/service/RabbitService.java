package com.cjd.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjd.pojo.MyOrderParam;

@FeignClient("rabbitmq-service")
public interface RabbitService {

	@RequestMapping("/rabbit/sendOrderMsg")
	public void sendOrderMsg(@RequestBody(required = false) MyOrderParam param);
}
