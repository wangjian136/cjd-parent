package com.cjd.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;

public class EasyUIJsonUtils {

	public static Map<String, Object> convertPageToJson(Page<?> pages) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", pages.getTotalElements());
		result.put("rows", pages.getContent());
		return result;
	}
}
