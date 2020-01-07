package com.cjd.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjd.pojo.ItemDesc;



@FeignClient("manage-service")
public interface ManageService {
	
	@RequestMapping("/item_desc/get_item_desc")
	public ItemDesc getItemDesc(@RequestParam Long id);
	
}
