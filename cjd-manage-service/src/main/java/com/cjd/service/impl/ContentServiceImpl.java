package com.cjd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjd.dao.ContentDao;
import com.cjd.pojo.Content;
import com.cjd.service.ContentService;
import com.cjd.service.RedisService;

@Service
@Transactional
public class ContentServiceImpl implements ContentService{
	
	@Autowired
	private ContentDao contentDao;
	
	@Autowired
	private RedisService redisService;

	@Override
	public Page<Content> selAllContent(int page, int rows, Long categoryId) {
		Page<Content> pages = null;
		page = page - 1;
		Pageable pageable = PageRequest.of(page, rows);
		if(redisService.existsKey("cons")) {
			List<Content> contents = redisService.getContent("cons", 0L, -1L, false);
			List<Content> newContents = new ArrayList<Content>();
			if(categoryId != 0) {
				for (Content content2 : contents) {
					if(categoryId == content2.getCategoryId()) {
						newContents.add(content2);
					}
				}
			}else {
				newContents = contents;
			}
			pages = new PageImpl<Content>(newContents,pageable,newContents.size());
		}else {
			Content content = new Content();
			if(categoryId != 0) {
				content.setCategoryId(categoryId);
			}
		    Example<Content> example = Example.of(content);
			pages = contentDao.findAll(example, pageable);
			List<Content> contents = pages.getContent();
			for (Content content2 : contents) {
				redisService.setContent("cons", content2);
			}
		}
		
		return pages;
	}

	@Override
	public Content insContent(Content content) {
		Content con = contentDao.save(content);
		return con;
	}

	@Override
	public int delContentByIds(List<String> ids) {
		int index = 0;
		for (String id : ids) {
			contentDao.deleteById(Long.parseLong(id));
			redisService.delZsetObject("cons", Long.parseLong(id));
			index++;
		}
		return index;
	}
}
