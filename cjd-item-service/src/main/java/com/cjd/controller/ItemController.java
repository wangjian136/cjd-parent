package com.cjd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjd.pojo.ItemES;
import com.cjd.service.ItemService;
import com.cjd.util.JsonUtils;

@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	/**
	 * 返回jsonp数据格式包含所有菜单信息
	 * @param callback
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/itemcat/all")
	public Object  showMenu(@RequestParam String callback) throws Exception{
		String jsonStr = JsonUtils.objectToJsonStr(itemService.showCatMenu());
        return callback + "("+jsonStr+");";
	}
	
	@RequestMapping("/item/show")
	public ItemES showItem(@RequestParam long id){
		return itemService.getItemESById(id);
	}
}
