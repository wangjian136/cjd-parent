package com.cjd.service;

import com.cjd.pojo.ItemDesc;

public interface ItemDescService {
	
	public int insItemDesc(ItemDesc itemDesc);
	
	public ItemDesc getItemDesc(Long id);
}
