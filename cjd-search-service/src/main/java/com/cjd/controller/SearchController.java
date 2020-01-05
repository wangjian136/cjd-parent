package com.cjd.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjd.pojo.Content;
import com.cjd.pojo.Item;
import com.cjd.service.SearchService;

@RestController
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/init")
	public Map<String, Object> initElasticSearch(){
		Map<String, Object> result = new HashMap<String, Object>();
		searchService.initES();
		result.put("status", "success");
		return result;
	}
	
	@RequestMapping("/search")
	public Map<String, Object> search(@RequestParam String query, @RequestParam int page, @RequestParam int rows){
		return searchService.queryForES(query, page, rows);
	}
	
	@RequestMapping("/es/ins")
	public void insItemES(@RequestBody(required = false) Item item) {
		searchService.insItemES(item);
	}
}
