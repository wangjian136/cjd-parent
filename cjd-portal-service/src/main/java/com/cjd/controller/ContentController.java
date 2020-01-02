package com.cjd.controller;

import java.util.ArrayList;
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
import com.cjd.util.EasyUIJsonUtils;
import com.cjd.util.JsonUtils;

@RestController
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;

	@RequestMapping("/show_big_content")
	public String showBigContent() throws Exception {
		List<Content> list = contentService.selContentByCount(6, true);
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		for (Content content : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("srcB", content.getPic2());
			map.put("height", 240);
			map.put("alt", "哎呦OK");
			map.put("width", 670);
			map.put("src", content.getPic());
			map.put("widthB", 550);
			map.put("href", content.getUrl());
			map.put("heightB", 240);
			result.add(map);
		}
		return JsonUtils.objectToJsonStr(result);
	}
}
