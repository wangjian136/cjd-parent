package com.cjd.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjd.pojo.Content;

@FeignClient("redis-service")
public interface RedisService {

	@RequestMapping("/redis/existsKey")
	public boolean existsKey(@RequestParam String key);
	
	@RequestMapping("/redis/setContent")
	public void setContent(@RequestParam String key,@RequestBody(required = false) Content content);
	
	@RequestMapping("/redis/getContent")
	public List<Content> getContent(@RequestParam String key, @RequestParam Long start, @RequestParam Long end, @RequestParam boolean isSort);
	
	@RequestMapping("/redis/delContent")
	public void delContent(@RequestParam String key, @RequestParam Long id);
}
