package com.cjd.service;

import java.util.List;

import com.cjd.pojo.ItemCat;

public interface ItemCatService {
	
	public List<ItemCat> show(long pid);
	
	public ItemCat getItemCat(long id);
}
