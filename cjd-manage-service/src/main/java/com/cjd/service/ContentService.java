package com.cjd.service;

import org.springframework.data.domain.Page;

import com.cjd.pojo.Content;

public interface ContentService {
	
	//查询全部分类内容
	public Page<Content> selAllContent(int page, int rows, Long categoryId);
}
