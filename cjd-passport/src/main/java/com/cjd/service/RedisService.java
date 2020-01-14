package com.cjd.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjd.pojo.User;

@FeignClient("redis-service")
public interface RedisService {

	@RequestMapping("/redis/setUser")
	public void setUser(@RequestParam String key,@RequestBody(required = false) User user);
	
	@RequestMapping("/redis/getUser")
	public User getUser(@RequestParam String key);
}
