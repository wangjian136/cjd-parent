package com.jd.test;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cjd.PassPortServiceApp;
import com.cjd.service.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PassPortServiceApp.class})
public class ServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testSel() {
		System.out.println(userService.selByUser("1", "2"));
	}
}
