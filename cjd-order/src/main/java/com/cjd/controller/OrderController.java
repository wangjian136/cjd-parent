package com.cjd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjd.pojo.MyOrderParam;
import com.cjd.service.OrderService;
import com.cjd.service.RabbitService;
import com.cjd.util.CookieUtils;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private RabbitService rabbitService;

	@RequestMapping("/order/order-cart.html")
	public String showCartOrder(Model model,@RequestParam(name = "id") List<Long> ids, HttpServletRequest request) {
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		model.addAttribute("cartList", orderService.showOrderCart(token, ids));
		return "/order-cart";
	}
	
	@RequestMapping("/order/create.html")
	public String createOrder(MyOrderParam param) {
		rabbitService.sendOrderMsg(param);
		return "/success";
	}
}
