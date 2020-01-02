package com.cjd.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjd.dao.ItemCatDao;
import com.cjd.pojo.ItemCat;
import com.cjd.pojo.PortalMenu;
import com.cjd.pojo.PortalMenuNode;
import com.cjd.service.ItemService;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemCatDao itemCatDao;
	
	@Override
	public List<ItemCat> show(long pid) {
		return itemCatDao.findByParentId(pid);
	}

	@Override
	public PortalMenu showCatMenu() {
		//查询出所有一级菜单
		List<ItemCat> list = show((long) 0);
		PortalMenu pm =new PortalMenu();
		pm.setData(selAllMenu(list));
		return pm;
	}
	/**
	 * 最终返回结果所有查询到的结果.
	 */
	public List<Object> selAllMenu(List<ItemCat> list){
		List<Object> listNode = new ArrayList<>();
		for (ItemCat itemCat : list) {
			if(itemCat.getIsParent()){
				PortalMenuNode pmd  = new PortalMenuNode();
				pmd.setU("/products/"+itemCat.getId()+".html");
				pmd.setN("<a href='/products/"+itemCat.getId()+".html'>"+itemCat.getName()+"</a>");
				List<ItemCat> temp = show(itemCat.getId());
				pmd.setI(selAllMenu(temp));
				listNode.add(pmd);
			}else{
				listNode.add("/products/"+itemCat.getId()+".html|"+itemCat.getName());
			}
		}
		
		return listNode;
	}
}
