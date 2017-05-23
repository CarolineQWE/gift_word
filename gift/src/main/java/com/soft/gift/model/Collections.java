package com.soft.gift.model;

import javax.persistence.*;

@Table(name="t_collection")
public class Collections {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private Integer collected_id;
	private Integer collected_type;
	private String account;

	public Collections() {
		super();
	}

	public Collections(Integer collected_id, Integer collected_type, String account) {
		this.collected_id = collected_id;
		this.collected_type = collected_type;
		this.account = account;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "collected_id")
	public Integer getCollected_id() {
		return collected_id;
	}

	public void setCollected_id(Integer collected_id) {
		this.collected_id = collected_id;
	}

	@Column(name = "collected_type")
	public Integer getCollected_type() {
		return collected_type;
	}

	public void setCollected_type(Integer collected_type) {
		this.collected_type = collected_type;
	}

	@Column(name = "account", length = 30)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Collections{" +
				"id=" + id +
				", collected_id=" + collected_id +
				", collected_type=" + collected_type +
				", account='" + account + '\'' +
				'}';
	}
}
