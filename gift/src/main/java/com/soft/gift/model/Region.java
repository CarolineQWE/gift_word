package com.soft.gift.model;

import javax.persistence.*;

@Table(name="t_region")
public class Region {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private Integer p_id;
	private String name;
	private int type;
	
	public Region() {
		super();
	}

	public Region(Integer p_id, String name, Integer type) {
		super();
		this.p_id = p_id;
		this.name = name;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="p_id")
	public Integer getP_id() {
		return p_id;
	}

	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}

	@Column(name="name",length=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
