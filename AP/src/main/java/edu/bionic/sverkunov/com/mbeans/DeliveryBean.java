package edu.bionic.sverkunov.com.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import edu.bionic.sverkunov.com.DAODB3.classes.DeliveryList;
import edu.bionic.sverkunov.com.services.DeliveryServiceI;

@Named("deb")
@Scope("session")
public class DeliveryBean implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String DELIVERING = "delivering";
	public static final String DELIVERED = "delivered";
	private int option;
	
	@Inject
	private DeliveryServiceI deliveryService;

	private List<DeliveryList> queue;

	public DeliveryBean() {
	}

	public DeliveryServiceI getDeliveryService() {
		return deliveryService;
	}

	public void setDeliveryService(DeliveryServiceI deliveryService) {
		this.deliveryService = deliveryService;
	}

	public List<DeliveryList> getQueue() {
		return queue;
	}

	public void setQueue(List<DeliveryList> queue) {
		this.queue = queue;
	}

	public void updateDeliveryScreen(int option) {
		queue = deliveryService.updateDeliveryScreen(option);
	}

	public String take(DeliveryList order, int id) {
		deliveryService.takeOrder(order, id);
		return "deliveryPage";
	}

	public String done(DeliveryList order, int id) {
		deliveryService.done(order, id);
		return "deliveryPage";
	}

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}

	public boolean dis(DeliveryList order) {
		if (order.getStatus().equals(DELIVERING)
				|| order.getStatus().equals(DELIVERED)) {
			return true;
		}
		return false;
	}

	public boolean check(DeliveryList order) {
		if (order.getStatus().equals(DELIVERED)) {
			return true;
		}
		return false;
	}
}
