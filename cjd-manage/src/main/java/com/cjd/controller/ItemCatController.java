package com.cjd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjd.service.ItemService;

@Controller
public class ItemCatController {

	@Autowired
	private ItemService itemService;
	
	/**
	 * 显示商品类目
	 * @param id
	 * @return
	 */
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List showCat(@RequestParam(defaultValue="0") long id){
		Map<String, Object> map = itemService.show(id);
		return (List) map.get("listTree");
	}
	
	
	/**
	 * 显示商品类目详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/item/cat/get")
	@ResponseBody
	public Object showCatDetail(@RequestParam(defaultValue="0") long id){
		Map<String, Object> map = itemService.getItemCat(id);
		return map.get("cat");
	}
}
