package com.cjd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjd.dao.CategoryDao;
import com.cjd.dao.ItemCatDao;
import com.cjd.pojo.Category;
import com.cjd.pojo.ItemCat;
import com.cjd.service.CategoryService;
import com.cjd.service.ItemCatService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<Category> show(long pid) {
		return categoryDao.findByParentIdAndStatus(pid,1);
	}

	@Override
	public Category insCategory(Category category) {
		//修改父分类的isParent
		Category parent = selCategoryById(category.getParentId());
		parent.setIsParent(true);
		categoryDao.save(parent);
		Category cat = categoryDao.save(category);
		return cat;
	}

	@Override
	public Category selCategoryById(Long id) {
		return categoryDao.findById(id).get();
	}

	@Override
	public Category updateCategory(Category category) {
		Category cat = categoryDao.save(category);
		return cat;
	}

	@Override
	public Category delCategory(Category category) {
		category.setStatus(2);//删除
		Category cat = categoryDao.save(category);
		//判断父类是否没有值
		List<Category> categories = show(category.getParentId());
		if(categories != null && categories.size() == 0) {
			//修改父分类的isParent
			Category parent = selCategoryById(category.getParentId());
			parent.setIsParent(false);
			categoryDao.save(parent);
		}
		return cat;
	}

	@Override
	public boolean isRepeatName(Category category) {
		boolean flag = false;
		//判断父类目下不可拥有重名的分类
		List<Category> childs = show(category.getParentId());
		for(Category ca : childs) {
			if(ca.getName().equals(category.getName())) {
				flag = true;
				break;
			}
		}
		return flag;
	}

}
