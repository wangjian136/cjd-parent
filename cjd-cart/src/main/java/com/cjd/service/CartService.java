package com.cjd.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjd.pojo.ItemES;

@FeignClient("cart-service")
public interface CartService {

	@RequestMapping("/cart/addCart")
	public void addCart(@RequestParam String token,@RequestParam String id,@RequestParam int num);
	
	@RequestMapping("/cart/showCart")
	public List<ItemES> showCart(@RequestParam String token);
	
	@RequestMapping("/cart/updateCart")
	public Map<String, Object> updateCart(@RequestParam String token,@RequestParam String id,@RequestParam int num);
	
	@RequestMapping("/cart/delCart")
	public Map<String, Object> delCart(@RequestParam String token,@RequestParam String id);
	
}