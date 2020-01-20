package com.cjd.service;

import java.util.List;

import com.cjd.pojo.User;

public interface UserService {

	public User selByUser(String username, String password);
	
	public List<User> checkUser(String value,String type);
	
	public User saveUser(User user);
}
