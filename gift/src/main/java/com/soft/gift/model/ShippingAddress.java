package com.soft.gift.model;

import javax.persistence.*;

@Table(name="t_shipping_address")
public class ShippingAddress {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String account;
	private String prov;
	private String city;
	private String dist;
	private String tel;
	private String receiver;
	private String address_info;
	
	public ShippingAddress() {
		super();
	}


	public ShippingAddress(String account, String prov, String city, String dist, String tel, String receiver,
			String address_info) {
		super();
		this.account = account;
		this.prov = prov;
		this.city = city;
		this.dist = dist;
		this.tel = tel;
		this.receiver = receiver;
		this.address_info = address_info;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name="account",length=30)
	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	@Column(name="prov",length=20)
	public String getProv() {
		return prov;
	}


	public void setProv(String prov) {
		this.prov = prov;
	}

	@Column(name="city" ,length=20)
	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	@Column(name="dist",length=20)
	public String getDist() {
		return dist;
	}


	public void setDist(String dist) {
		this.dist = dist;
	}


	@Column(name="tel")
	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	@Column(name="receiver",length=20)
	public String getReceiver() {
		return receiver;
	}


	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}


	@Column(name="address_info",length=200)
	public String getAddress_info() {
		return address_info;
	}


	public void setAddress_info(String address_info) {
		this.address_info = address_info;
	}


	@Override
	public String toString() {
		return "ShippingAddress [id=" + id + ", account=" + account + ", prov=" + prov + ", city=" + city + ", dist="
				+ dist + ", tel=" + tel + ", receiver=" + receiver + ", address_info=" + address_info + "]";
	}
	
	
}
