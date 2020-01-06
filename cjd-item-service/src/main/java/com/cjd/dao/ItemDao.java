package com.cjd.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjd.pojo.Item;

public interface ItemDao extends JpaRepository<Item, Long>{
	
}