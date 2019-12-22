package com.cjd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjd.pojo.Item;
import com.cjd.service.ItemService;
import com.cjd.util.EasyUIJsonUtils;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;

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
}
