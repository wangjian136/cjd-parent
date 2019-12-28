package com.jd.test;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cjd.ManageApp;
import com.cjd.pojo.Item;
import com.cjd.service.ManageService;
import com.cjd.util.IDUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ManageApp.class})
public class ManageTest {
	
	@Autowired
	private ManageService itemService;
	
	@Test
	public void testSelAllItem() {
		
	}
}
