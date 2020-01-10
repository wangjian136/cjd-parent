package com.cjd.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjd.pojo.User;

@FeignClient("passport-service")
public interface PassPortService {

	@RequestMapping("/user/login")
	public User login(@RequestParam String username, @RequestParam String password);
}
