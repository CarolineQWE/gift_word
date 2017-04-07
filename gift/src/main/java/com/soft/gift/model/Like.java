package com.soft.gift.model;

import javax.persistence.*;

@Table(name="t_like")
public class Like {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String account;
	private Integer stategy_id;
	
	
	public Like() {
		super();
	}


	public Like(Integer id, String account, Integer stategy_id) {
		super();
		this.id = id;
		this.account = account;
		this.stategy_id = stategy_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name="account",length=20)
	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name="stategy_id")
	public Integer getStategy_id() {
		return stategy_id;
	}


	public void setStategy_id(Integer stategy_id) {
		this.stategy_id = stategy_id;
	}
	
	
}
