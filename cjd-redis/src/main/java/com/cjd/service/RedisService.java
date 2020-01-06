package com.cjd.service;

import java.util.List;
import com.cjd.pojo.Content;
import com.cjd.pojo.Item;

public interface RedisService {

	public boolean exists(String key);
	
	public void setZsetContent(String key, Content content);
	
	public void setZsetItem(String key, Item item);
	
	public void delContent(String key, Long id);
	
	public List<Content> getContents(String key, Long start, Long end, boolean isSort);
	
	public List<Item> getItems(String key, Long start, Long end, boolean isSort);
}
