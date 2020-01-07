package com.cjd.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjd.pojo.Item;

@FeignClient("redis-service")
public interface RedisService {
	
	@RequestMapping("/redis/existsKey")
	public boolean existsKey(@RequestParam String key);

	@RequestMapping("/redis/getItem")
	public Item getItem(@RequestParam String key, @RequestParam Long id);
}
