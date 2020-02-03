package com.cjd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjd.pojo.Item;
import com.cjd.pojo.ItemES;
import com.cjd.pojo.User;
import com.cjd.service.OrderService;
import com.cjd.service.RedisService;
import com.cjd.util.ItemUtils;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private RedisService redisService;

	@Override
	public List<ItemES> showCartOrder(String token, List<Long> ids) {
		//获取当前用户
		User user = redisService.getUser(token);
		List<Item> items = redisService.getItems("cart:"+user.getId(), 0L, -1L, false);
		List<ItemES> newItems = new ArrayList<ItemES>();
		for (Item item : items) {
			for (Long id : ids) {
				if(id.equals(item.getId())) {
					ItemES itemES = ItemUtils.ItemChangeToES(item);
					newItems.add(itemES);
				}
			}
		}
		return newItems;
	}

}
