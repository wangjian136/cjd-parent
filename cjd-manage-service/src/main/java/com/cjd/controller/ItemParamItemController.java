package com.cjd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjd.pojo.ItemParamItem;
import com.cjd.service.ItemParamItemService;

@RestController
@RequestMapping("/item_param_item")
public class ItemParamItemController {
	
	@Autowired
	private ItemParamItemService itemParamItemService;
	
	
	@RequestMapping("/get_item_param_item")
	public ItemParamItem getItemParamByItem(@RequestParam Long itemId) {
		return itemParamItemService.getItemParamByItemId(itemId);
	}
	
}
