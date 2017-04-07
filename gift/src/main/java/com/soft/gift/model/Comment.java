package com.soft.gift.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name="t_comment")
public class Comment implements Serializable,Comparable<Comment>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private Integer commented_id;
	private String account;
	private String content;
	private Timestamp comment_time;
	private Integer score;
	private Integer commented_type;
	private Integer leval;
	
	public Comment() {
		super();
	}
	
	public Comment(Integer commented_id, String account, String content, Timestamp comment_time, Integer score,
			Integer commented_type,Integer leval) {
		super();
		this.commented_id = commented_id;
		this.account = account;
		this.content = content;
		this.comment_time = comment_time;
		this.score = score;
		this.commented_type = commented_type;
		this.leval = leval;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="commented_id" ,nullable=false)
	public Integer getcommented_id() {
		return commented_id;
	}
	public void setcommented_id(Integer commented_id) {
		this.commented_id = commented_id;
	}
	@Column(name="account", nullable=false,length=30)
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Column(name="content", nullable=false,length=200)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="comment_time",nullable=false)
	public Timestamp getComment_time() {
		return comment_time;
	}
	public void setComment_time(Timestamp comment_time) {
		this.comment_time = comment_time;
	}

	@Column(name="score")
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(name="commented_type")
	public Integer getCommented_type() {
		return commented_type;
	}

	public void setCommented_type(Integer commented_type) {
		this.commented_type = commented_type;
	}

	@Column(name="leval")
	public Integer getLeval() {
		return leval;
	}

	public void setLeval(Integer leval) {
		this.leval = leval;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((comment_time == null) ? 0 : comment_time.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + id;
		result = prime * result + commented_id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (comment_time == null) {
			if (other.comment_time != null)
				return false;
		} else if (!comment_time.equals(other.comment_time))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id != other.id)
			return false;
		if (commented_id != other.commented_id)
			return false;
		return true;
	}
	@Override
	public int compareTo(Comment comment) {
		if (this == comment) {
			return 0;
		} else if (comment != null && comment instanceof Comment) {
			Comment c = (Comment) comment;
			if (this.id >= c.id) {
				return -1;
			} else {
				return 1;
			}
		} else {
			return -1;
		}
	}


}
