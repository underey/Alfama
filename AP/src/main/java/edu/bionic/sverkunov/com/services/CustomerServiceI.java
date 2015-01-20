package edu.bionic.sverkunov.com.services;

import java.util.List;

import edu.bionic.sverkunov.com.DAODB3.classes.Customer;
import edu.bionic.sverkunov.com.DAODB3.classes.Dish;
import edu.bionic.sverkunov.com.DAODB3.classes.Order;
import edu.bionic.sverkunov.com.DAODB3.classes.User;

public interface CustomerServiceI {

	public List<Dish> selectMenusection(int id, int accesslevel); 
	
	public String getMenusectionName(int id);
	
	public String signUp(Customer c);
	
	public boolean check(String pass);
	
	public User signIn(String email, String pass);
	
	public int submitOrder(List<Order> dishes, Customer c);
	
	public String getWineInfo(int i);

	public double makeTotalPrice(List<Order> cartOrder);

	public List<Order> buttonAction(List<Order> cartOrder, Order order, boolean a);
}
