package com.cjd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjd.service.ItemService;
import com.cjd.util.JsonUtils;

@RestController
@RequestMapping("/portal")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value = "/itemcat/all",produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	public Object showMenu(String callback) throws Exception{
		String jsonStr = JsonUtils.objectToJsonStr(itemService.showCatMenu());
		return callback + "("+jsonStr+");";
	}
}
