package com.cjd.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjd.pojo.Item;
import com.cjd.pojo.ItemDesc;
import com.cjd.pojo.ItemParamItem;
import com.cjd.service.ItemDescService;
import com.cjd.service.ItemParamItemService;
import com.cjd.service.ItemService;
import com.cjd.util.EasyUIJsonUtils;
import com.cjd.util.IDUtils;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemDescService itemDescService;
	
	@Autowired
	private ItemParamItemService itemParamItemService;

	@RequestMapping("/show_item")
	public Map<String, Object> show(@RequestParam int page,@RequestParam int rows) {
		Page<Item> pages = itemService.selAllItem(page, rows);
		return EasyUIJsonUtils.convertPageToJson(pages);
	}
	
	@RequestMapping("/delete_item")
	public Map<String, Object> delete(@RequestParam(name = "ids",required=true) List<String> ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Item> items = itemService.selItemByIds(ids);
		int num = itemService.delItems(items);
		if(num == 1) {
			result.put("status", 200);
		}
		return result;
	}
	
	@RequestMapping("/instock_item")
	public Map<String, Object> instock(@RequestParam(name = "ids",required=true) List<String> ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Item> items = itemService.selItemByIds(ids);
		int num = itemService.instockItems(items);
		if(num == 1) {
			result.put("status", 200);
		}
		return result;
	}
	
	@RequestMapping("/reshelf_item")
	public Map<String, Object> reshelf(@RequestParam(name = "ids",required=true) List<String> ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Item> items = itemService.selItemByIds(ids);
		int num = itemService.reshelfItems(items);
		if(num == 1) {
			result.put("status", 200);
		}
		return result;
	}
	
	@RequestMapping("/save_item")
	public Map<String, Object> save(@RequestBody(required = false) Item item, @RequestParam String desc, @RequestParam String paramData) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		ItemDesc itemDesc = new ItemDesc();
		ItemParamItem paramItem = new ItemParamItem();
		Date date = new Date();
		if(item.getId() == null) {
			long id = IDUtils.genItemId();
			item.setId(id);
			itemDesc.setItemId(id);
			paramItem.setItemId(id);
		}else {
			ItemParamItem itemParamItem = itemParamItemService.getItemParamByItemId(item.getId());
			paramItem = itemParamItem;
			itemDesc.setItemId(item.getId());
		}
		item.setCreated(date);
		itemDesc.setCreated(date);
		paramItem.setCreated(date);
		item.setUpdated(date);
		itemDesc.setUpdated(date);
		paramItem.setUpdated(date);
		item.setStatus((byte) 1);
		itemDesc.setItemDesc(desc);
	
		paramItem.setParamData(paramData);
		int index = 0;

		index = itemService.insItemDesc(item, itemDesc, paramItem);
		System.out.println("index:" + index);
		if(index == 1) {
			result.put("status", 200);
		}
		return result;
	}
}
