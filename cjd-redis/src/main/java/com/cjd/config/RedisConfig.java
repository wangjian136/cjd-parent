package com.cjd.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * SpringData Redis配置类
 * @author jwang
 *
 */
@Configuration
public class RedisConfig {
	
	//配置RedisStandaloneConfiguration
	@Bean
	@ConfigurationProperties(prefix="spring.redis")//该注解表示读取application.yml中指定开头键值对封装返回的对象
	public RedisStandaloneConfiguration getStandaloneConfiguration(){
		RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
		return standaloneConfig;
	}
	
	//配置Jedis连接工厂
	@Bean
	public JedisConnectionFactory getJedisConnectionFactory(RedisStandaloneConfiguration standaloneConfig){
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory(standaloneConfig);
		return connectionFactory;
	}
	
	//配置Redis模板类
	@Bean
	public RedisTemplate<String, Object> getRedisTemplate(JedisConnectionFactory connectionFactory){
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory);
		//设置redis序列化器
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
		return redisTemplate;
	}
}
