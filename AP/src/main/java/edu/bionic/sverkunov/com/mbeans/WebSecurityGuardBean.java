package edu.bionic.sverkunov.com.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import edu.bionic.sverkunov.com.DAODB3.classes.Participant;
import edu.bionic.sverkunov.com.DAODB3.classes.Staff;
import edu.bionic.sverkunov.com.services.StaffServiceI;

@Named("wsg")
@Scope("session")
public class WebSecurityGuardBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private StaffServiceI staffService;

	private Staff staff;
	private String name;
	private String surname;
	private String login;
	private int accesslevel;
	private String email;
	private String password;
	private String availableMail;
	private List<Staff> staffs;
	private String sur;
	private String existingEmail;
	private List<Participant> participants;
	private int orderId;
	private boolean isactivated;

	public WebSecurityGuardBean() {
		if (staff == null) {
			staff = new Staff();
		}
	}

	public boolean isIsactivated() {
		return isactivated;
	}

	public void setIsactivated(boolean isactivated) {
		this.isactivated = isactivated;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public int getAccesslevel() {
		return accesslevel;
	}

	public void setAccesslevel(int accesslevel) {
		this.accesslevel = accesslevel;
	}

	public String getAvailableMail() {
		return availableMail;
	}

	public void setAvailableMail(String availableMail) {
		this.availableMail = availableMail;
	}

	public String getEmail() {
		return email;
	}

	public List<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}

	public void setEmail(String email) {
		if (email == null) {
		} else {
			staff = staffService.check(email);
			if (staff == null) {
				availableMail = "Available";
				this.email = email;
			} else {
				availableMail = "Not available";
				this.email = null;
			}
		}
	}

	public StaffServiceI getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffServiceI staffService) {
		this.staffService = staffService;
	}

	public String getSur() {
		return sur;
	}

	public void setSur(String sur) {
		this.sur = sur;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getExistingEmail() {
		return existingEmail;
	}

	public void setExistingEmail(String existingEmail) {
		if (existingEmail == null) {
		} else {
			staff = staffService.check(existingEmail);
			if (staff != null) {
				availableMail = "Exist";
				this.existingEmail = existingEmail;
			} else {
				availableMail = "Not exist";
				this.existingEmail = null;
			}
		}
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String create() {
		if (staff == null) {
			staff = new Staff();
		}
		String page = "addStaffPage";
		if (email.trim().length() == 0) {
		} else {
			staff.setName(name);
			staff.setSurname(surname);
			staff.setLogin(login);
			staff.setEmail(email);
			staff.setIsactivated(isactivated);
			staff.setPassword(password);
			boolean b = staffService.signUp(staff, accesslevel);
			if (b) {
				page = "homePage";
			}
		}
		return page;
	}

	public String generateStaffListBySurname() {
		String page = "staffListPage";
		staffs = staffService.generateStaffListBySurname(sur);
		if (staffs == null) {
			page = "homePage";
		}
		return page;
	}

	public String generateStaffListByRole() {
		String page = "staffListPage";
		staffs = staffService.generateStaffListByRole(accesslevel);
		if (staffs == null) {
			page = "homePage";
		}
		return page;
	}

	public void editStaff(Staff s) {
		boolean f = check(s);
		if (f) {
			staffService.editPerson(s);
		}
	}

	private boolean check(Staff f) {
		boolean flag = true;
		if (f.getSurname() == null || f.getSurname().trim().length() == 0
				|| f.getName() == null || f.getName().trim().length() == 0
				|| f.getLogin() == null || f.getLogin().trim().length() == 0) {
			flag = false;
		}
		return flag;
	}

	public void submitChange() {
		staffService.changeStaffPassword(staff, password);
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Successful", "Pass for "
				+ staff.getName() + " was successfully changed"));
		existingEmail = null;
		password = null;
	}

	public void showOrderDetail() {
		participants = staffService.showParticipants(orderId);
	}
}
