package com.cjd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjd.pojo.Item;
import com.cjd.pojo.ItemES;
import com.cjd.pojo.User;
import com.cjd.service.CartService;
import com.cjd.service.RedisService;
import com.cjd.util.ItemUtils;

@Service
@Transactional
public class CartServiceImpl implements CartService {
	
	@Autowired
	private RedisService redisService;

	@Override
	public void addCart(String token, long id, int num) {
		//获取当前用户
		User user = redisService.getUser(token);
		Item item = null;
		if(redisService.existsHashKey("cart:"+user.getId()) && redisService.getItem("cart:"+user.getId(), id) != null) {
			item = redisService.getItem("cart:"+user.getId(), id);
			redisService.delZsetObject("cart:"+user.getId(), id);
			item.setNum(item.getNum()+num);
		}else {
			//获取商品
			item = redisService.getItem("items", id);
			item.setNum(num);
		}
		redisService.setItem("cart:"+user.getId(), item);
	}

	@Override
	public List<ItemES> showCart(String token) {
		List<ItemES> items = new ArrayList<ItemES>();
		//获取当前用户
		User user = redisService.getUser(token);
		if(redisService.existsHashKey("cart:"+user.getId())) {
			List<Item> list = redisService.getItems("cart:"+user.getId(), 0L, -1L, false);
			for (Item item : list) {
				ItemES itemES = ItemUtils.ItemChangeToES(item);
				items.add(itemES);
			}
		}
		return items;
	}

	@Override
	public Map<String, Object> delCart(String token, long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		//获取当前用户
		User user = redisService.getUser(token);
		redisService.delZsetObject("cart:"+user.getId(), id);
		return result;
	}

	@Override
	public Map<String, Object> updateCart(String token, long id, int num) {
		Map<String, Object> result = new HashMap<String, Object>();
		//获取当前用户
		User user = redisService.getUser(token);
		Item item = redisService.getItem("cart:"+user.getId(), id);
		redisService.delZsetObject("cart:"+user.getId(), id);
		item.setNum(num);
		redisService.setItem("cart:"+user.getId(), item);
		return result;
	}

}
