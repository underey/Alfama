package edu.bionic.sverkunov.com.DAODB3.DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import edu.bionic.sverkunov.com.DAODB3.classes.Accesslevel;
import edu.bionic.sverkunov.com.DAODB3.classes.Customer;
import edu.bionic.sverkunov.com.DAODB3.classes.Orderr;
import edu.bionic.sverkunov.com.DAODB3.classes.Staff;
import edu.bionic.sverkunov.com.DAODB3.interfaces.WebSecurityGuardDAOI;

@Repository
public class WebSecurityGuardDAO implements WebSecurityGuardDAOI, Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Staff> getAllStaffBySurname(String surname) {
		TypedQuery<Staff> query = em.createQuery(
				"select s from Staff s where s.surname = :par", Staff.class);

		List<Staff> result = null;
		query.setParameter("par", surname);
		result = query.getResultList();
		return result;
	}

	@Override
	public List<Staff> getAllStaffByRole(int accesslevel) {
		Accesslevel al = getAlevel(accesslevel);
		TypedQuery<Staff> query2 = em
				.createQuery(
						"select s from Staff s where s.accesslevel = :par",
						Staff.class);

		List<Staff> result = null;
		query2.setParameter("par", al);
		result = query2.getResultList();
		return result;
	}

	private Accesslevel getAlevel(int accesslevel) {
		TypedQuery<Accesslevel> query1 = em
				.createQuery(
						"select al from Accesslevel al where al.level_of_access = :par",
						Accesslevel.class);
		query1.setParameter("par", accesslevel);
		return query1.getSingleResult();

	}

	@Override
	public boolean signUp(Staff staff, int accesslevel) {
		Accesslevel al = getAlevel(accesslevel);
		staff.setAccesslevel(al);
		em.persist(staff);
		em.flush();
		return true;
	}

	@Override
	public void editStaff(Staff staff) {
		TypedQuery<Accesslevel> query = em.createQuery(
				"SELECT al FROM Accesslevel al where al.access_desc = :par",
				Accesslevel.class);

		List<Accesslevel> result = null;
		query.setParameter("par", staff.getAccesslevel().getAccess_desc());
		result = query.getResultList();
		Accesslevel al = result.get(0);
		staff.setAccesslevel(al);

		Staff s = em.find(Staff.class, staff.getId());
		s.setName(staff.getName());
		s.setSurname(staff.getSurname());
		s.setLogin(staff.getLogin());
		s.setAccesslevel(staff.getAccesslevel());
		s.setIsactivated(staff.isIsactivated());
	}

	@Override
	public Staff findStaff(String email) {
		TypedQuery<Staff> query = em.createQuery(
				"SELECT s FROM Staff s where s.email = :par", Staff.class);
		query.setParameter("par", email);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void changeStaffPassword(int id, String hashedPass) {
		Staff s = em.find(Staff.class, id);
		s.setPassword(hashedPass);
		em.flush();
	}

	@Override
	public Customer findCustomerByOrder(int orderId) {
		Orderr o = em.find(Orderr.class, orderId);
		if (o == null) {
			return null;
		}
		return o.getCustomer();

	}

	@Override
	public List<Staff> findStaffByOrder(int orderId) {
		TypedQuery<Staff> query = em
				.createQuery(
						"select distinct s from Staff s join Stafforderr so on "
								+ "s.id = so.staff_id "
								+ "where so.orderr_id = :par", Staff.class);
		
		query.setParameter("par", orderId);
		return query.getResultList();
	}
}
