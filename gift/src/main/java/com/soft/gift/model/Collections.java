package com.soft.gift.model;

import javax.persistence.*;

@Table(name="t_collection")
public class Collections {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private Integer gift_id;
	private String account;
	public Collections(Integer gift_id, String account) {
		super();
		this.gift_id = gift_id;
		this.account = account;
	}
	public Collections() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="gift_id", nullable=false)
	public Integer getgift_id() {
		return gift_id;
	}
	public void setgift_id(Integer gift_id) {
		this.gift_id = gift_id;
	}
	@Column(name="account", nullable=false,length=30)
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
}
