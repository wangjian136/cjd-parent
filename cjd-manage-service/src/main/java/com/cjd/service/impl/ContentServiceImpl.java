package com.cjd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjd.dao.CategoryDao;
import com.cjd.dao.ContentDao;
import com.cjd.dao.ItemCatDao;
import com.cjd.pojo.Category;
import com.cjd.pojo.Content;
import com.cjd.pojo.Item;
import com.cjd.pojo.ItemCat;
import com.cjd.service.CategoryService;
import com.cjd.service.ContentService;
import com.cjd.service.ItemCatService;

@Service
@Transactional
public class ContentServiceImpl implements ContentService{
	
	@Autowired
	private ContentDao contentDao;

	@Override
	public Page<Content> selAllContent(int page, int rows, Long categoryId) {
		Content content = new Content();
		if(categoryId != 0) {
			content.setCategoryId(categoryId);
		}
	    Example<Content> example = Example.of(content);
		page = page - 1;
		Pageable pageable = PageRequest.of(page, rows);
		Page<Content> pages = contentDao.findAll(example, pageable);
		return pages;
	}

}
