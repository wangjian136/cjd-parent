package com.cjd.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 商品实体类
 * @author jwang
 *
 */
@Document(indexName="item",type="_doc",shards = 2, replicas = 0)
public class ItemES implements Serializable{
	@Field(name="id",type = FieldType.Long)
    private Long id;
	@Field(name="title",analyzer = "ik_max_word",searchAnalyzer = "ik_max_word", type = FieldType.Text)
    private String title;
	@Field(name="sellPoint",analyzer = "ik_max_word",searchAnalyzer = "ik_max_word" ,type = FieldType.Text)
    private String sellPoint;
	@Field(name="price",type = FieldType.Long)
    private Long price;
	@Field(name="num",type = FieldType.Integer)
    private Integer num;
	@Field(name="barcode",type = FieldType.Keyword)
    private String barcode;
	@Field(name="image",type = FieldType.Keyword)
    private String image;
	@Field(name="cid",type = FieldType.Long)
    private Long cid;
	@Field(name="status",type = FieldType.Byte)
    private Byte status;
	@Field(name="created",type = FieldType.Date)
    private Date created;
	@Field(name="updated",type = FieldType.Date)
    private Date updated;
	@Field(name="images",type = FieldType.Keyword)
	private String [] images;

    public ItemES() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ItemES(Long id, String title, String sellPoint, Long price, Integer num, String barcode, String image,
			Long cid, Byte status, Date created, Date updated, String[] images) {
		super();
		this.id = id;
		this.title = title;
		this.sellPoint = sellPoint;
		this.price = price;
		this.num = num;
		this.barcode = barcode;
		this.image = image;
		this.cid = cid;
		this.status = status;
		this.created = created;
		this.updated = updated;
		this.images = images;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint == null ? null : sellPoint.trim();
    }
    
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

	@Override
	public String toString() {
		return "Item [id=" + id + ", title=" + title + ", sellPoint=" + sellPoint + ", price=" + price + ", num=" + num
				+ ", barcode=" + barcode + ", image=" + image + ", cid=" + cid + ", status=" + status + ", created="
				+ created + ", updated=" + updated + "]";
	}
}