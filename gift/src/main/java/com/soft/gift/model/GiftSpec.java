package com.soft.gift.model;

import javax.persistence.*;

@Table(name="t_gift_spec")
public class GiftSpec {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private Integer gift_id;
	private Integer spec_id;
	
	public GiftSpec() {
		super();
	}
	
	public GiftSpec(Integer gift_id, Integer spec_id) {
		super();
		this.gift_id = gift_id;
		this.spec_id = spec_id;
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
	
	@Column(name="spec_id")
	public Integer getSpec_id() {
		return spec_id;
	}
	public void setSpec_id(Integer spec_id) {
		this.spec_id = spec_id;
	}
	
	
}
