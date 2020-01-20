package com.cjd.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjd.pojo.User;

@FeignClient("passport-service")
public interface PassPortService {

	@RequestMapping("/user/login")
	public User login(@RequestParam String username, @RequestParam String password);
	
	@RequestMapping("/user/check")
	public List<User> checkUser(@RequestParam String value, @RequestParam String type);
	
	@RequestMapping("/user/save")
	public User saveUser(@RequestBody(required = false) User user);
}
