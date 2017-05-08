package com.soft.gift.model;

import javax.persistence.*;

@Table(name="t_user")
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String account;
	private String password;
	private Integer credits;
	private Integer care;
	private Integer identity;
	
	
	public User(String account, String password, Integer credits, Integer care,Integer identity) {
		super();
		this.account = account;
		this.password = password;
		this.credits = credits;
		this.care = care;
		this.identity = identity;
	}

	public User() {
		super();
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
	
	@Column(name="password",length=30)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="credits")
	public Integer getCredits() {
		return credits;
	}
	public void setCredits(Integer credits) {
		this.credits = credits;
	}
	
	
	@Column(name="care")
	public Integer getCare() {
		return care;
	}
	public void setCare(Integer care) {
		this.care = care;
	}

	@Column(name="identity")
	public Integer getIdentity() {
		return identity;
	}

	public void setIdentity(Integer identity) {
		this.identity = identity;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", account='" + account + '\'' +
				", password='" + password + '\'' +
				", credits=" + credits +
				", care=" + care +
				", identity=" + identity +
				'}';
	}
}
