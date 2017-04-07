package com.soft.gift.model;

public class ScoreNum {
	private Integer score;
	private Integer num;
	
	public ScoreNum() {
		super();
	}
	public ScoreNum(Integer score, Integer num) {
		super();
		this.score = score;
		this.num = num;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "ScoreNum [score=" + score + ", num=" + num + "]";
	}
	
	
}
