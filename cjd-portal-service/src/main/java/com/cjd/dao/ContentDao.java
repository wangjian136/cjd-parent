package com.cjd.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjd.pojo.Content;

public interface ContentDao extends JpaRepository<Content, Long>{
	
}