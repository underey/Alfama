package edu.bionic.sverkunov.com.DAODB3.interfaces;

import java.util.List;

import edu.bionic.sverkunov.com.DAODB3.classes.Customer;
import edu.bionic.sverkunov.com.DAODB3.classes.Staff;

public interface WebSecurityGuardDAOI {

	public List<Staff> getAllStaffBySurname(String surname);
	
	public List<Staff> getAllStaffByRole(int accesslevel);

	public boolean signUp(Staff staff, int accesslevel);

	public void editStaff(Staff staff);

	public Staff findStaff(String email);

	public void changeStaffPassword(int id, String hashedPass);

	public Customer findCustomerByOrder(int orderId);

	public List<Staff> findStaffByOrder(int orderId);

}
