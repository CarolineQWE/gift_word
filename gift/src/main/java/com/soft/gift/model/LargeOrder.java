package com.soft.gift.model;

import java.sql.Timestamp;

public class LargeOrder {
	private String account;
	private String prov;
	private String city;
	private String dist;
	private String receiver;
	private String tel;
	private String id;
	private Timestamp order_time;
	private Integer status;
	public LargeOrder(String account, String prov,String city, String dist, String receiver, String tel, String id,
			Timestamp order_time,Integer status) {
		super();
		this.account = account;
		this.setProv(prov);
		this.city = city;
		this.dist = dist;
		this.receiver = receiver;
		this.tel = tel;
		this.id = id;
		this.order_time = order_time;
		this.status = status;
	}
	public LargeOrder() {
		super();
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	public Timestamp getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Timestamp order_time) {
		this.order_time = order_time;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "LargeOrder [account=" + account + ", prov=" + prov + ", city=" + city + ", dist=" + dist + ", receiver="
				+ receiver + ", tel=" + tel + ", id=" + id + ", order_time=" + order_time + ", status="
				+ status + "]";
	}
	
}
