package com.cjd.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjd.pojo.ItemES;

@FeignClient("item-service")
public interface ItemService {

	@RequestMapping("/itemcat/all")
	public String showMenu(@RequestParam String callback) throws Exception;
	
	@RequestMapping("/item/show")
	public ItemES showItem(@RequestParam long id);
}
