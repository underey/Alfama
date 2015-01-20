package edu.bionic.sverkunov.com.DAODB3.DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import edu.bionic.sverkunov.com.DAODB3.classes.KitchenList;
import edu.bionic.sverkunov.com.DAODB3.classes.Orderr;
import edu.bionic.sverkunov.com.DAODB3.classes.Orderrmenuitem;
import edu.bionic.sverkunov.com.DAODB3.classes.Stafforderr;
import edu.bionic.sverkunov.com.DAODB3.interfaces.KitchenMemberStaffDAOI;

@Repository
public class KitchenMemberStaffDAO implements KitchenMemberStaffDAOI,
		Serializable {

	private static final long serialVersionUID = 1L;
	public static final String KITCHENDONE = "done";
	public static final String READY = "ready for shipment";
	public static final boolean NONKITCHENMADE = false;
	public static final boolean KITCHENMADE = true;
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<KitchenList> updateKitchenScreen() {
		TypedQuery<KitchenList> query = em
				.createQuery(
						"select new edu.bionic.sverkunov.com.DAODB3.classes.KitchenList(omi.id, omi.date_time, d.name) "
								+ "from Orderrmenuitem omi join Dish d on omi.dish_id = d.id "
								+ "where omi.status = 'undone' AND d.is_kitchen_made = :par order by omi.date_time ASC",
						KitchenList.class);
		query.setParameter("par", KITCHENMADE);
		return query.getResultList();
	}

	@Override
	public int done(int orderId) {
		Orderrmenuitem omi = em.find(Orderrmenuitem.class, orderId);
		omi.setStatus(KITCHENDONE);
		return omi.getOrderr_id();
	}

	@Override
	public List<Orderrmenuitem> findOrder(int id, boolean type) {
		TypedQuery<Orderrmenuitem> query = em
				.createQuery(
						"select omi from Orderrmenuitem omi join Dish d on omi.dish_id = d.id "
								+ "where omi.orderr_id = :par AND d.is_kitchen_made = :var",
						Orderrmenuitem.class);
		query.setParameter("par", id);
		query.setParameter("var", type);
		List<Orderrmenuitem> result = query.getResultList();
		if (type == NONKITCHENMADE && result != null) {
			for (Orderrmenuitem omi : result) {
				omi.setStatus(KITCHENDONE);
			}
		}
		return result;
	}

	@Override
	public void sendToDelivery(int id) {
		Orderr orderr = em.find(Orderr.class, id);
		orderr.setStatus(READY);
	}

	@Override
	public void checkPreparedDish(int id, int staffId) {
		Stafforderr s = new Stafforderr();
		s.setOrderr_id(id);
		s.setStaff_id(staffId);
		em.persist(s);
	}

}
