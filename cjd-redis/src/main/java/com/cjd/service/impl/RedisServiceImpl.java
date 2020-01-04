package com.cjd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import com.cjd.pojo.Content;
import com.cjd.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService{

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public boolean exists(String key) {
		return redisTemplate.hasKey(key);
	}

	@Override
	public void setZsetContent(String key, Content content) {
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Content.class));
		redisTemplate.opsForZSet().add(key, content, content.getId());
	}

	@Override
	public List<Content> getContents(String key, Long start, Long end, boolean isSort) {
		Set<Object> set = null;
		List<Content> result = new ArrayList<Content>();
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Content.class));
		if(isSort) {
			set = redisTemplate.opsForZSet().reverseRange(key, start, end);
		}else {
			set = redisTemplate.opsForZSet().range(key, start, end);
		}
		for (Object object : set) {
			Content content = (Content) object;
			result.add(content);
		}
		return result;
	}

	@Override
	public void delContent(String key, Long id) {
		redisTemplate.opsForZSet().removeRangeByScore(key, id, id);
	}

	

}
