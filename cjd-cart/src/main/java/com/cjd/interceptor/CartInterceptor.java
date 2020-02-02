package com.cjd.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.cjd.pojo.User;
import com.cjd.service.RedisService;
import com.cjd.util.CookieUtils;

public class CartInterceptor implements HandlerInterceptor {
	
	@Autowired
	private RedisService redisService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		User user = null;
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		if(token != null) {
			user = redisService.getUser(token);
		}
		if(user != null) {
			return true;
		}
		String num = request.getParameter("num");
		response.sendRedirect("http://localhost:8084/user/showLogin?interUrl="+request.getRequestURL()+"%3Fnum="+num);
		return false;
	}
}
