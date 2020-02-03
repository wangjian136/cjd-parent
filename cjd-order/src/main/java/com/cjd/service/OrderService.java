package com.cjd.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjd.pojo.ItemES;

@FeignClient("order-service")
public interface OrderService {

	@RequestMapping("/order/showOrderCart")
	public List<ItemES> showOrderCart(@RequestParam String token,@RequestParam(name = "id") List<Long> ids);
}
