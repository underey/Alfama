package edu.bionic.sverkunov.com.DAODB3.interfaces;

import edu.bionic.sverkunov.com.DAODB3.classes.Dish;

public interface RestaurantAdministratorDAOI {

	public void addDishToMenu(Dish dish, int ms);

	public void editDish(Dish dish);

}
