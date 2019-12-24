package com.cjd.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjd.pojo.Item;
import com.cjd.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	/**
	 * 分页显示商品
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public Map<String, Object> show(int page, int rows){
		return itemService.selAllItem(page, rows);
	}
	
	/**
	 * 显示商品修改
	 * @return
	 */
	@RequestMapping("/rest/page/item-edit")
	public String showItemEdit(){
		return "/item-edit";
	}
	
	/**
	 * 商品删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/rest/item/delete")
	@ResponseBody
	public Map<String, Object> delete(String ids){
		String[] id_array = ids.split(",");
		return itemService.deleteItem(Arrays.asList(id_array));
	}
	
	/**
	 * 商品下架
	 * @param ids
	 * @return
	 */
	@RequestMapping("/rest/item/instock")
	@ResponseBody
	public Map<String, Object> instock(String ids){
		String[] id_array = ids.split(",");
		return itemService.instockItem(Arrays.asList(id_array));
	}
	
	/**
	 * 商品上架
	 * @param ids
	 * @return
	 */
	@RequestMapping("/rest/item/reshelf")
	@ResponseBody
	public Map<String, Object> reshelf(String ids){
		String[] id_array = ids.split(",");
		return itemService.reshelfItem(Arrays.asList(id_array));
	}
	
	/**
	 * 商品保存
	 * @param item
	 * @param desc
	 * @return
	 */
	@RequestMapping("/item/save")
	@ResponseBody
	public Map<String, Object> saveItem(Item item, String desc){
		return itemService.save(item, desc);
	}
}
