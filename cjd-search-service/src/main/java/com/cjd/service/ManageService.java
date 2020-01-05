package com.cjd.service;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cjd.pojo.Item;

@FeignClient("manage-service")
public interface ManageService {
	
	@RequestMapping("/item/show_all_item")
	public List<Item> showAll();
}
