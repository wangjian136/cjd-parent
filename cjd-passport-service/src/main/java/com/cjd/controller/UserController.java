package com.cjd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjd.pojo.User;
import com.cjd.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/login")
	public User login(@RequestParam String username, @RequestParam String password) {
		return userService.selByUser(username, password);
	}
	
	@RequestMapping("/user/check")
	public List<User> checkUser(@RequestParam String value, @RequestParam String type) {
		return userService.checkUser(value, type);
	}
	
	@RequestMapping("/user/save")
	public User saveUser(@RequestBody(required = false) User user) {
		return userService.saveUser(user);
	}
}
