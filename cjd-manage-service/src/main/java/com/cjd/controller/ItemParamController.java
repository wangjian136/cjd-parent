package com.cjd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjd.pojo.EasyUiTree;
import com.cjd.pojo.Item;
import com.cjd.pojo.ItemCat;
import com.cjd.pojo.ItemDesc;
import com.cjd.pojo.ItemParam;
import com.cjd.service.ItemCatService;
import com.cjd.service.ItemParamService;
import com.cjd.util.EasyUIJsonUtils;

@RestController
@RequestMapping("/item_param")
public class ItemParamController {
	
	@Autowired
	private ItemParamService itemParamService;
	
	@Autowired
	private ItemCatService itemCatService;
	

	@RequestMapping("/show_item_param")
	public Map<String, Object> show(@RequestParam int page,@RequestParam int rows) {
		Page<ItemParam> pages = itemParamService.selAllItemParam(page, rows);
		List<ItemParam> params = pages.getContent();
		for (ItemParam itemParam : params) {
			ItemCat itemCat = itemCatService.getItemCat(itemParam.getItemCatId());
			itemParam.setItemCatName(itemCat.getName());
		}
		return EasyUIJsonUtils.convertPageToJson(pages);
	}
	
	@RequestMapping("/delete_item_param")
	public Map<String, Object> delete(@RequestParam(name = "ids",required=true) List<String> ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		int num = itemParamService.delItemByIds(ids);
		if(num == ids.size()) {
			result.put("status", 200);
		}
		return result;
	}
	
	@RequestMapping("/get_item_param_cat")
	public ItemParam getItemParamByCat(@RequestParam Long catId) {
		return itemParamService.getItemParamByCatId(catId);
	}
	
	@RequestMapping("/save_item_param")
	public Map<String, Object> saveItemParam(@RequestBody(required = false) ItemParam itemParam){
		Map<String, Object> result = new HashMap<String, Object>();
		int index = 0;
		index = itemParamService.insItemParam(itemParam);
		System.out.println("index:" + index);
		if(index == 1) {
			result.put("status", 200);
		}
		return result;
	}
}
