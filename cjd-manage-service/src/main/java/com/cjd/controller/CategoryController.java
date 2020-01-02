package com.cjd.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjd.pojo.Category;
import com.cjd.pojo.EasyUiTree;
import com.cjd.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/show_category")
	public Map<String, Object> show(@RequestParam Long pid) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Category> list = categoryService.show(pid);
		List<EasyUiTree> listTree = new ArrayList<>();
		for (Category cat : list) {
			EasyUiTree tree = new EasyUiTree();
			tree.setId(cat.getId());
			tree.setText(cat.getName());
			tree.setState(cat.getIsParent()?"closed":"open");
			listTree.add(tree);
		}
		map.put("listTree", listTree);
		return map;
	}
	
	@RequestMapping("/save_category")
	public Map<String, Object> saveCategory(@RequestBody(required = false) Category category) {
		Map<String, Object> result = new HashMap<String, Object>();
		//判断父类目下不可拥有重名的分类
		boolean flag = categoryService.isRepeatName(category);
		if(flag) {
			result.put("status", 500);
			return result;
		}
		Date currentDate = new Date();
		category.setIsParent(false);
		category.setStatus(0);
		category.setSortOrder(1);
		category.setCreated(currentDate);
		category.setUpdated(currentDate);
		Category cat = categoryService.insCategory(category);
		if(cat != null) {
			result.put("status", 200);
			result.put("data", cat);
		}
		return result;
	}
	
	
	@RequestMapping("/update_category")
	public Map<String, Object> updateCategory(@RequestBody(required = false) Category category) {
		Map<String, Object> result = new HashMap<String, Object>();
		Category target = categoryService.selCategoryById(category.getId());
		target.setName(category.getName());
		//判断父类目下不可拥有重名的分类
		boolean flag = categoryService.isRepeatName(target);
		if(flag) {
			result.put("status", 500);
			return result;
		}
		Category cat = categoryService.updateCategory(target);
		if(cat != null) {
			result.put("status", 200);
		}
		return result;
	}
	
	
	@RequestMapping("/delete_category")
	public Map<String, Object> deleteCategory(@RequestBody(required = false) Category category) {
		Map<String, Object> result = new HashMap<String, Object>();
		Category target = categoryService.selCategoryById(category.getId());
		Category cat = categoryService.delCategory(target);
		if(cat != null) {
			result.put("status", 200);
		}
		return result;
	}

}
