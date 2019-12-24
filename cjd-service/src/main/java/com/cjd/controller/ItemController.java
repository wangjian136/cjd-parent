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
import com.cjd.service.ItemDescService;
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
	public Map<String, Object> save(@RequestBody(required = false) Item item, @RequestParam String desc) {
		Map<String, Object> result = new HashMap<String, Object>();
		long id = IDUtils.genItemId();
		item.setId(id);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		item.setStatus((byte) 1);
		int index = itemService.insItem(item);
		if(index > 0) {
			ItemDesc itemDesc = new ItemDesc();
			itemDesc.setItemId(id);
			itemDesc.setItemDesc(desc);
			itemDesc.setCreated(date);
			itemDesc.setUpdated(date);
			index += itemDescService.insItemDesc(itemDesc);
		}
		if(index == 2) {
			result.put("status", 200);
		}
		return result;
	}
}
