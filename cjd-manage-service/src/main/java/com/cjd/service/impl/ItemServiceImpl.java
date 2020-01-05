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
import com.cjd.dao.ItemDescDao;
import com.cjd.dao.ItemParamItemDao;
import com.cjd.pojo.Item;
import com.cjd.pojo.ItemDesc;
import com.cjd.pojo.ItemParamItem;
import com.cjd.service.ItemService;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private ItemDescDao itemDescDao;
	
	@Autowired
	private ItemParamItemDao itemParamItemDao;

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

	@Override
	public int instockItems(List<Item> items) {
		if(items != null) {
			for (Item item : items) {
				item.setStatus((byte) 2);
			}
			itemDao.saveAll(items);
			return 1;
		}
		return -1;
	}

	@Override
	public int reshelfItems(List<Item> items) {
		if(items != null) {
			for (Item item : items) {
				item.setStatus((byte) 1);
			}
			itemDao.saveAll(items);
			return 1;
		}
		return -1;
	}

	@Override
	public int insItem(Item item) {
		Item i = itemDao.save(item);
		if(i != null) {
			return 1;
		}
		return -1;
	}
	
	@Override
	public int insItemDesc(Item item, ItemDesc desc, ItemParamItem paramItem) throws Exception {
		int index =0;
		try {
			Item i = itemDao.save(item);
			if(i != null) {
				index+=1;
			}
			ItemDesc d = itemDescDao.save(desc);
			if(d != null) {
				index+=1;
			}
			ItemParamItem p = itemParamItemDao.save(paramItem);
			if(p != null) {
				index+=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(index==3){
			return 1;
		}else{
			throw new Exception("新增失败,数据还原");
		}
	}

	@Override
	public List<Item> selAllItem2() {
		return itemDao.findAll();
	}

}
