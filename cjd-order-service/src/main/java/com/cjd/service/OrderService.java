package com.cjd.service;

import java.util.List;
import java.util.Map;

import com.cjd.pojo.ItemES;
import com.cjd.pojo.MyOrderParam;

public interface OrderService {

	public List<ItemES> showCartOrder(String token,List<Long> ids);
	
	public Map<String, Object> saveOrder(MyOrderParam param);
}
