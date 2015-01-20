package edu.bionic.sverkunov.com.services;

import java.util.List;

import edu.bionic.sverkunov.com.DAODB3.classes.Participant;
import edu.bionic.sverkunov.com.DAODB3.classes.Staff;

public interface StaffServiceI {
	public Staff check(String email);

	public boolean signUp(Staff staff, int accesslevel);

	public List<Staff> generateStaffListBySurname(String surname);

	public List<Staff> generateStaffListByRole(int accesslevel);

	public void editPerson(Staff staff);

	public void changeStaffPassword(Staff staff, String password);

	public List<Participant> showParticipants(int orderId);
}
