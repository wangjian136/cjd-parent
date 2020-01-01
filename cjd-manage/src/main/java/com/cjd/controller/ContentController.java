package com.cjd.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjd.pojo.Content;
import com.cjd.service.ManageService;

@Controller
public class ContentController {

	@Autowired
	private ManageService manageService;
	
	@RequestMapping("/content/query/list")
	@ResponseBody
	public Map<String, Object> show(int page, int rows, Long categoryId){
		return manageService.showContent(page, rows, categoryId);
	}
	
	@RequestMapping({"/content/save","/rest/content/edit"})
	@ResponseBody
	public Map<String, Object> save(Content content){
		return manageService.save(content);
	}
	
	@RequestMapping("/content/delete")
	@ResponseBody
	public Map<String, Object> delete(String ids){
		String[] id_array = ids.split(",");
		return manageService.deleteContent(Arrays.asList(id_array));
	}
}
