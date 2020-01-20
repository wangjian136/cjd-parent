package com.cjd.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjd.pojo.Content;
import com.cjd.pojo.Item;
import com.cjd.pojo.ItemDesc;

@FeignClient("redis-service")
public interface RedisService {

	@RequestMapping("/redis/existsKey")
	public boolean existsKey(@RequestParam String key);
	
	@RequestMapping("/redis/setContent")
	public void setContent(@RequestParam String key,@RequestBody(required = false) Content content);
	
	@RequestMapping("/redis/setItem")
	public void setItem(@RequestParam String key,@RequestBody(required = false) Item item);
	
	@RequestMapping("/redis/setItemDesc")
	public void setItemDesc(@RequestParam String key,@RequestBody(required = false) ItemDesc itemDesc);
	
	@RequestMapping("/redis/getContent")
	public List<Content> getContent(@RequestParam String key, @RequestParam Long start, @RequestParam Long end, @RequestParam boolean isSort);
	
	@RequestMapping("/redis/getItemDesc")
	public ItemDesc getItemDesc(@RequestParam String key, @RequestParam String subKey);
	
	@RequestMapping("/redis/getItem")
	public Item getItem(@RequestParam String key, @RequestParam Long id);
	
	@RequestMapping("/redis/getItems")
	public List<Item> getItems(@RequestParam String key, @RequestParam Long start, @RequestParam Long end,@RequestParam boolean isSort);
	
	@RequestMapping("/redis/delZsetObject")
	public void delZsetObject(@RequestParam String key, @RequestParam Long id);
	
	@RequestMapping("/redis/delHashObject")
	public void delHashObject(@RequestParam String key, @RequestBody(required = false) String ... hashKeys);
}
