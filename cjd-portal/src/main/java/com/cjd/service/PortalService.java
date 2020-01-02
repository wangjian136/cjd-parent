package com.cjd.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("portal-service")
public interface PortalService {
	
	@RequestMapping("/content/show_big_content")
	public String selBigContent() throws Exception;
	
}
