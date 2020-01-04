package com.cjd.redis;

import java.util.Date;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cjd.RedisServiceApp;
import com.cjd.pojo.Content;
import com.cjd.service.RedisService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedisServiceApp.class})
public class RedisTest {

	@Autowired
	private RedisService redisService;
	
	@Test
	public void testRedis() {
		Content content = new Content();
		content.setId((long) 1100);
		content.setCategoryId((long) 11);
		content.setContent("sss");
		content.setCreated(new Date());
		content.setUpdated(new Date());
		content.setPic("111");
		content.setTitle("111");
		content.setSubTitle("12");
		content.setTitleDesc("333");
		content.setUrl("baidu");
		redisService.setZsetContent("cons", content);
		System.out.println("========success========");
	}
	
	@Test
	public void testGetRedis() {
		/*Set<Object> contents = redisService.getContents("cons", (long)0, (long)3);
		for (Object object : contents) {
			Content content = (Content) object;
			System.out.println(content);
		}*/
		System.out.println("========success========");
		System.out.println(redisService.exists("cons"));
		System.out.println("========success========");
	}
	
	@Test
	public void testDelRedis() {
		System.out.println("========success========");
		System.out.println(redisService.exists("cons"));
		System.out.println("========success========");
	}
}
