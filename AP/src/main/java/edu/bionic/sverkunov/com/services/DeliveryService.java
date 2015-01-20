package edu.bionic.sverkunov.com.services;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import edu.bionic.sverkunov.com.DAODB3.classes.DeliveryList;
import edu.bionic.sverkunov.com.DAODB3.interfaces.DeliveryMemberStaffDAOI;

@Named
public class DeliveryService implements DeliveryServiceI, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DeliveryMemberStaffDAOI deliveryDAO;

	public DeliveryService() {
	}

	public DeliveryMemberStaffDAOI getDeliveryDAO() {
		return deliveryDAO;
	}

	public void setDeliveryDAO(DeliveryMemberStaffDAOI deliveryDAO) {
		this.deliveryDAO = deliveryDAO;
	}

	@Override
	public List<DeliveryList> updateDeliveryScreen(int option) {
		List<DeliveryList> d = deliveryDAO.updateDeliveryScreen(option);
		List<Integer> temp = new ArrayList<Integer>();
		for (DeliveryList dl : d) {
			temp.add(dl.getOrderId());
		}
		List<String> address = deliveryDAO.findCustomerAddress(temp);
		DateFormat df = new SimpleDateFormat("EEE, d MMM HH:mm", Locale.US);
		for (int k = 0; k < d.size(); k++) {
			DeliveryList dl = d.get(k);
			dl.setDisplayAddress(address.get(k));
			dl.setDisplayTime(df.format(dl.getTime()));
		}
		return d;
	}

	@Transactional
	public void takeOrder(DeliveryList order, int id) {
		deliveryDAO.takeOrder(order.getOrderId());
		deliveryDAO.markAsDelivering(order.getOrderId(), id);
	}

	@Transactional
	public void done(DeliveryList order, int id) {
		deliveryDAO.done(order.getOrderId());
		deliveryDAO.markAsDelivered(order.getOrderId(), id);
	}

}
