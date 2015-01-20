package edu.bionic.sverkunov.com.DAODB3.classes;

import java.sql.Timestamp;

public class KitchenList {

	private int orderId;
	private Timestamp time;
	private String name;
	private String displayTime;

	public KitchenList(int orderId, Timestamp time, String name) {
		this.orderId = orderId;
		this.time = time;
		this.name = name;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayTime() {
		return displayTime;
	}

	public void setDisplayTime(String displayTime) {
		this.displayTime = displayTime;
	}

}
