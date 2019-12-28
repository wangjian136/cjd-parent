package com.cjd.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient("manage-service")
public interface ManageService {
	
	@RequestMapping("/item_cat/show_item_cat")
	public Map<String, Object> show(@RequestParam Long pid);
}
