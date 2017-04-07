package com.soft.gift.model;

public class SpecNum {
	private String spec_name;
	private Integer spec_num;
	
	public SpecNum(String spec_name, Integer spec_num) {
		super();
		this.spec_name = spec_name;
		this.spec_num = spec_num;
	}
	public SpecNum() {
		super();
	}
	public String getSpec_name() {
		return spec_name;
	}
	public void setSpec_name(String spec_name) {
		this.spec_name = spec_name;
	}
	
	public Integer getSpec_num() {
		return spec_num;
	}
	public void setSpec_num(Integer spec_num) {
		this.spec_num = spec_num;
	}
	@Override
	public String toString() {
		return "SpecNum [spec_name=" + spec_name + ", spec_num=" + spec_num + "]";
	}
	
	
}
