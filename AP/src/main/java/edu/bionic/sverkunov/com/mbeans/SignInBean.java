package edu.bionic.sverkunov.com.mbeans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;

import edu.bionic.sverkunov.com.DAODB3.classes.Customer;
import edu.bionic.sverkunov.com.DAODB3.classes.Staff;
import edu.bionic.sverkunov.com.DAODB3.classes.User;
import edu.bionic.sverkunov.com.services.CustomerServiceI;

@Named("cSignin")
@Scope("session")
public class SignInBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CustomerServiceI customerService;

	private Customer customer;
	private Staff staff;
	private String email;
	private String password;
	private int accessLevel;

	public SignInBean() {
	}

	public CustomerServiceI getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerServiceI customerService) {
		this.customerService = customerService;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String findUser() {
		email = email.trim();
		password = password.trim();
		User u = customerService.signIn(email, password);
		if (u == null) {
			this.email = "";
			this.password = "";
			return "sign-inPage";
		} else if (u.getClass() == Customer.class) {
			customer = (Customer) u;
			setAccessLevel(customer.getAccesslevel().getLevel_of_access());
		} else {
			staff = (Staff) u;
			setAccessLevel(staff.getAccesslevel().getLevel_of_access());
		}

		FacesContext ctx = FacesContext.getCurrentInstance();

		ExternalContext extContext = ctx.getExternalContext();
		String url = extContext.encodeActionURL(ctx.getApplication()
				.getViewHandler().getActionURL(ctx, "/homePage.xhtml"));
		if (accessLevel == 200) {
			url = extContext.encodeActionURL(ctx.getApplication()
					.getViewHandler().getActionURL(ctx, "/kitchenPage.xhtml"));
		} else if (accessLevel == 300) {
			url = extContext.encodeActionURL(ctx.getApplication()
					.getViewHandler().getActionURL(ctx, "/deliveryPage.xhtml"));
		} else if (accessLevel == 500) {
			url = extContext.encodeActionURL(ctx.getApplication()
					.getViewHandler().getActionURL(ctx, "/analystReportPage.xhtml"));
		}
		try {
			extContext.redirect(url);
		} catch (IOException ioe) {
			throw new FacesException(ioe);

		}
		return null;
	}

	public String displayIcon() {
		if (accessLevel == 0) {
			return "Sign In";
		} else {
			return "Sign Out";
		}
	}

	public String out() {
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(false)).invalidate();
		customer = null;
		staff = null;
		this.accessLevel = 0;
		return "homePage";
	}

	public String goHome() {
		FacesContext ctx = FacesContext.getCurrentInstance();

		ExternalContext extContext = ctx.getExternalContext();
		String url = extContext.encodeActionURL(ctx.getApplication()
				.getViewHandler().getActionURL(ctx, "/homePage.xhtml"));
		try {
			extContext.redirect(url);
		} catch (IOException ioe) {
			throw new FacesException(ioe);

		}
		return null;
	}
}