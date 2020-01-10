package com.cjd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjd.dao.UserDao;
import com.cjd.pojo.User;
import com.cjd.service.UserService;
import com.cjd.util.MD5Utils;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User selByUser(String username, String password) {
		String md5Password = MD5Utils.string2MD5(password);
		return userDao.selUserNamePassWord(username, md5Password);
	}

}
