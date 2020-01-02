package com.cjd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjd.dao.ContentDao;
import com.cjd.pojo.Content;
import com.cjd.service.ContentService;

@Service
@Transactional
public class ContentServiceImpl implements ContentService{
	
	@Autowired
	private ContentDao contentDao;

	@Override
	public List<Content> selContentByCount(int count, boolean isSort) {
		Pageable pageable = null;
		if(isSort) {
			pageable = PageRequest.of(0, count,Sort.by("updated").descending());
		}else {
			pageable = PageRequest.of(0, count);
		}
		Page<Content> conPage = contentDao.findAll(pageable);
		return conPage.getContent();
	}

}
