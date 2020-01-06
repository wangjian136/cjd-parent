package com.cjd.util;

import com.cjd.pojo.Item;
import com.cjd.pojo.ItemES;

public class ItemUtils {

	public static ItemES ItemChangeToES(Item item) {
		ItemES itemES = new ItemES(item.getId(), item.getTitle(), item.getSellPoint(), item.getPrice(), item.getNum(), item.getBarcode(), item.getImage(), item.getCid(), item.getStatus(), item.getCreated(), item.getUpdated(), null);
		String image = item.getImage();
		if(image != null && !"".equals(image)) {
			itemES.setImages(image.split(","));
		}
		return itemES;
	}
}
