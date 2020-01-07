package com.cjd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjd.dao.ItemDescDao;
import com.cjd.pojo.ItemDesc;
import com.cjd.service.ItemDescService;

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

	@Override
	public ItemDesc getItemDesc(Long id) {
		return itemDescDao.findById(id).get();
	}

}
