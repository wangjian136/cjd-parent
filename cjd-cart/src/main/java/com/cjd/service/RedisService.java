package com.cjd.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjd.pojo.Item;
import com.cjd.pojo.User;

@FeignClient("redis-service")
public interface RedisService {

	@RequestMapping("/redis/setUser")
	public void setUser(@RequestParam String key,@RequestBody(required = false) User user);
	
	@RequestMapping("/redis/getUser")
	public User getUser(@RequestParam(required = false) String key);
	
	@RequestMapping("/redis/delStringObject")
	public void delStringObject(@RequestParam String key);
	
	@RequestMapping("/redis/setItem")
	public void setItem(@RequestParam String key,@RequestBody(required = false) Item item);
	
	@RequestMapping("/redis/getItem")
	public Item getItem(@RequestParam String key, @RequestParam Long id);
	
	@RequestMapping("/redis/existsKey")
	public boolean existsHashKey(@RequestParam String key);
	
	@RequestMapping("/redis/getItems")
	public List<Item> getItems(@RequestParam String key, @RequestParam Long start, @RequestParam Long end,@RequestParam boolean isSort);
}
