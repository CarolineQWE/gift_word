package com.soft.gift.model;

import javax.persistence.*;

@Table(name="t_gift_info")
public class GiftInfo {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private Integer gift_id;
	private String brief;
	private String gift_info;
	
	public GiftInfo() {
		super();
	}

	public GiftInfo(Integer gift_id, String brief, String gift_info) {
		super();
		this.gift_id = gift_id;
		this.brief = brief;
		this.gift_info = gift_info;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="gift_id")
	public Integer getGift_id() {
		return gift_id;
	}

	public void setGift_id(Integer gift_id) {
		this.gift_id = gift_id;
	}
	
	@Column(name="brief",length=500)
	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	@Column(name="gift_info",length=1000)
	public String getGift_info() {
		return gift_info;
	}

	public void setGift_info(String gift_info) {
		this.gift_info = gift_info;
	}

	@Override
	public String toString() {
		return "GiftInfo [id=" + id + ", gift_id=" + gift_id + ", brief=" + brief + ", gift_info=" + gift_info + "]";
	}
	
	
}
