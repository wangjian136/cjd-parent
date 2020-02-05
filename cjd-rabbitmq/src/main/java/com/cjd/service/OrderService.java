package com.cjd.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cjd.pojo.MyOrderParam;

@FeignClient("order-service")
public interface OrderService {

	@RequestMapping("/order/createOrder")
	public Map<String, Object> createOrder(@RequestBody(required = false) MyOrderParam param);
}
