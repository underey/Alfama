package edu.bionic.sverkunov.com.mbeans;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import edu.bionic.sverkunov.com.DAODB3.classes.Customer;
import edu.bionic.sverkunov.com.services.CustomerServiceI;

@Named("creg")
@Scope("session")
public class CustomerRegistrationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CustomerServiceI customerService;

	private String name;
	private String surname;
	private Date birthday;
	private String city;
	private String street;
	private String building;
	private String app;
	private String payment;
	private String phone;

	private String email;
	private String availableMail;
	private String login;
	private String password;
	private String passwordAgain;

	public CustomerRegistrationBean() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		boolean ans = customerService.check(email);
		if (ans == true) {
			availableMail = "Available";
			this.email = email;
		} else {
			availableMail = "Not available";
			this.email = null;
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}

	public String getAvailableMail() {
		return availableMail;
	}

	public void setAvailableMail(String availableMail) {
		this.availableMail = availableMail;
	}

	public String submit() {
		String page = "customer-registrationPage";
		if (email.trim().length() == 0) {
			
		} else {
			Customer c = new Customer();
			c.setName(name);
			c.setSurname(surname);
			java.sql.Date b = new java.sql.Date(birthday.getTime());
			c.setBirthday(b);
			c.setCity(city);
			c.setStreet(street);
			c.setBuilding(building);
			c.setApp(app);
			c.setPayment_info(payment);
			c.setPhone(phone);
			c.setEmail(email);
			c.setLogin(login);
			c.setPassword(password);
			String s = customerService.signUp(c);
			if (s.equals("ok")) {
				page = "homePage";
			}
		}
		return page;
	}

	public CustomerServiceI getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerServiceI customerService) {
		this.customerService = customerService;
	}

}
