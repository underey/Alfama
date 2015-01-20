package edu.bionic.sverkunov.com.DAODB3.interfaces;

import java.util.List;

import edu.bionic.sverkunov.com.DAODB3.classes.KitchenList;
import edu.bionic.sverkunov.com.DAODB3.classes.Orderrmenuitem;

public interface KitchenMemberStaffDAOI {

	public List<KitchenList> updateKitchenScreen();

	public int done(int orderId);

	public List<Orderrmenuitem> findOrder(int id, boolean type);

	public void sendToDelivery(int id);

	public void checkPreparedDish(int id, int staffId);
}
