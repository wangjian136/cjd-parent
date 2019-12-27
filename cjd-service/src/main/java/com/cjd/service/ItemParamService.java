package com.cjd.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cjd.pojo.Item;
import com.cjd.pojo.ItemCat;
import com.cjd.pojo.ItemParam;

public interface ItemParamService {
	//查询全部商品规格
	public Page<ItemParam> selAllItemParam(int page, int rows);

	//根据id集合删除商品规格
	public int delItemByIds(List<String> ids);
	
	public ItemParam getItemParamByCatId(long catId);
	
	public int insItemParam(ItemParam itemParam);
}
