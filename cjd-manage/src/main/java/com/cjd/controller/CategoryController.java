package com.cjd.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjd.pojo.Category;
import com.cjd.service.ManageService;

@Controller
public class CategoryController {

	@Autowired
	private ManageService manageService;
	
	/**
	 * 显示商品类目
	 * @param id
	 * @return
	 */
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List showCategory(@RequestParam(defaultValue="0") long id){
		Map<String, Object> map = manageService.showCategory(id);
		return (List) map.get("listTree");
	}
	

	/**
	 * 新增类目
	 * @param id
	 * @return
	 */
	@RequestMapping("/content/category/create")
	@ResponseBody
	public Map<String, Object> saveCategory(Category category){
		return manageService.saveCategory(category);
	}
	
	/**
	 * 修改类目
	 * @param category
	 * @return
	 */
	@RequestMapping("/content/category/update")
	@ResponseBody
	public Map<String, Object> updateCategory(Category category){
		return manageService.updateCategory(category);
	}
	
	/**
	 * 删除类目
	 * @param category
	 * @return
	 */
	@RequestMapping("/content/category/delete")
	@ResponseBody
	public Map<String, Object> deleteCategory(Category category){
		return manageService.deleteCategory(category);
	}
}
