package edu.bionic.sverkunov.com.DAODB3.DAO;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import edu.bionic.sverkunov.com.DAODB3.classes.Dish;
import edu.bionic.sverkunov.com.DAODB3.classes.Menusection;
import edu.bionic.sverkunov.com.DAODB3.interfaces.RestaurantAdministratorDAOI;

@Repository
public class RestaurantAdministratorDAO implements RestaurantAdministratorDAOI, Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;

	@Override
	public void addDishToMenu(Dish dish, int ms) {
		Menusection m = em.find(Menusection.class, ms);
		dish.setMenusection(m);
		em.persist(dish);
	}

	@Override
	public void editDish(Dish dish) {
		int dishId = dish.getId();
		Dish d = em.find(Dish.class, dishId);
		
		d.setName(dish.getName());
		d.setWeight(dish.getWeight());
		d.setPrice(dish.getPrice());
		d.setDescription(dish.getDescription());
		d.setIs_kitchen_made(dish.isIs_kitchen_made());
		d.setMenusection(dish.getMenusection());
		d.setIsactivated(dish.isIsactivated());
		d.setCover(dish.getCover());
	}

}
