package com.soft.gift.model;

import javax.persistence.*;

@Table(name="t_spec")
public class Spec {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String value;
	private Integer type;
	
	public Spec(String name, String value,Integer type) {
		super();
		this.name = name;
		this.value = value;
		this.type = type;
	}
	public Spec() {
		super();
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
	
	@Column(name="value",length=50)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Column(name="type")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Spec [id=" + id + ", name=" + name + ", value=" + value + ", type=" + type + "]";
	}
	
	
	
}
