package com.cjd.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("portal-service")
public interface PortalService {

	@RequestMapping(value = "/itemcat/all",produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	public Object showMenu(@RequestParam String callback) throws Exception;
}
