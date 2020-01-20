package com.cjd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjd.pojo.Content;
import com.cjd.pojo.Item;
import com.cjd.pojo.ItemDesc;
import com.cjd.pojo.User;
import com.cjd.service.RedisService;

@RestController
public class RedisController {

	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/redis/existsKey")
	public boolean existsHashKey(@RequestParam String key) {
		return redisService.exists(key);
	}
	
	@RequestMapping("/redis/setUser")
	public void setUser(@RequestParam String key,@RequestBody(required = false) User user) {
		redisService.setStringUser(key, user);
	}
	
	@RequestMapping("/redis/setContent")
	public void setContent(@RequestParam String key,@RequestBody(required = false) Content content) {
		redisService.setZsetContent(key, content);
	}
	
	@RequestMapping("/redis/setItem")
	public void setItem(@RequestParam String key,@RequestBody(required = false) Item item) {
		redisService.setZsetItem(key, item);
	}
	
	@RequestMapping("/redis/setItemDesc")
	public void setItemDesc(@RequestParam String key,@RequestBody(required = false) ItemDesc itemDesc) {
		redisService.setHashItemDesc(key, itemDesc);
	}
	
	@RequestMapping("/redis/getUser")
	public User getUser(@RequestParam String key) {
		return redisService.getStringUser(key);
	}
	
	@RequestMapping("/redis/getItem")
	public Item getItem(@RequestParam String key, @RequestParam Long id) {
		return redisService.getZsetItem(key, id);
	}
	
	@RequestMapping("/redis/getItemDesc")
	public ItemDesc getItemDesc(@RequestParam String key, @RequestParam String subKey) {
		return redisService.getHashItemDesc(key, subKey);
	}
	
	@RequestMapping("/redis/getContent")
	public List<Content> getContent(@RequestParam String key, @RequestParam Long start, @RequestParam Long end,@RequestParam boolean isSort) {
		return redisService.getContents(key, start, end, isSort);
	}
	
	@RequestMapping("/redis/getItems")
	public List<Item> getItems(@RequestParam String key, @RequestParam Long start, @RequestParam Long end,@RequestParam boolean isSort) {
		return redisService.getItems(key, start, end, isSort);
	}
	
	@RequestMapping("/redis/delZsetObject")
	public void delZsetObject(@RequestParam String key, @RequestParam Long id) {
		redisService.delZsetObject(key, id);
	}
	
	@RequestMapping("/redis/delHashObject")
	public void delHashObject(@RequestParam String key, @RequestBody(required = false) String ... hashKeys) {
		redisService.delHashObject(key, hashKeys);
	}
	
	@RequestMapping("/redis/delStringObject")
	public void delStringObject(@RequestParam String key) {
		redisService.delStringObject(key);
	}
}
