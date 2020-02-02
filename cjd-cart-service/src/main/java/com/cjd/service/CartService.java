package com.cjd.service;

import java.util.List;
import java.util.Map;

import com.cjd.pojo.ItemES;

public interface CartService {

	public void addCart(String token, long id, int num);
	
	public List<ItemES> showCart(String token);
	
	public Map<String, Object> delCart(String token, long id);
	
	public Map<String, Object> updateCart(String token, long id, int num);
}
