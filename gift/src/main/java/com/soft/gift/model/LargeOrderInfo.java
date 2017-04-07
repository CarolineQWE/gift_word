package com.soft.gift.model;

public class LargeOrderInfo{
	//订单详情
	private String orderinfo_id;
	private Double orderinfo_price;
	private Integer orderinfo_num;
	//礼物
	private Integer gift_id;
	private String gift_name;
	private String small_img;
	private Double gift_price;
	public LargeOrderInfo(String orderinfo_id, Double orderinfo_price, Integer orderinfo_num, Integer gift_id,
			String gift_name, String small_img, Double gift_price) {
		super();
		this.orderinfo_id = orderinfo_id;
		this.orderinfo_price = orderinfo_price;
		this.orderinfo_num = orderinfo_num;
		this.gift_id = gift_id;
		this.gift_name = gift_name;
		this.small_img = small_img;
		this.gift_price = gift_price;
	}
	public LargeOrderInfo() {
		super();
	}
	public String getOrderinfo_id() {
		return orderinfo_id;
	}
	public void setOrderinfo_id(String orderinfo_id) {
		this.orderinfo_id = orderinfo_id;
	}
	public Double getOrderinfo_price() {
		return orderinfo_price;
	}
	public void setOrderinfo_price(Double orderinfo_price) {
		this.orderinfo_price = orderinfo_price;
	}
	public Integer getOrderinfo_num() {
		return orderinfo_num;
	}
	public void setOrderinfo_num(Integer orderinfo_num) {
		this.orderinfo_num = orderinfo_num;
	}
	public Integer getGift_id() {
		return gift_id;
	}
	public void setGift_id(Integer gift_id) {
		this.gift_id = gift_id;
	}
	public String getGift_name() {
		return gift_name;
	}
	public void setGift_name(String gift_name) {
		this.gift_name = gift_name;
	}
	public String getSmall_img() {
		return small_img;
	}
	public void setSmall_img(String small_img) {
		this.small_img = small_img;
	}
	public Double getGift_price() {
		return gift_price;
	}
	public void setGift_price(Double gift_price) {
		this.gift_price = gift_price;
	}
	@Override
	public String toString() {
		return "LargeOrderInfo [orderinfo_id=" + orderinfo_id + ", orderinfo_price=" + orderinfo_price
				+ ", orderinfo_num=" + orderinfo_num + ", gift_id=" + gift_id + ", gift_name=" + gift_name
				+ ", small_img=" + small_img + ", gift_price=" + gift_price + "]";
	}
	
	
	
}
