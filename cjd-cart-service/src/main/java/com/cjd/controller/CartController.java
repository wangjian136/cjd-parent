package com.cjd.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjd.pojo.ItemES;
import com.cjd.service.CartService;

@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;

	@RequestMapping("/cart/addCart")
	public void addCart(@RequestParam String token,@RequestParam String id,@RequestParam int num) {
		cartService.addCart(token,Long.parseLong(id), num);
	}
	
	@RequestMapping("/cart/showCart")
	public List<ItemES> showCart(@RequestParam String token) {
		return cartService.showCart(token);
	}
	
	@RequestMapping("/cart/updateCart")
	public Map<String, Object> updateCart(@RequestParam String token,@RequestParam String id,@RequestParam int num) {
		return cartService.updateCart(token, Long.parseLong(id), num);
	}
	
	@RequestMapping("/cart/delCart")
	public Map<String, Object> delCart(@RequestParam String token,@RequestParam String id) {
		return cartService.delCart(token, Long.parseLong(id));
	}
}
