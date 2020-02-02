package com.cjd.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjd.service.CartService;
import com.cjd.util.CookieUtils;

@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/cart/add/{id}.html")
	public String addCart(@PathVariable String id,int num, HttpServletRequest request) {
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		cartService.addCart(token, id, num);
		return "/cartSuccess";
	}
	
	@RequestMapping("/cart/cart.html")
	public String showCart(Model model, HttpServletRequest request) {
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		model.addAttribute("cartList", cartService.showCart(token));
		return "/cart";
	}
	
	@RequestMapping("/cart/update/num/{id}/{num}.action")
	public Map<String, Object> updateCart(@PathVariable String id,@PathVariable String num,HttpServletRequest request) {
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		return cartService.updateCart(token, id, Integer.parseInt(num));
	}
	
	@RequestMapping("/cart/delete/{id}.action")
	public Map<String, Object> deleteCart(@PathVariable String id,HttpServletRequest request) {
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		return cartService.delCart(token, id);
	}
}
