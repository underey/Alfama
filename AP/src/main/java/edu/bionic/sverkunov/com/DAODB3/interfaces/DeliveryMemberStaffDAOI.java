package edu.bionic.sverkunov.com.DAODB3.interfaces;

import java.util.List;

import edu.bionic.sverkunov.com.DAODB3.classes.DeliveryList;

public interface DeliveryMemberStaffDAOI {

	public List<DeliveryList> updateDeliveryScreen(int option);

	public List<String> findCustomerAddress(List<Integer> temp);

	public void takeOrder(int orderId);

	public void markAsDelivering(int orderId, int id);

	public void done(int orderId);

	public void markAsDelivered(int orderId, int id);
}
