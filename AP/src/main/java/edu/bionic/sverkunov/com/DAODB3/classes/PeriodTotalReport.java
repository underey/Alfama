package edu.bionic.sverkunov.com.DAODB3.classes;

import java.io.Serializable;

public class PeriodTotalReport implements Serializable {

	private static final long serialVersionUID = 1L;
	private long orderCount;
	private double sumOmi;

	public PeriodTotalReport(long orderCount, double sumOmi) {
		this.orderCount = orderCount;
		this.sumOmi = sumOmi;
	}

	public long getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(long orderCount) {
		this.orderCount = orderCount;
	}

	public double getSumOmi() {
		return sumOmi;
	}

	public void setSumOmi(double sumOmi) {
		this.sumOmi = sumOmi;
	}

}
