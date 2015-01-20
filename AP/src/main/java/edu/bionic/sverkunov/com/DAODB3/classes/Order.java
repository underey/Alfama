package edu.bionic.sverkunov.com.DAODB3.classes;

import java.io.Serializable;
import java.util.Comparator;

public class Order implements Comparator<Order>, Serializable {

	private static final long serialVersionUID = 1L;
	private int dishId;
	private String dishName;
	private double dishprice;
	private int dishAmount;

	public Order() {
	}

	public Order(int dishId, String dishName, double dishprice, int dishAmount) {
		super();
		this.dishId = dishId;
		this.dishName = dishName;
		this.dishprice = dishprice;
		this.dishAmount = dishAmount;
	}

	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public double getDishprice() {
		return dishprice;
	}

	public void setDishprice(double dishprice) {
		this.dishprice = dishprice;
	}

	public int getDishAmount() {
		return dishAmount;
	}

	public void setDishAmount(int dishAmount) {
		this.dishAmount = dishAmount;
	}

	@Override
	public String toString() {
		return "Order [dishId=" + dishId + ", dishName=" + dishName
				+ ", dishprice=" + dishprice + ", dishAmount=" + dishAmount
				+ "]";
	}

	@Override
	public int compare(Order o1, Order o2) {
		return o1.getDishName().compareTo(o2.getDishName());
	}

}
