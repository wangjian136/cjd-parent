package com.cjd.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjd.pojo.Item;
import com.cjd.pojo.ItemES;

@FeignClient("search-service")
public interface SearchService {
	
	@RequestMapping("/es/find")
	public ItemES findItemES(@RequestParam Long id);

	@RequestMapping("/es/ins")
	public void insItemES(@RequestBody(required = false) Item item);
	
	@RequestMapping("/es/update")
	public void updateItemES(@RequestBody(required = false) Item item);
	
	@RequestMapping("/es/delete")
	public void deleteItemES(@RequestBody(required = false) Item item);
}
