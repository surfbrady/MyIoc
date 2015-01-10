package com.myioc;

public class Student {
	private String name;
	private Double score;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	
	@Override
	public String toString(){
		return "[name:"+name+"score:"+score+"]";
	}
	
}
