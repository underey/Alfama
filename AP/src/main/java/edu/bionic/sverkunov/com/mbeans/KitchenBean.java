package edu.bionic.sverkunov.com.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import edu.bionic.sverkunov.com.DAODB3.classes.KitchenList;
import edu.bionic.sverkunov.com.services.KitchenServiceI;

@Named("kb")
@Scope("session")
public class KitchenBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private KitchenServiceI kitchenService;

	private List<KitchenList> queue;
	private int staffId;

	public KitchenBean() {
	}

	public KitchenServiceI getKitchenService() {
		return kitchenService;
	}

	public void setKitchenService(KitchenServiceI kitchenService) {
		this.kitchenService = kitchenService;
	}

	public List<KitchenList> getQueue() {
		return queue;
	}

	public void setQueue(List<KitchenList> queue) {
		this.queue = queue;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public void updateKitchenScreen() {
		queue = kitchenService.updateKitchenScreen();
	}

	public String done(KitchenList kl, int staffId) {
		kitchenService.done(kl, staffId);
		return "kitchenPage";
	}
}
