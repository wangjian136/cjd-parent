package com.cjd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjd.dao.OrderDao;
import com.cjd.dao.OrderItemDao;
import com.cjd.dao.OrderShippingDao;
import com.cjd.pojo.Item;
import com.cjd.pojo.ItemES;
import com.cjd.pojo.MyOrderParam;
import com.cjd.pojo.Order;
import com.cjd.pojo.OrderItem;
import com.cjd.pojo.OrderShipping;
import com.cjd.pojo.User;
import com.cjd.service.OrderService;
import com.cjd.service.RedisService;
import com.cjd.util.IDUtils;
import com.cjd.util.ItemUtils;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderItemDao orderItemDao;
	
	@Autowired
	private OrderShippingDao orderShippingDao;

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

	@Override
	public Map<String, Object> saveOrder(MyOrderParam param) {
		Map<String, Object> result = new HashMap<String, Object>();
		//订单表数据
		Order order = new Order();
		order.setPayment(param.getPayment());
		order.setPaymentType(param.getPaymentType());
		long id = IDUtils.genItemId();
		order.setOrderId(id+"");
		Date date =new Date();
		order.setCreateTime(date);
		order.setUpdateTime(date);
		
		order.setUserId(7L);
		order.setBuyerNick("zhangsan");
		order.setBuyerRate(0);
		//订单-商品表
		for (OrderItem  item : param.getOrderItems()) {
			item.setId(IDUtils.genItemId()+"");
			item.setOrderId(id+"");
		}
		//收货人信息
		OrderShipping shipping = param.getOrderShipping();
		shipping.setOrderId(id+"");
		shipping.setCreated(date);
		shipping.setUpdated(date);
		
		//保存全部数据
		orderDao.save(order);
		for (OrderItem orderItem : param.getOrderItems()) {
			orderItemDao.save(orderItem);
		}
		orderShippingDao.save(shipping);
		return result;
	}

}
