package edu.bionic.sverkunov.com.services;

import java.util.List;

import edu.bionic.sverkunov.com.DAODB3.classes.DeliveryList;

public interface DeliveryServiceI {

	List<DeliveryList> updateDeliveryScreen(int option);

	void takeOrder(DeliveryList order, int id);

	void done(DeliveryList order, int id);

}
