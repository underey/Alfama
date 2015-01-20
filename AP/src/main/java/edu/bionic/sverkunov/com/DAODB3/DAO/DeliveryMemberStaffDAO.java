package edu.bionic.sverkunov.com.DAODB3.DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import edu.bionic.sverkunov.com.DAODB3.classes.Customer;
import edu.bionic.sverkunov.com.DAODB3.classes.DeliveryList;
import edu.bionic.sverkunov.com.DAODB3.classes.Orderr;
import edu.bionic.sverkunov.com.DAODB3.classes.Stafforderr;
import edu.bionic.sverkunov.com.DAODB3.interfaces.DeliveryMemberStaffDAOI;

@Repository
public class DeliveryMemberStaffDAO implements DeliveryMemberStaffDAOI,
		Serializable {

	private static final long serialVersionUID = 1L;
	public static final String READY = "ready for shipment";
	public static final String DELIVERING = "delivering";
	public static final String DELIVERED = "delivered";

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<DeliveryList> updateDeliveryScreen(int option) {
		String par4 = null;
		if (option == 1) {
			par4 = "ord.date_time";
		} else {
			par4 = "ord.status DESC";
		}
		TypedQuery<DeliveryList> query = em
				.createQuery(
						"select new edu.bionic.sverkunov.com.DAODB3.classes.DeliveryList(ord.id, ord.date_time, ord.status, SUM(omi.price)) "
								+ "from Orderr ord join Orderrmenuitem omi on omi.orderr_id = ord.id "
								+ "where (ord.status = :par1 OR ord.status = :par2) OR ord.status = :par3 "
								+ "group by ord.date_time, ord.id, ord.status "
								+ "order by " + par4, DeliveryList.class);
		query.setParameter("par1", READY);
		query.setParameter("par2", DELIVERING);
		query.setParameter("par3", DELIVERED);
		return query.getResultList();
	}

	@Override
	public List<String> findCustomerAddress(List<Integer> temp) {
		List<String> result = new ArrayList<String>();
		for (int t : temp) {
			Orderr o = em.find(Orderr.class, t);
			Customer c = o.getCustomer();
			StringBuffer addres = new StringBuffer();
			addres.append(c.getCity()).append(", " + c.getStreet())
					.append(" " + c.getBuilding()).append(", " + c.getApp());
			result.add(addres.toString());
		}
		return result;
	}

	@Override
	public void takeOrder(int orderId) {
		Orderr o = em.find(Orderr.class, orderId);
		o.setStatus(DELIVERING);
	}

	@Override
	public void markAsDelivering(int orderId, int id) {
		Stafforderr s = new Stafforderr();
		s.setOrderr_id(orderId);
		s.setStaff_id(id);
		em.persist(s);
	}

	@Override
	public void done(int orderId) {
		Orderr o = em.find(Orderr.class, orderId);
		o.setStatus(DELIVERED);
	}

	@Override
	public void markAsDelivered(int orderId, int id) {
		Stafforderr s = new Stafforderr();
		s.setOrderr_id(orderId);
		s.setStaff_id(id);
		em.persist(s);
	}

}
