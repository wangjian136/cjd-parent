package com.cjd.service;

import java.util.List;

import com.cjd.pojo.ItemES;

public interface OrderService {

	public List<ItemES> showCartOrder(String token,List<Long> ids);
}
