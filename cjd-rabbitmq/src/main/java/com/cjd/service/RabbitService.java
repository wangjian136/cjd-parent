package com.cjd.service;

import com.cjd.pojo.MyOrderParam;

public interface RabbitService {

	public void sendOrderMsg(MyOrderParam param);
	
}
