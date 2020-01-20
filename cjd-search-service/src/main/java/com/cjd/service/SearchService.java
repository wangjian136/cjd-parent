package com.cjd.service;

import java.util.Map;

import com.cjd.pojo.Item;
import com.cjd.pojo.ItemES;

public interface SearchService {

	public void initES();
	
	public Map<String, Object> queryForES(String query, int page, int rows);
	
	public ItemES findItemById(Long id);
	
	public void insItemES(Item item);
	
	public void updateItemES(Item item);
	
	public void delItemES(Item item);
}
