package com.cjd.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cjd.pojo.Item;

public interface ItemService {

	public Page<Item> selAllItem(int page, int rows);
	
	public int delItems(List<Item> items);
	
	public List<Item> selItemByIds(List<String> ids);
}
