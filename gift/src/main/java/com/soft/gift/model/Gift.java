package com.soft.gift.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name="t_gift")
public class Gift{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer collection;
	private Integer comment;
	private Integer stock;
	private Double price;
	private Timestamp on_shelf_time;
	private Integer sale_num;
	private String small_img;
	private String second_img;
	private String third_img;
	private Integer if_custom_made;
	private Integer category;
	private Integer status;
	
	public Gift() {
		super();
	}

	public Gift(String name, Integer collection, Integer comment, Integer stock, Double price, Timestamp on_shelf_time, Integer sale_num, String small_img, String second_img, String third_img, Integer if_custom_made, Integer category) {
		this.name = name;
		this.collection = collection;
		this.comment = comment;
		this.stock = stock;
		this.price = price;
		this.on_shelf_time = on_shelf_time;
		this.sale_num = sale_num;
		this.small_img = small_img;
		this.second_img = second_img;
		this.third_img = third_img;
		this.if_custom_made = if_custom_made;
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="name", nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name="collection", nullable=false)
	public Integer getCollection() {
		return collection;
	}

	public void setCollection(Integer collection) {
		this.collection = collection;
	}
	@Column(name="comment")
	public Integer getComment() {
		return comment;
	}

	public void setComment(Integer comment) {
		this.comment = comment;
	}
	@Column(name="stock")
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	@Column(name="price")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	@Column(name="on_shelf_time")
	public Timestamp getOn_shelf_time() {
		return on_shelf_time;
	}

	public void setOn_shelf_time(Timestamp on_shelf_time) {
		this.on_shelf_time = on_shelf_time;
	}

	public Integer getSale_num() {
		return sale_num;
	}

	@Column(name="sale_num")
	public void setSale_num(Integer sale_num) {
		this.sale_num = sale_num;
	}

	public String getSmall_img() {
		return small_img;
	}

	@Column(name="small_img",length=100)
	public void setSmall_img(String small_img) {
		this.small_img = small_img;
	}

	@Column(name="category")
	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	@Column(name="second_img",length = 100)
	public String getSecond_img() {
		return second_img;
	}

	public void setSecond_img(String second_img) {
		this.second_img = second_img;
	}

	@Column(name="third_img",length = 100)
	public String getThird_img() {
		return third_img;
	}

	public void setThird_img(String third_img) {
		this.third_img = third_img;
	}

	@Column(name="if_custom_made")
	public Integer getIf_custom_made() {
		return if_custom_made;
	}

	public void setIf_custom_made(Integer if_custom_made) {
		this.if_custom_made = if_custom_made;
	}

	@Column(name="status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Gift{" +
				"id=" + id +
				", name='" + name + '\'' +
				", collection=" + collection +
				", comment=" + comment +
				", stock=" + stock +
				", price=" + price +
				", on_shelf_time=" + on_shelf_time +
				", sale_num=" + sale_num +
				", small_img='" + small_img + '\'' +
				", second_img='" + second_img + '\'' +
				", third_img='" + third_img + '\'' +
				", if_custom_made='" + if_custom_made + '\'' +
				", category=" + category +
				'}';
	}
}
