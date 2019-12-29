package com.cjd.service;

import java.util.List;

import com.cjd.pojo.Category;

public interface CategoryService {
	
	public List<Category> show(long pid);
	
	public Category insCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public Category delCategory(Category category);
	
	public Category selCategoryById(Long id);
	
	public boolean isRepeatName(Category category);
}
