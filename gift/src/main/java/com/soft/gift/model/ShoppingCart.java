package com.soft.gift.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Table(name="t_shopping_cart")
public class ShoppingCart {
	@Id
	@Column(name="id",length=30)
	private String id;
	private String account;
	private Integer gift_id;
	private Integer num;
	private Timestamp add_time;
	private Double price;
	
	public ShoppingCart() {
		super();
	}

	public ShoppingCart(String id,String account, Integer gift_id, Integer num, Timestamp add_time,
			Double price) {
		super();
		this.id = id;
		this.account = account;
		this.gift_id = gift_id;
		this.num = num;
		this.add_time = add_time;
		this.price = price;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="account",length=30)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name="gift_id")
	public Integer getGift_id() {
		return gift_id;
	}

	public void setGift_id(Integer gift_id) {
		this.gift_id = gift_id;
	}


	@Column(name="num")
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name="add_time")
	public Timestamp getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Timestamp add_time) {
		this.add_time = add_time;
	}

	@Column(name="price")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", account=" + account + ", gift_id=" + gift_id + ", num=" + num
				+ ", add_time=" + add_time + ", price=" + price + "]";
	}
	
	
}
