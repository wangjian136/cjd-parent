package com.cjd.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tb_item_param_item")
public class ItemParamItem implements Serializable{

	private Long id;
	
	private Long itemId;
	
	private String paramData;
	
	private Date created;
	
    private Date updated;
    

    @Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "item_id")
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@Column(name = "param_data")
	public String getParamData() {
		return paramData;
	}

	public void setParamData(String paramData) {
		this.paramData = paramData;
	}

	@Column(name = "created")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "updated")
	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "ItemParamItem [id=" + id + ", itemId=" + itemId + ", paramData=" + paramData + ", created=" + created
				+ ", updated=" + updated + "]";
	}
    
}
