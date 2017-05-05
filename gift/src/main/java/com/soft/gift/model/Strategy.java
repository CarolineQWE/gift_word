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
	private Integer likea;
	private Integer comment;
	private Timestamp publish_time;
	private String relationship;
	private String style;
	private String occasion;
	private String account;
	private Integer collect;
	private Integer dislike;
	private Integer if_publish;
	private String brief;


	public Strategy(String title, String content, String top_img, Integer like, Integer comment, Timestamp publish_time,
					String relationship, String style, String occasion, String account,Integer collect,Integer dislike,Integer if_publish,String brief) {
		super();
		this.title = title;
		this.content = content;
		this.top_img = top_img;
		this.likea = like;
		this.comment = comment;
		this.publish_time = publish_time;
		this.relationship = relationship;
		this.style = style;
		this.occasion = occasion;
		this.account = account;
        this.collect = collect;
        this.dislike = dislike;
		this.if_publish = if_publish;
		this.brief = brief;
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
		return likea;
	}
	public void setLike(Integer like) {
		this.likea = like;
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
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	@Column(name="style")
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
	@Column(name="occasion")
	public String getOccasion() {
		return occasion;
	}
	public void setOccasion(String occasion) {
		this.occasion = occasion;
	}
	
	@Column(name="account",length=30)
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}

    @Column(name="collect")
    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    @Column(name="dislike")
    public Integer getDislike() {
        return dislike;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

	@Column(name="if_publish")
	public Integer getIf_publish() {
		return if_publish;
	}

	public void setIf_publish(Integer if_publish) {
		this.if_publish = if_publish;
	}

	@Column(name = "brief")
	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

    @Override
    public String toString() {
        return "Strategy{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", top_img='" + top_img + '\'' +
                ", likea=" + likea +
                ", comment=" + comment +
                ", publish_time=" + publish_time +
                ", relationship='" + relationship + '\'' +
                ", style='" + style + '\'' +
                ", occasion='" + occasion + '\'' +
                ", account='" + account + '\'' +
                ", collect=" + collect +
                ", dislike=" + dislike +
                ", if_publish=" + if_publish +
                ", brief='" + brief + '\'' +
                '}';
    }
}

