package com.cjd.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjd.pojo.ItemCat;

public interface ItemCatDao extends JpaRepository<ItemCat, Long>{
	
	public List<ItemCat> findByParentId(Long parentId);
}