package com.cjd.service.impl;

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

}
