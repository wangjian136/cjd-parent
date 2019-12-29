package com.cjd.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjd.pojo.Content;
import com.cjd.service.ContentService;
import com.cjd.util.EasyUIJsonUtils;

@RestController
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;

	@RequestMapping("/show_content")
	public Map<String, Object> show(@RequestParam int page,@RequestParam int rows,@RequestParam Long categoryId) {
		Page<Content> pages = contentService.selAllContent(page, rows, categoryId);
		return EasyUIJsonUtils.convertPageToJson(pages);
	}

}
