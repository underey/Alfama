package edu.bionic.sverkunov.com.DAODB3.classes;

import java.sql.Timestamp;

public class DeliveryList {

	private int orderId;
	private Timestamp time;
	private String status;
	private double price;
	private String displayAddress;
	private String displayTime;

	public DeliveryList(int orderId, Timestamp time, String status,
			double price) {
		this.orderId = orderId;
		this.time = time;
		this.status = status;
		this.price = price;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDisplayTime() {
		return displayTime;
	}

	public void setDisplayTime(String displayTime) {
		this.displayTime = displayTime;
	}

	public String getDisplayAddress() {
		return displayAddress;
	}

	public void setDisplayAddress(String displayAddress) {
		this.displayAddress = displayAddress;
	}

}
