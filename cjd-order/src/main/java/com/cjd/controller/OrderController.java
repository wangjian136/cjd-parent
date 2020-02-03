package com.cjd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

	@RequestMapping("/order/order-cart.html")
	public String showCartOrder() {
		return "/order-cart";
	}
}
