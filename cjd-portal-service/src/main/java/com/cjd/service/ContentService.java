package com.cjd.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cjd.pojo.Content;
import com.cjd.pojo.Item;
import com.cjd.pojo.ItemDesc;

public interface ContentService {
	
	//显示最近的前n个
	public List<Content> selContentByCount(int count, boolean isSort);
}
