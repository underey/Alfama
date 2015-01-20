package edu.bionic.sverkunov.com.DAODB3.DAO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import edu.bionic.sverkunov.com.DAODB3.classes.Accesslevel;
import edu.bionic.sverkunov.com.DAODB3.classes.Customer;
import edu.bionic.sverkunov.com.DAODB3.classes.Dish;
import edu.bionic.sverkunov.com.DAODB3.classes.Menusection;
import edu.bionic.sverkunov.com.DAODB3.classes.Orderr;
import edu.bionic.sverkunov.com.DAODB3.classes.Orderrmenuitem;
import edu.bionic.sverkunov.com.DAODB3.classes.Staff;
import edu.bionic.sverkunov.com.DAODB3.classes.User;
import edu.bionic.sverkunov.com.DAODB3.interfaces.CustomerDAOI;

@Repository
public class CustomerDAO implements CustomerDAOI, Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	public static final String KITCHENDONE = "done";
	public static final String READY = "ready for shipment";

	@Override
	public List<Dish> selectMenusection(int id) {
		Menusection m = em.find(Menusection.class, id);
		TypedQuery<Dish> query = em.createQuery(
				"SELECT d FROM Dish d where d.menusection = :par", Dish.class);

		List<Dish> result = null;
		query.setParameter("par", m);
		result = query.getResultList();
		return result;

	}

	@Override
	public String getMenusectionName(int id) {
		TypedQuery<Menusection> query = em.createQuery(
				"SELECT m FROM Menusection m where m.id = :par",
				Menusection.class);
		String res = null;

		List<Menusection> result = null;
		query.setParameter("par", id);
		result = query.getResultList();

		for (Menusection m : result) {
			res = m.getName();
			break;
		}
		return res;

	}

	@Override
	public double getPrice(int id) {

		Dish dish = em.find(Dish.class, id);
		double result = dish.getPrice();
		return result;
	}

	@Override
	public Dish addDishToCart(int id) {
		Dish dish = null;
		dish = em.find(Dish.class, id);
		return dish;
	}

	public int submitOrder(Customer customer) {
		int al = customer.getAccesslevel().getLevel_of_access();
		if (al == 0) {
			em.persist(customer);
		} else {
			em.merge(customer);
		}
		return addToOrderr(customer);
	}

	public int addToOrderrmenuitem(Map<Integer, Integer> order, int orderrId) {
		int counter = 0;
		for (Map.Entry<Integer, Integer> entry : order.entrySet()) {
			int temp = entry.getValue();
			Dish d = null;
			while (temp != 0) {
				Orderrmenuitem ord = new Orderrmenuitem();

				d = em.find(Dish.class, entry.getKey());
				int dishId = d.getId();
				double price = d.getPrice();
				long time = System.currentTimeMillis();
				Timestamp timestamp = new java.sql.Timestamp(time);
				ord.setStatus("undone");
				ord.setDate_time(timestamp);
				ord.setPrice(price);
				ord.setOrderr_id(orderrId);
				ord.setDish_id(dishId);

				em.persist(ord);
				em.flush();
				temp--;
			}
			if (!d.isIs_kitchen_made()) {
				counter++;
			}
		}
		return counter;
	}

	private int addToOrderr(Customer customer) {
		Orderr orderr = new Orderr();
		long time = System.currentTimeMillis();
		Timestamp timestamp = new java.sql.Timestamp(time);

		orderr.setStatus("undone");
		orderr.setCustomer(customer);
		orderr.setDate_time(timestamp);

		em.persist(orderr);
		em.flush();
		return orderr.getId();
	}

	@Override
	public String getDishDescription(int dishId) {
		String description = null;
		Dish dish = em.find(Dish.class, dishId);
		description = dish.getDescription();
		return description;
	}

	@Override
	public void signUp(Customer customer) {
		Customer c = customer;
		Accesslevel al = em.find(Accesslevel.class, 2);
		c.setAccesslevel(al);
		em.persist(c);
	}

	public boolean check(String email) {
		TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c",
				Customer.class);

		List<Customer> result = null;
		result = query.getResultList();
		for (Customer c : result) {
			if (c.getEmail().equals(email)) {
				return false;
			}
		}
		return true;
	}

	public User signIn(String email) {
		TypedQuery<Customer> query1 = em
				.createQuery(
						"SELECT c FROM Customer c where c.email = :par AND c.isactivated = 'true'",
						Customer.class);

		List<Customer> result1 = null;
		query1.setParameter("par", email);
		result1 = query1.getResultList();
		if (result1.isEmpty()) {
		} else {
			Customer c = result1.get(0);
			return c;
		}

		TypedQuery<Staff> query2 = em
				.createQuery(
						"SELECT s FROM Staff s where s.email = :par AND s.isactivated = 'true'",
						Staff.class);
		List<Staff> result2 = null;
		query2.setParameter("par", email);
		result2 = query2.getResultList();
		if (result2.isEmpty()) {
			return null;
		} else {
			Staff s = result2.get(0);
			return s;
		}
	}

	@Override
	public String getDishInfo(int id) {
		Menusection m = em.find(Menusection.class, id);
		TypedQuery<String> query = em.createQuery(
				"SELECT d.description FROM Dish d where d.menusection = :par",
				String.class);
		List<String> list = null;
		query.setParameter("par", m);
		list = query.getResultList();
		int size = list.size();
		size = (int) (Math.random() * size / 2);
		String result = list.get(size);
		return result;
	}

	public void sendToDelivery(int id) {
		TypedQuery<Orderrmenuitem> query = em.createQuery(
				"select omi from Orderrmenuitem omi join Dish d on omi.dish_id = d.id "
						+ "where omi.orderr_id = :par", Orderrmenuitem.class);
		query.setParameter("par", id);
		List<Orderrmenuitem> result = query.getResultList();
		for (Orderrmenuitem o : result) {
			o.setStatus(KITCHENDONE);
		}

		Orderr o = em.find(Orderr.class, id);
		o.setStatus(READY);
	}

}
