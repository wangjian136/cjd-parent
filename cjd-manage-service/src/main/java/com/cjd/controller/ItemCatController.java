package com.cjd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjd.pojo.EasyUiTree;
import com.cjd.pojo.ItemCat;
import com.cjd.service.ItemCatService;

@RestController
@RequestMapping("/item_cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;

	@RequestMapping("/show_item_cat")
	public Map<String, Object> show(@RequestParam Long pid) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ItemCat> list = itemCatService.show(pid);
		List<EasyUiTree> listTree = new ArrayList<>();
		for (ItemCat cat : list) {
			EasyUiTree tree = new EasyUiTree();
			tree.setId(cat.getId());
			tree.setText(cat.getName());
			tree.setState(cat.getIsParent()?"closed":"open");
			listTree.add(tree);
		}
		map.put("listTree", listTree);
		return map;
	}
	
	@RequestMapping("/get_item_cat")
	public Map<String, Object> getItemCat(@RequestParam Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		ItemCat cat = itemCatService.getItemCat(id);
		EasyUiTree tree = new EasyUiTree();
		tree.setId(cat.getId());
		tree.setText(cat.getName());
		tree.setState(cat.getIsParent()?"closed":"open");
		map.put("cat", tree);
		return map;
	}
}
