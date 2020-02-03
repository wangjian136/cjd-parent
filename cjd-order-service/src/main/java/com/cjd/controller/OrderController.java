package com.cjd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjd.pojo.ItemES;
import com.cjd.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/order/showOrderCart")
	public List<ItemES> showOrderCart(@RequestParam String token,@RequestParam(name = "id") List<Long> ids) {
		return orderService.showCartOrder(token, ids);
	}
}
