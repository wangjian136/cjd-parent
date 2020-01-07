package com.cjd.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjd.pojo.Content;
import com.cjd.service.ContentService;
import com.cjd.service.RedisService;
import com.cjd.util.EasyUIJsonUtils;

@RestController
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private RedisService redisService;

	@RequestMapping("/show_content")
	public Map<String, Object> show(@RequestParam int page,@RequestParam int rows,@RequestParam Long categoryId) {
		Page<Content> pages = contentService.selAllContent(page, rows, categoryId);
		return EasyUIJsonUtils.convertPageToJson(pages);
	}
	
	@RequestMapping("/save_content")
	public Map<String, Object> save(@RequestBody(required = false) Content content){
		Map<String, Object> result = new HashMap<String, Object>();
		Date currentDate = new Date();
		content.setCreated(currentDate);
		content.setUpdated(currentDate);
		Content con = contentService.insContent(content);
		if(con != null) {
			redisService.delZsetObject("cons", con.getId());
			redisService.setContent("cons", con);
			result.put("status", 200);
		}
		return result;
	}
	
	@RequestMapping("/delete_content")
	public Map<String, Object> delete(@RequestParam(name = "ids",required=true) List<String> ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		int num = contentService.delContentByIds(ids);
		if(num == ids.size()) {
			
			result.put("status", 200);
		}
		return result;
	}
}
