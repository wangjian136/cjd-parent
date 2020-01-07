package com.cjd.service;

import java.util.Map;

import com.cjd.pojo.Item;

public interface SearchService {

	public void initES();
	
	public Map<String, Object> queryForES(String query, int page, int rows);
	
	public void insItemES(Item item);
	
	public void updateItemES(Item item);
}
