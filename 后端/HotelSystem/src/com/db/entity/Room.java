package com.db.entity;

public class Room {
	private String type;
	private int price;
	private int sum;
	private int used;
	private int Remain;
	private int type_id;
	
	
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getUsed() {
		return used;
	}
	public void setUsed(int used) {
		this.used = used;
	}
	public int getRemain() {
		return Remain;
	}
	public void setRemain(int remain) {
		Remain = remain;
	}
	
	
	
}
