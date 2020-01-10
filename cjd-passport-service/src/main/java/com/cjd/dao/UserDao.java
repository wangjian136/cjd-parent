package com.cjd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cjd.pojo.User;


public interface UserDao extends JpaRepository<User, Long>{
	
	@Query(" from User where username=:username and password=:password ")
	public User selUserNamePassWord(@Param(value = "username") String username, @Param(value = "password") String password);
}
