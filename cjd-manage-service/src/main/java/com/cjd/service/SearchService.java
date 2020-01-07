package com.cjd.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cjd.pojo.Item;

@FeignClient("search-service")
public interface SearchService {

	@RequestMapping("/es/ins")
	public void insItemES(@RequestBody(required = false) Item item);
	
	@RequestMapping("/es/update")
	public void updateItemES(@RequestBody(required = false) Item item);
}
