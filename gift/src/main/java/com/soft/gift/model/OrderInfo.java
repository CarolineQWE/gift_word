package com.soft.gift.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="t_order_info")
public class OrderInfo {
	@Id
	private String id;
	private String order_id;
	private Integer num;
	private Integer gift_id;
	private Double price;
	
	public OrderInfo() {
		super();
	}

	public OrderInfo(String id,String order_id,  Integer num, Integer gift_id, Double price) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.num = num;
		this.gift_id = gift_id;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="order_id",length=30)
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}


	@Column(name="num")
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	

	@Column(name="gift_id")
	public Integer getGift_id() {
		return gift_id;
	}

	public void setGift_id(Integer gift_id) {
		this.gift_id = gift_id;
	}

	@Column(name="price")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
