package com.cjd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjd.dao.ItemDao;
import com.cjd.pojo.Item;
import com.cjd.service.ItemService;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemDao itemDao;

	@Override
	public Page<Item> selAllItem(int page, int rows) {
		page = page - 1;
		Pageable pageable = PageRequest.of(page, rows);
		Page<Item> pages = itemDao.findAll(pageable);
		return pages;
	}

	@Override
	public int delItems(List<Item> items) {
		if(items != null) {
			for (Item item : items) {
				item.setStatus((byte) 3);
			}
			itemDao.saveAll(items);
			return 1;
		}
		return -1;
	}

	@Override
	public List<Item> selItemByIds(List<String> ids) {
		List<Long> newIds = new ArrayList<Long>();
		for (String id : ids) {
			Long newId = Long.parseLong(id);
			newIds.add(newId);
		}
		List<Item> items = itemDao.findAllById(newIds);
		return items;
	}

}
