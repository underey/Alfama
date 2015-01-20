package edu.bionic.sverkunov.com.DAODB3.interfaces;

import java.util.List;

import edu.bionic.sverkunov.com.DAODB3.classes.Customer;
import edu.bionic.sverkunov.com.DAODB3.classes.Dish;

public interface CustomerDAOI {

	public List<Dish> selectMenusection(int id);
	
	public String getMenusectionName(int id);
	
	public double getPrice(int id);
	
	public Dish addDishToCart(int id);
	
	public int submitOrder(Customer customer);
	
	public String getDishDescription(int dishId);
	
	public void signUp(Customer customer);
	
	public String getDishInfo(int i);
}

