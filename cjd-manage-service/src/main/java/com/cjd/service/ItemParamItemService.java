package com.cjd.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cjd.pojo.Item;
import com.cjd.pojo.ItemCat;
import com.cjd.pojo.ItemParam;
import com.cjd.pojo.ItemParamItem;

public interface ItemParamItemService {
	
	public int insItemParamItem(ItemParamItem itemParamItem);
	
	public ItemParamItem getItemParamByItemId(long itemId);
}
