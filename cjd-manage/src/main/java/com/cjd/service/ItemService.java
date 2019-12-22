package com.cjd.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("eureka-provider")
public interface ItemService {
	
	@RequestMapping("/item/show_item")
	public Map<String, Object> selAllItem(@RequestParam int page, @RequestParam int rows);
	
	@RequestMapping("/item/delete_item")
	public Map<String, Object> deleteItem(@RequestParam(name = "ids",required=true) List<String> ids);
}
