package edu.bionic.sverkunov.com.services;

import edu.bionic.sverkunov.com.DAODB3.classes.Dish;

public interface RestaurantAdministratorServiceI {

	public int addDishToMenu(Dish dish, int ms);
	
	public void editDish(Dish dish);
}
