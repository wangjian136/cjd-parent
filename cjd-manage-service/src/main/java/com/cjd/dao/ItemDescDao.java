package com.cjd.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjd.pojo.ItemCat;
import com.cjd.pojo.ItemDesc;

public interface ItemDescDao extends JpaRepository<ItemDesc, Long>{
	
}