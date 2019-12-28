package com.cjd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjd.dao.ItemParamItemDao;
import com.cjd.pojo.ItemParamItem;
import com.cjd.service.ItemParamItemService;

@Service
@Transactional
public class ItemParamItemServiceImpl implements ItemParamItemService{
	
	@Autowired
	private ItemParamItemDao itemParamItemDao;

	@Override
	public int insItemParamItem(ItemParamItem itemParamItem) {
		ItemParamItem i = itemParamItemDao.save(itemParamItem);
		if(i != null) {
			return 1;
		}
		return -1;
	}

	@Override
	public ItemParamItem getItemParamByItemId(long itemId) {
		return itemParamItemDao.findByItemId(itemId);
	}

}
