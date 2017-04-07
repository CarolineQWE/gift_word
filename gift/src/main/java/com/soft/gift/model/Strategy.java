package com.soft.gift.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name="t_strategy")
public class Strategy {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String content;
	private String top_img;
	private Integer like;
	private Integer comment;
	private Timestamp publish_time;
	private Integer relationship;
	private Integer style;
	private Integer occasion;
	private String account;
	
	public Strategy(String title, String content, String top_img, Integer like, Integer comment, Timestamp publish_time,
			Integer relationship, Integer style, Integer occasion, String account) {
		super();
		this.title = title;
		this.content = content;
		this.top_img = top_img;
		this.like = like;
		this.comment = comment;
		this.publish_time = publish_time;
		this.relationship = relationship;
		this.style = style;
		this.occasion = occasion;
		this.account = account;
	}
	
	public Strategy() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="title",length=100)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name="top_img",length=100)
	public String getTop_img() {
		return top_img;
	}
	public void setTop_img(String top_img) {
		this.top_img = top_img;
	}
	
	@Column(name="like")
	public Integer getLike() {
		return like;
	}
	public void setLike(Integer like) {
		this.like = like;
	}
	
	@Column(name="comment")
	public Integer getComment() {
		return comment;
	}
	public void setComment(Integer comment) {
		this.comment = comment;
	}
	
	@Column(name="publish_time")
	public Timestamp getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(Timestamp publish_time) {
		this.publish_time = publish_time;
	}
	
	@Column(name="relationship")
	public Integer getRelationship() {
		return relationship;
	}
	public void setRelationship(Integer relationship) {
		this.relationship = relationship;
	}
	
	@Column(name="style")
	public Integer getStyle() {
		return style;
	}
	public void setStyle(Integer style) {
		this.style = style;
	}
	
	@Column(name="occasion")
	public Integer getOccasion() {
		return occasion;
	}
	public void setOccasion(Integer occasion) {
		this.occasion = occasion;
	}
	
	@Column(name="account",length=30)
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
}
