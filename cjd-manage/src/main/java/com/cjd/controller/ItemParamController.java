package com.cjd.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjd.pojo.ItemDesc;
import com.cjd.pojo.ItemParam;
import com.cjd.service.ItemService;

@Controller
public class ItemParamController {

	@Autowired
	private ItemService itemService;
	
	/**
	 * 分页显示商品规格
	 */
	@RequestMapping("/item/param/list")
	@ResponseBody
	public Map<String, Object> show(int page, int rows){
		return itemService.selAllItemParam(page, rows);
	}
	
	/**
	 * 批量删除规格
	 * @param ids
	 * @return
	 */
	@RequestMapping("/item/param/delete")
	@ResponseBody
	public Map<String, Object> delete(String ids){
		String[] id_array = ids.split(",");
		return itemService.deleteItemParam(Arrays.asList(id_array));
	}
	
	@RequestMapping("/item/param/query/itemcatid/{id}")
	@ResponseBody
	public Map<String, Object> showItemParam(@PathVariable Long id){
		Map<String, Object> result = new HashMap<String, Object>();
		ItemParam param = itemService.getItemParamByCat(id);
		if(param != null) {
			result.put("data", param);
			result.put("status", 200);
		}
		return result;
	}
	
	@RequestMapping("/item/param/save/{catId}")
	@ResponseBody
	public Map<String, Object> save(ItemParam param,@PathVariable long catId){
		Date curreDate = new Date();
		param.setItemCatId(catId);
		param.setCreated(curreDate);
		param.setUpdated(curreDate);
		return itemService.saveItemParam(param);
	}
}
