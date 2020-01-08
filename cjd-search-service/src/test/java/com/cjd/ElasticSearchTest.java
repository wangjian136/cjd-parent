package com.cjd;
 
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.cjd.pojo.ItemES;
import com.cjd.service.SearchService;
 
@RunWith(SpringRunner.class)
@SpringBootTest(classes=SearchServiceApp.class)
public class ElasticSearchTest {
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;
	
	@Test
    public void createIndex(){
		// 创建索引，会根据Item类的@Document注解信息来创建
		elasticsearchRestTemplate.createIndex(ItemES.class);
		 // 配置映射，会根据Item类中的@Id、@Field等字段来自动完成映射
		elasticsearchRestTemplate.putMapping(ItemES.class);
 
    }
	
	@Test
    public void testQuerty(){
		Map<String, Object> queryForES = searchService.queryForES("手机", 0, 20);
		System.out.println(queryForES);
    }
	
}