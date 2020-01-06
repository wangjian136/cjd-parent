package com.cjd.service;

import java.util.List;

import com.cjd.pojo.ItemCat;
import com.cjd.pojo.ItemES;
import com.cjd.pojo.PortalMenu;

public interface ItemService {

	/**
	 * 查询出所有分类类目并转换为特定类型.
	 * @return
	 */
	public PortalMenu showCatMenu();
	
	//根据父id查找所有子分类
	public List<ItemCat> show(long pid);
	
	public ItemES getItemESById(long id);
}
