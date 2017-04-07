package com.soft.gift.model;

import javax.persistence.*;

@Table(name="t_return_application")
public class ReturnApplication {
	private Integer id;
	private Integer reason_id;
	private String description;
	private String proof_pic;
	private String order_id;
	private Integer order_info_id;
	public ReturnApplication(Integer reason_id, String description, String proof_pic, String order_id,
			Integer order_info_id) {
		super();
		this.reason_id = reason_id;
		this.description = description;
		this.proof_pic = proof_pic;
		this.order_id = order_id;
		this.order_info_id = order_info_id;
	}
	public ReturnApplication() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="reasion_id")
	public Integer getReason_id() {
		return reason_id;
	}
	public void setReason_id(Integer reason_id) {
		this.reason_id = reason_id;
	}
	
	@Column(name="descrition",length=200)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="proof_pic",length=100)
	public String getProof_pic() {
		return proof_pic;
	}
	public void setProof_pic(String proof_pic) {
		this.proof_pic = proof_pic;
	}
	
	@Column(name="order_id",length=30)
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	@Column(name="order_info_id")
	public Integer getOrder_info_id() {
		return order_info_id;
	}
	public void setOrder_info_id(Integer order_info_id) {
		this.order_info_id = order_info_id;
	}
	
	
}
