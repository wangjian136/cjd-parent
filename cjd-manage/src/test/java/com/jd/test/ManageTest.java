package com.jd.test;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cjd.ManageApp;
import com.cjd.service.ItemService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ManageApp.class})
public class ManageTest {
	
	@Autowired
	private ItemService itemService;
	
	@Test
	public void testSelAllItem() {
		/*Map<String, Object> result = itemService.selAllItem(1, 20);
		System.out.println(result);*/
	}
}
