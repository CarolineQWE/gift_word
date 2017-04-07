package com.soft.gift.model;

import javax.persistence.*;

@Table(name="t_category")
public class Category {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer p_id;
	private Integer leval;
	
	
	public Category() {
		super();
	}


	public Category(String name, Integer p_id, Integer leval) {
		super();
		this.name = name;
		this.p_id = p_id;
		this.leval = leval;
	}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name="name",length=30)
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Column(name="p_id")
	public Integer getP_id() {
		return p_id;
	}


	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}


	@Column(name="leval")
	public Integer getLeval() {
		return leval;
	}


	public void setLeval(Integer leval) {
		this.leval = leval;
	}


	@Override
	public String toString() {
		return "Spec [id=" + id + ", name=" + name + ", p_id=" + p_id + ", leval=" + leval + "]";
	}
	
	
}
