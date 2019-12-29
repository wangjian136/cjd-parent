package com.cjd.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjd.pojo.Category;
import com.cjd.pojo.Item;
import com.cjd.pojo.ItemDesc;
import com.cjd.pojo.ItemParam;
import com.cjd.pojo.ItemParamItem;



@FeignClient("manage-service")
public interface ManageService {
	
	@RequestMapping("/item/show_item")
	public Map<String, Object> selAllItem(@RequestParam int page, @RequestParam int rows);
	
	@RequestMapping("/item/delete_item")
	public Map<String, Object> deleteItem(@RequestParam(name = "ids",required=true) List<String> ids);
	
	@RequestMapping("/item/instock_item")
	public Map<String, Object> instockItem(@RequestParam(name = "ids",required=true) List<String> ids);
	
	@RequestMapping("/item/reshelf_item")
	public Map<String, Object> reshelfItem(@RequestParam(name = "ids",required=true) List<String> ids);
	
	@RequestMapping("/item_cat/show_item_cat")
	public Map<String, Object> show(@RequestParam Long pid);
	
	@RequestMapping("/item_cat/get_item_cat")
	public Map<String, Object> getItemCat(@RequestParam Long id);
	
	@RequestMapping("/item/save_item")
	public Map<String, Object> save(@RequestBody(required = false) Item item, @RequestParam String desc, @RequestParam String paramData) throws Exception;
	
	@RequestMapping("/item_desc/get_item_desc")
	public ItemDesc getItemDesc(@RequestParam Long id);
	
	@RequestMapping("/item_param/show_item_param")
	public Map<String, Object> selAllItemParam(@RequestParam int page,@RequestParam int rows);
	
	@RequestMapping("/item_param/delete_item_param")
	public Map<String, Object> deleteItemParam(@RequestParam(name = "ids",required=true) List<String> ids);
	
	@RequestMapping("/item_param/get_item_param_cat")
	public ItemParam getItemParamByCat(@RequestParam Long catId);
	
	@RequestMapping("/item_param/save_item_param")
	public Map<String, Object> saveItemParam(@RequestBody(required = false) ItemParam itemParam);
	
	@RequestMapping("/item_param_item/get_item_param_item")
	public ItemParamItem getItemParamByItem(@RequestParam Long itemId);
	
	@RequestMapping("/category/show_category")
	public Map<String, Object> showCategory(@RequestParam Long pid);
	
	@RequestMapping("/category/save_category")
	public Map<String, Object> saveCategory(@RequestBody(required = false) Category category);
	
	@RequestMapping("/category/update_category")
	public Map<String, Object> updateCategory(@RequestBody(required = false) Category category);
	
	@RequestMapping("/category/delete_category")
	public Map<String, Object> deleteCategory(@RequestBody(required = false) Category category);
}
