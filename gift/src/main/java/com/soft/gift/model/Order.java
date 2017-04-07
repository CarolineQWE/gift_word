package com.soft.gift.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Table(name="t_order")
public class Order {
	@Id
	private String id;
	private String account;
	private Timestamp order_time;
	private Integer status;
	private Integer address_id;
	private Double total_price;
	private Integer type;
	private Integer info_num;
	
	public Order() {
		super();
	}

	public Order(String id, String account, Timestamp order_time, Integer status, Integer address_id,
			Double total_price, Integer type,Integer info_num) {
		super();
		this.id = id;
		this.account = account;
		this.order_time = order_time;
		this.status = status;
		this.address_id = address_id;
		this.total_price = total_price;
		this.type = type;
		this.info_num = info_num;
	}

	@Column(name="account",length=20)
	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	@Column(name="order_time")
	public Timestamp getOrder_time() {
		return order_time;
	}


	public void setOrder_time(Timestamp order_time) {
		this.order_time = order_time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="total_price")
	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Double total_paice) {
		this.total_price = total_paice;
	}

	@Column(name="address_id")
	public Integer getAddress_id() {
		return address_id;
	}


	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}

	@Column(name="type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name="info_num")
	public Integer getInfo_num() {
		return info_num;
	}

	public void setInfo_num(Integer info_num) {
		this.info_num = info_num;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", account=" + account + ", order_time=" + order_time + ", status=" + status
				+ ", address_id=" + address_id + ", total_price=" + total_price + ", type=" + type + ", info_num="
				+ info_num + "]";
	}

	
	
}
