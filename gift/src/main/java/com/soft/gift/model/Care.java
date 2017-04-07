package com.soft.gift.model;

import javax.persistence.*;

@Table(name="t_care")
public class Care {
	private Integer id;
	private String cared_account;
	private String account;
	
	public Care() {
		super();
	}
	public Care(String cared_account, String account) {
		super();
		this.cared_account = cared_account;
		this.account = account;
	}
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="account", length=30)
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	@Column(name="cared_account",length=30)
	public String getCared_account() {
		return cared_account;
	}
	public void setCared_account(String cared_account) {
		this.cared_account = cared_account;
	}
	
	
}
