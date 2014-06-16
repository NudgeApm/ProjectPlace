package com.nla.domain;

public class StringBean extends ObjectBase{
	String s;
	int count = 0;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}
	
	public void increment(){
		count++;
	}
	
}
