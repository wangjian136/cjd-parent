package com.cjd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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

	@Override
	public List<User> checkUser(String value,String type) {
		User user = new User();
		switch (type) {
		case "1":
			user.setUsername(value);
			break;
		case "2":
			user.setPhone(value);
			break;
		case "3":
			user.setEmail(value);
			break;
		default:
			break;
		}
		Example<User> example = Example.of(user);
		return userDao.findAll(example);
	}

	@Override
	public User saveUser(User user) {
		return userDao.save(user);
	}

}
