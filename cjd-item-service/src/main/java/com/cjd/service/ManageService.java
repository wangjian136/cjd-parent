package com.cjd.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjd.pojo.ItemCat;



@FeignClient("manage-service")
public interface ManageService {
	
	@RequestMapping("/item_cat/show_item_cat_only")
	public List<ItemCat> showItemCats(@RequestParam Long pid);
	
}
