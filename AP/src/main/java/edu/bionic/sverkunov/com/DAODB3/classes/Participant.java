package edu.bionic.sverkunov.com.DAODB3.classes;

public class Participant {

	private Customer customer;
	private Staff staff;

	public Participant(Customer customer, Staff staff) {
		this.customer = customer;
		this.staff = staff;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
}
