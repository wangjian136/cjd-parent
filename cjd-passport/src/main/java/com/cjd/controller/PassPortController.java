package com.cjd.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjd.pojo.User;
import com.cjd.service.PassPortService;

@Controller
public class PassPortController {

	@Autowired
	private PassPortService passPortService;
	
	@RequestMapping("/user/showLogin")
	public String showLoginPage(@RequestHeader("Referer") String url, Model model) {
		model.addAttribute("redirect", url);
		return "/login";
	}
	
	@RequestMapping("/user/showRegister")
	public String showRegisterPage() {
		return "/register";
	}
	
	@RequestMapping("/user/login")
	@ResponseBody
	public Map<String, Object> login(String username, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = passPortService.login(username, password);
		if(user != null) {
			map.put("status", "200");
		}else {
			map.put("status", "500");
			map.put("msg", "用户名或密码错误！");
		}
		return map;
	}
}
