package com.cjd.service;

import org.springframework.data.domain.Page;

import com.cjd.pojo.ItemParam;

public interface ItemParamService {
	//查询全部商品规格
	public Page<ItemParam> selAllItemParam(int page, int rows);

}
