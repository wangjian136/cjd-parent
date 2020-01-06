package com.cjd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjd.service.ItemService;
import com.cjd.util.JsonUtils;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{id}.html")
	public String showItemDetails(@PathVariable long id,Model model){
		model.addAttribute("item", itemService.showItem(id));
		return "/item";
	}
	
	@RequestMapping("/rest/itemcat/all")
	public Object  showMenu(String callback) throws Exception{
        return itemService.showMenu(callback);
	}
}
