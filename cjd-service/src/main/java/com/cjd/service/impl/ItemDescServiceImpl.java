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
import com.cjd.pojo.Item;
import com.cjd.pojo.ItemDesc;
import com.cjd.service.ItemDescService;
import com.cjd.service.ItemService;

@Service
@Transactional
public class ItemDescServiceImpl implements ItemDescService{
	
	@Autowired
	private ItemDescDao itemDescDao;

	@Override
	public int insItemDesc(ItemDesc itemDesc) {
		ItemDesc desc = itemDescDao.save(itemDesc);
		if(desc != null) {
			return 1;
		}
		return -1;
	}

}
