package edu.bionic.sverkunov.com.services;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import edu.bionic.sverkunov.com.DAODB3.classes.KitchenList;
import edu.bionic.sverkunov.com.DAODB3.classes.Orderrmenuitem;
import edu.bionic.sverkunov.com.DAODB3.interfaces.KitchenMemberStaffDAOI;

@Named
public class KitchenService implements KitchenServiceI, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private KitchenMemberStaffDAOI kitchenDAO;
	public static final String KITCHENDONE = "done";
	public static final boolean NONKITCHENMADE = false;
	public static final boolean KITCHENMADE = true;
	
	public KitchenService() {
	}

	public KitchenMemberStaffDAOI getKitchenDAO() {
		return kitchenDAO;
	}

	public void setKitchenDAO(KitchenMemberStaffDAOI kitchenDAO) {
		this.kitchenDAO = kitchenDAO;
	}

	@Override
	public List<KitchenList> updateKitchenScreen() {
		List<KitchenList> k = kitchenDAO.updateKitchenScreen();
		DateFormat df = new SimpleDateFormat("EEE, d MMM HH:mm", Locale.US);
		for (KitchenList kl : k) {
			kl.setDisplayTime(df.format(kl.getTime()));
		}
		return k;
	}

	@Transactional
	public void done(KitchenList kl, int staffId) {
		int id = kitchenDAO.done(kl.getOrderId());
		List<Orderrmenuitem> order = null;
		order = kitchenDAO.findOrder(id, KITCHENMADE);
		int number = 0;
		for (Orderrmenuitem o : order) {
			if (o.getStatus().equals(KITCHENDONE)) {
				number++;
			}
		}
		if (order.size() == number) {
			order = kitchenDAO.findOrder(id, NONKITCHENMADE);
			kitchenDAO.sendToDelivery(id);
		}
		kitchenDAO.checkPreparedDish(id, staffId);
	}

}
