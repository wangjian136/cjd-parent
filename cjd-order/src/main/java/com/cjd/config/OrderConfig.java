package com.cjd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cjd.interceptor.OrderInterceptor;

@Configuration
public class OrderConfig implements WebMvcConfigurer {

	@Bean
    public HandlerInterceptor getOrderInterceptor(){
        return new OrderInterceptor();
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getOrderInterceptor()).addPathPatterns("/**");
	}
}
