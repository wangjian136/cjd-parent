package com.cjd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjd.pojo.ItemDesc;
import com.cjd.service.ItemDescService;

@RestController
@RequestMapping("/item_desc")
public class ItemDescController {
	
	@Autowired
	private ItemDescService itemDescService;
	
	@RequestMapping("/get_item_desc")
	public ItemDesc getItemDesc(@RequestParam Long id) {
		return itemDescService.getItemDesc(id);
	}
}
