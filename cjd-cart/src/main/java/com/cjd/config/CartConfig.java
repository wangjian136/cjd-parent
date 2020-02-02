package com.cjd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cjd.interceptor.CartInterceptor;

@Configuration
public class CartConfig implements WebMvcConfigurer {

	@Bean
    public HandlerInterceptor getCartInterceptor(){
        return new CartInterceptor();
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getCartInterceptor()).addPathPatterns("/**");
	}
}
