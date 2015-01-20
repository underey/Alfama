package edu.bionic.sverkunov.com.DAODB3.DAO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import edu.bionic.sverkunov.com.DAODB3.classes.Menusection;
import edu.bionic.sverkunov.com.DAODB3.classes.PeriodTotalReport;
import edu.bionic.sverkunov.com.DAODB3.interfaces.BusinessAnalystDAOI;

@Repository
public class BusinessAnalystDAO implements BusinessAnalystDAOI, Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;

	@Override
	public PeriodTotalReport getPeriodTotalReport(Timestamp from,
			Timestamp until) {
		TypedQuery<PeriodTotalReport> query = em
				.createQuery(
						"select new edu.bionic.sverkunov.com.DAODB3.classes.PeriodTotalReport(count(o.id), sum(omi.price))"
								+ " from Orderr o, Orderrmenuitem omi, Dish d where omi.orderr_id = o.id AND omi.dish_id = d.id "
								+ "AND omi.date_time between ?1 AND ?2",
						PeriodTotalReport.class);

		query.setParameter(1, from);
		query.setParameter(2, until);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public PeriodTotalReport getDailyReport(Timestamp from, Timestamp until,
			int menuSection) {
		Menusection ms = em.find(Menusection.class, menuSection);
		TypedQuery<PeriodTotalReport> query = em
				.createQuery(
						"select new edu.bionic.sverkunov.com.DAODB3.classes.PeriodTotalReport(count(o.id), sum(omi.price))"
								+ " from Orderr o join Orderrmenuitem omi on omi.orderr_id = o.id join Dish d on omi.dish_id = d.id where d.menusection = :par1 "
						 + " AND omi.date_time between :par2 AND :par3"
						, PeriodTotalReport.class);
		query.setParameter("par1", ms);
		query.setParameter("par2", from);
		query.setParameter("par3", until);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Menusection> getMenusection() {
		TypedQuery<Menusection> query = em.createQuery(
				"select m from Menusection m", Menusection.class);
		List<Menusection> result = null;

		result = query.getResultList();
		return result;
	}

}
