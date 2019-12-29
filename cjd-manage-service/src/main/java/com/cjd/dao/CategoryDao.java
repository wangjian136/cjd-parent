package com.cjd.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjd.pojo.Category;

public interface CategoryDao extends JpaRepository<Category, Long>{
	
	public List<Category> findByParentIdAndStatus(Long parentId,Integer status);
}