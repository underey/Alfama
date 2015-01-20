package edu.bionic.sverkunov.com.services;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import edu.bionic.sverkunov.com.DAODB3.DAO.RestaurantAdministratorDAO;
import edu.bionic.sverkunov.com.DAODB3.classes.Dish;

@Named
public class RestaurantAdministratorService implements
		RestaurantAdministratorServiceI, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private RestaurantAdministratorDAO raDao;

	public RestaurantAdministratorService() {
	}

	public RestaurantAdministratorDAO getRaDao() {
		return raDao;
	}

	public void setRaDao(RestaurantAdministratorDAO raDao) {
		this.raDao = raDao;
	}

	@Transactional
	public int addDishToMenu(Dish dish, int ms) {
		raDao.addDishToMenu(dish, ms);
		return 1;
	}

	@Transactional
	public void editDish(Dish dish) {
		raDao.editDish(dish);
	}

}
