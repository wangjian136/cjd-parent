package com.cjd.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjd.service.ItemService;

@Controller
public class ItemParamController {

	@Autowired
	private ItemService itemService;
	
	/**
	 * 分页显示商品
	 */
	@RequestMapping("/item/param/list")
	@ResponseBody
	public Map<String, Object> show(int page, int rows){
		return itemService.selAllItemParam(page, rows);
	}
}
