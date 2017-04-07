package com.soft.gift.model;

import javax.persistence.*;

@Table(name="t_sc_spec")
public class ScSpec {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String sc_id;
	private Integer spec_id;
	public ScSpec(String sc_id, Integer spec_id) {
		super();
		this.sc_id = sc_id;
		this.spec_id = spec_id;
	}
	public ScSpec() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="sc_id",length=30)
	public String getSc_id() {
		return sc_id;
	}
	public void setSc_id(String sc_id) {
		this.sc_id = sc_id;
	}
	
	@Column(name="spec_id")
	public Integer getSpec_id() {
		return spec_id;
	}
	public void setSpec_id(Integer spec_id) {
		this.spec_id = spec_id;
	}
	
	
}
