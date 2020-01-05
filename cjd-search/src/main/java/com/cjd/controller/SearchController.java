package com.cjd.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjd.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;

	@RequestMapping("/search.html")
	public String search(@RequestParam String q,Model model,@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int rows) {
		Map<String, Object> map = searchService.search(q, page, rows);
		model.addAttribute("query", q);
		model.addAttribute("itemList", map.get("itemList"));
		model.addAttribute("totalPages", map.get("totalPages"));
		model.addAttribute("page",page);
		return "/search";
	}
}
