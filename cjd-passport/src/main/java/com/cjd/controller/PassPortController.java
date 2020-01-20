package com.cjd.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjd.pojo.User;
import com.cjd.service.PassPortService;
import com.cjd.service.RedisService;
import com.cjd.util.CookieUtils;
import com.cjd.util.JsonUtils;
import com.cjd.util.MD5Utils;
import com.sun.org.apache.regexp.internal.recompile;

@Controller
public class PassPortController {

	@Autowired
	private PassPortService passPortService;
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/user/showLogin")
	public String showLoginPage(@RequestHeader("Referer") String url, Model model) {
		model.addAttribute("redirect", url);
		return "/login";
	}
	
	@RequestMapping("/user/showRegister")
	public String showRegisterPage() {
		return "/register";
	}
	
	@RequestMapping("/user/check/{value}/{type}")
	@ResponseBody
	public Object checkUser(@PathVariable String value,@PathVariable String type, @RequestParam(value = "callback",required = false) String callback) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> checkUser = passPortService.checkUser(value, type);
		map.put("status", "200");
		map.put("msg", "OK");
		if(checkUser != null && checkUser.size() > 0) {
			map.put("data", false);
		}else {
			map.put("data", true);
		}
		if(callback != null && !"".equals(callback)) {
			String jsonStr = "";
			try {
				jsonStr = JsonUtils.objectToJsonStr(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return callback + "("+jsonStr+");";
		}
		return map;
	}
	
	@RequestMapping("/user/login")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response, String username, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = passPortService.login(username, password);
		if(user != null) {
			map.put("status", "200");
			String uuid = UUID.randomUUID().toString();
			//当用户登录成功将用户信息存到redis中,有效期默认一天
			redisService.setUser(uuid, user);
			//产生cookie
			CookieUtils.setCookie(request, response, "TT_TOKEN", uuid, 60*60*24);
		}else {
			map.put("status", "500");
			map.put("msg", "用户名或密码错误！");
		}
		return map;
	}
	
	@RequestMapping("/user/register")
	@ResponseBody
	public Map<String, Object> register(HttpServletRequest request, HttpServletResponse response, User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		Date currDate = new Date();
		user.setCreated(currDate);
		user.setUpdated(currDate);
		String md5Password = MD5Utils.string2MD5(user.getPassword());
		user.setPassword(md5Password);
		User saveUser = passPortService.saveUser(user);
		if(saveUser.getId() != null) {
			map.put("status", "200");
		}else {
			map.put("status", "500");
		}
		return map;
	}
	
	
	@RequestMapping("/user/token/{_token}")
	@ResponseBody
	public Object token(@PathVariable String _token,@RequestParam String callback) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = redisService.getUser(_token);
		if(user != null) {
			user.setPassword("");
			map.put("status", "200");
			map.put("msg", "OK");
			map.put("data", user);
		}else {
			map.put("msg","fail");
		}
		if(callback != null && !"".equals(callback)) {
			String jsonStr = "";
			try {
				jsonStr = JsonUtils.objectToJsonStr(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return callback + "("+jsonStr+");";
		}
		return map;
	}
	
	
	@RequestMapping("/user/logout/{_token}")
	@ResponseBody
	public Object logout(HttpServletRequest request, HttpServletResponse response,@PathVariable String _token,@RequestParam String callback) {
		Map<String, Object> map = new HashMap<String, Object>();
		redisService.delStringObject(_token);
		CookieUtils.deleteCookie(request, response, "TT_TOKEN");
		map.put("status", "200");
		map.put("msg", "OK");
		if(callback != null && !"".equals(callback)) {
			String jsonStr = "";
			try {
				jsonStr = JsonUtils.objectToJsonStr(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return callback + "("+jsonStr+");";
		}
		return map;
	}
}
