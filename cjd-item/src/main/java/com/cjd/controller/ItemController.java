package com.cjd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjd.pojo.ItemDesc;
import com.cjd.service.ItemService;
import com.cjd.service.ManageService;
import com.cjd.service.RedisService;
import com.cjd.util.ItemUtils;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ManageService manageService;
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/item/{id}.html")
	public String showItemDetails(@PathVariable long id,Model model){
		if(redisService.existsKey("items")) {
			model.addAttribute("item", ItemUtils.ItemChangeToES(redisService.getItem("items", id)));
		}else {
			model.addAttribute("item", itemService.showItem(id));
		}
		return "/item";
	}
	
	@RequestMapping("/item/desc/{itemId}.html")
	@ResponseBody
	public String showItemDesc(@PathVariable long itemId){
		ItemDesc itemDesc = manageService.getItemDesc(itemId);
		return itemDesc.getItemDesc();
	}
	
	@RequestMapping("/rest/itemcat/all")
	public Object  showMenu(String callback) throws Exception{
        return itemService.showMenu(callback);
	}
}
