package edu.bionic.sverkunov.com.DAODB3.classes;

public class Address {

	private String city;
	private String street;
	private String building;
	private String app;
	
	public Address(String city, String street, String building, String app) {
		this.city = city;
		this.street = street;
		this.building = building;
		this.app = app;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}
	
}
