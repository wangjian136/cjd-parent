package com.cjd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjd.pojo.Content;
import com.cjd.service.RedisService;

@RestController
public class RedisController {

	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/redis/existsKey")
	public boolean existsHashKey(@RequestParam String key) {
		return redisService.exists(key);
	}
	
	@RequestMapping("/redis/setContent")
	public void setContent(@RequestParam String key,@RequestBody(required = false) Content content) {
		redisService.setZsetContent(key, content);
	}
	
	@RequestMapping("/redis/getContent")
	public List<Content> getContent(@RequestParam String key, @RequestParam Long start, @RequestParam Long end,@RequestParam boolean isSort) {
		return redisService.getContents(key, start, end, isSort);
	}
	
	@RequestMapping("/redis/delContent")
	public void delContent(@RequestParam String key, @RequestParam Long id) {
		redisService.delContent(key, id);
	}
}
