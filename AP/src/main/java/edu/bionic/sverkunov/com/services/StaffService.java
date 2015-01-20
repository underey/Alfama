package edu.bionic.sverkunov.com.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;

import edu.bionic.sverkunov.com.DAODB3.classes.Customer;
import edu.bionic.sverkunov.com.DAODB3.classes.Participant;
import edu.bionic.sverkunov.com.DAODB3.classes.Staff;
import edu.bionic.sverkunov.com.DAODB3.interfaces.WebSecurityGuardDAOI;

@Named
public class StaffService implements StaffServiceI, Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private WebSecurityGuardDAOI securityDao;

	public StaffService() {
	}

	public Staff check(String email) {
		Staff ans = securityDao.findStaff(email);
		if (ans == null) {
			return null;
		}
		return ans;
	}

	@Transactional
	public boolean signUp(Staff staff, int accesslevel) {
		String pass = staff.getPassword();
		String hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());
		staff.setPassword(hashedPass);
		boolean b = securityDao.signUp(staff, accesslevel);
		return b;
	}

	@Transactional
	public void editPerson(Staff staff) {
		securityDao.editStaff(staff);
	}

	@Override
	public List<Staff> generateStaffListBySurname(String surname) {
		List<Staff> staffs = securityDao.getAllStaffBySurname(surname);
		return staffs;
	}

	@Override
	public List<Staff> generateStaffListByRole(int accesslevel) {
		List<Staff> staffs = securityDao.getAllStaffByRole(accesslevel);
		return staffs;
	}

	@Transactional
	public void changeStaffPassword(Staff staff, String password) {
		if (staff == null) {
		} else {
			String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt());
			securityDao.changeStaffPassword(staff.getId(), hashedPass);
		}
	}

	@Override
	public List<Participant> showParticipants(int orderId) {
		List<Participant> p = null;
		Customer c = securityDao.findCustomerByOrder(orderId);
		if (c == null) {
			return p;
		}
		List<Staff> staffs = securityDao.findStaffByOrder(orderId);
		if (staffs == null) {
			return p;
		}
		p = new ArrayList<Participant>();
		for (Staff s : staffs) {
			p.add(new Participant(c, s));
		}
		return p;
	}
}
