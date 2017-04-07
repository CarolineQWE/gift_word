package com.soft.gift.model;

import javax.persistence.*;

@Table(name="t_orderinfo_spec")
public class OrderInfoSpec {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String orderinfo_id;
	private Integer spec_id;
	public OrderInfoSpec(String orderinfo_id, Integer spec_id) {
		super();
		this.orderinfo_id = orderinfo_id;
		this.spec_id = spec_id;
	}
	public OrderInfoSpec() {
		super();
	}
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="orderinfo_id",length=50)
	public String getOrderinfo_id() {
		return orderinfo_id;
	}
	public void setOrderinfo_id(String orderinfo_id) {
		this.orderinfo_id = orderinfo_id;
	}
	
	@Column(name="spec_id")
	public Integer getSpec_id() {
		return spec_id;
	}
	public void setSpec_id(Integer spec_id) {
		this.spec_id = spec_id;
	}
	@Override
	public String toString() {
		return "OrderInfoSpec [id=" + id + ", orderinfo_id=" + orderinfo_id + ", spec_id=" + spec_id + "]";
	}
	
	
}
