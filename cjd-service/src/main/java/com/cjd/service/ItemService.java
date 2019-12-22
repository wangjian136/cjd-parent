package com.cjd.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cjd.pojo.Item;

public interface ItemService {
	//查询全部商品
	public Page<Item> selAllItem(int page, int rows);
	//删除商品
	public int delItems(List<Item> items);
	//下架商品
	public int instockItems(List<Item> items);
	//上架商品
	public int reshelfItems(List<Item> items);
	//根据id集合查找商品
	public List<Item> selItemByIds(List<String> ids);
}
