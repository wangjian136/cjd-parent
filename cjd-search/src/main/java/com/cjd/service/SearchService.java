package com.cjd.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("search-service")
public interface SearchService {

	@RequestMapping("/search")
	public Map<String, Object> search(@RequestParam String query, @RequestParam int page, @RequestParam int rows);
}
