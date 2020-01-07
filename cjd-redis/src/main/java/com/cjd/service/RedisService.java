package com.cjd.service;

import java.util.List;
import com.cjd.pojo.Content;
import com.cjd.pojo.Item;
import com.cjd.pojo.ItemDesc;

public interface RedisService {

	public boolean exists(String key);
	
	public void setZsetContent(String key, Content content);
	
	public void setZsetItem(String key, Item item);
	
	public void setHashItemDesc(String key, ItemDesc itemDesc);
	
	public ItemDesc getHashItemDesc(String key,String subKey);
	
	public Item getZsetItem(String key, Long id);
	
	public void delZsetObject(String key, Long id);
	
	public void delHashObject(String key, String ... hashKeys);
	
	public List<Content> getContents(String key, Long start, Long end, boolean isSort);
	
	public List<Item> getItems(String key, Long start, Long end, boolean isSort);
}
