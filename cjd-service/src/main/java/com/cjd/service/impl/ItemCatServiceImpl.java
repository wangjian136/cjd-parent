package com.cjd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjd.dao.ItemCatDao;
import com.cjd.pojo.ItemCat;
import com.cjd.service.ItemCatService;

@Service
@Transactional
public class ItemCatServiceImpl implements ItemCatService{
	
	@Autowired
	private ItemCatDao itemCatDao;

	@Override
	public List<ItemCat> show(long pid) {
		return itemCatDao.findByParentId(pid);
	}
	
	@Override
	public ItemCat getItemCat(long id) {
		return itemCatDao.findById(id).get();
	}

}
