package com.cjd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjd.dao.ItemParamDao;
import com.cjd.pojo.ItemParam;
import com.cjd.service.ItemParamService;

@Service
@Transactional
public class ItemParamServiceImpl implements ItemParamService{
	
	@Autowired
	private ItemParamDao itemParamDao;

	@Override
	public Page<ItemParam> selAllItemParam(int page, int rows) {
		page = page - 1;
		Pageable pageable = PageRequest.of(page, rows);
		Page<ItemParam> pages = itemParamDao.findAll(pageable);
		return pages;
	}

	@Override
	public int delItemByIds(List<String> ids) {
		int index = 0;
		for (String id : ids) {
			itemParamDao.deleteById(Long.parseLong(id));
			index++;
		}
		return index;
	}

	@Override
	public ItemParam getItemParamByCatId(long catId) {
		return itemParamDao.findByItemCatId(catId);
	}

	@Override
	public int insItemParam(ItemParam itemParam) {
		ItemParam i = itemParamDao.save(itemParam);
		if(i != null) {
			return 1;
		}
		return -1;
	}

}
