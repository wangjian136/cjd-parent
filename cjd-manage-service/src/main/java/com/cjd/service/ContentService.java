package com.cjd.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cjd.pojo.Content;
import com.cjd.pojo.Item;
import com.cjd.pojo.ItemDesc;

public interface ContentService {
	
	//查询全部分类内容
	public Page<Content> selAllContent(int page, int rows, Long categoryId);
	
	public Content insContent(Content content);
	
	//删除内容管理
	public int delContentByIds(List<String> ids);
	
}
