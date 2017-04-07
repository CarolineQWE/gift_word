package com.soft.gift.model;

import java.sql.Timestamp;

public class LargeShopCart {
	//购物车
	private String sc_id;
	private String account;
	private Integer gift_id;
	private Integer num;
	private Timestamp add_time;
	private Double sc_price;
	//礼物
	private String name;
	private Double price;
	private String small_img;
	private Integer stock;
	//属性
	public LargeShopCart(String sc_id, String account, Integer gift_id, Integer num, Timestamp add_time,
			Double sc_price, String name, Double price, String small_img, Integer stock) {
		super();
		this.sc_id = sc_id;
		this.account = account;
		this.gift_id = gift_id;
		this.num = num;
		this.add_time = add_time;
		this.sc_price = sc_price;
		this.name = name;
		this.price = price;
		this.small_img = small_img;
		this.stock = stock;
	}
	public LargeShopCart() {
		super();
	}
	public String getSc_id() {
		return sc_id;
	}
	public void setSc_id(String sc_id) {
		this.sc_id = sc_id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Integer getGift_id() {
		return gift_id;
	}
	public void setGift_id(Integer gift_id) {
		this.gift_id = gift_id;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Timestamp getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Timestamp add_time) {
		this.add_time = add_time;
	}
	public Double getSc_price() {
		return sc_price;
	}
	public void setSc_price(Double sc_price) {
		this.sc_price = sc_price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getSmall_img() {
		return small_img;
	}
	public void setSmall_img(String small_img) {
		this.small_img = small_img;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "LargeShopCart [sc_id=" + sc_id + ", account=" + account + ", gift_id=" + gift_id + ", num=" + num
				+ ", add_time=" + add_time + ", sc_price=" + sc_price + ", name=" + name + ", price=" + price
				+ ", small_img=" + small_img + ", stock=" + stock + "]";
	}
	
	
}
