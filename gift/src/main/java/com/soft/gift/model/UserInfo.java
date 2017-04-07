package com.soft.gift.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Table(name="t_userinfo")
public class UserInfo {
	private String account;
	private String nickname;
	private Integer age;
	private Integer sex;
	private Integer prov;
	private Integer city;
	private Integer dist;
	private Date birthday;
	private String head;
	private String tel;
	private String brief;
	
	
	public UserInfo(String account, String nickname, Integer age, Integer sex, Integer prov, Integer city, Integer dist,
			Date birthday, String head, String tel, String brief) {
		super();
		this.account = account;
		this.nickname = nickname;
		this.age = age;
		this.sex = sex;
		this.prov = prov;
		this.city = city;
		this.dist = dist;
		this.birthday = birthday;
		this.head = head;
		this.tel = tel;
		this.brief = brief;
	}

	public UserInfo() {
		super();
	}
	
	@Id
	@Column(name="account",length=30)
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	@Column(name="nickname")
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Column(name="age")
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Column(name="sex")
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	@Column(name="prov")
	public Integer getProv() {
		return prov;
	}
	public void setProv(Integer prov) {
		this.prov = prov;
	}
	
	@Column(name="city")
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	
	@Column(name="dist")
	public Integer getDist() {
		return dist;
	}
	public void setDist(Integer dist) {
		this.dist = dist;
	}
	
	@Column(name="birthday")
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Column(name="head")
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	
	@Column(name="tel")
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Column(name="brief",length=200)
	public String getBrief() {
		return brief;
	}
	
	public void setBrief(String brief) {
		this.brief = brief;
	}
	
	
}
