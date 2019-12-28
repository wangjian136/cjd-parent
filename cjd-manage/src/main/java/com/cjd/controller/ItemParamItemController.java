package com.cjd.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjd.pojo.ItemParamItem;
import com.cjd.service.ManageService;

@Controller
public class ItemParamItemController {

	@Autowired
	private ManageService manageService;
	
	@RequestMapping("/rest/item/param/item/query/{id}")
	@ResponseBody
	public Map<String, Object> showItemParam(@PathVariable Long id){
		Map<String, Object> result = new HashMap<String, Object>();
		ItemParamItem param = manageService.getItemParamByItem(id);
		if(param != null) {
			result.put("data", param);
			result.put("status", 200);
		}
		return result;
	}
}
