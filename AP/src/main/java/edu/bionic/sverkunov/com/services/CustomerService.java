package edu.bionic.sverkunov.com.services;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;

import edu.bionic.sverkunov.com.DAODB3.DAO.CustomerDAO;
import edu.bionic.sverkunov.com.DAODB3.classes.Customer;
import edu.bionic.sverkunov.com.DAODB3.classes.Dish;
import edu.bionic.sverkunov.com.DAODB3.classes.Order;
import edu.bionic.sverkunov.com.DAODB3.classes.User;

@Named
public class CustomerService implements CustomerServiceI, Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private CustomerDAO customerDao;

	public CustomerService() {
	}

	@Transactional
	public List<Dish> selectMenusection(int id, int al) {
		List<Dish> dishes = customerDao.selectMenusection(id);
		List<Dish> result = null;
		if (al != 400) {
			result = new ArrayList<Dish>();
			for (Dish d : dishes) {
				if (d.isIsactivated()) {
					result.add(d);
				}
			}
			dishes = result;
		}
		return dishes;
	}

	@Transactional
	public String getMenusectionName(int id) {
		String name = customerDao.getMenusectionName(id).toLowerCase();
		return name;
	}

	public List<Order> buttonAction(List<Order> cartOrder, Order order,
			boolean a) {
		int id = order.getDishId();
		String name = order.getDishName();
		int amount = order.getDishAmount();
		BigDecimal p = new BigDecimal(order.getDishprice() / amount);
		p = p.round(new MathContext(5, RoundingMode.HALF_UP));
		double price = p.doubleValue();

		Order tempOrd = new Order(id, name, price, 1);

		for (Order o : cartOrder) {
			if (o.getDishId() == id) {
				cartOrder.remove(o);
				if (a) {
					tempOrd.setDishAmount(++amount);
				} else {
					tempOrd.setDishAmount(--amount);
				}
				tempOrd.setDishprice(amount * price);
				cartOrder.add(tempOrd);
				Collections.sort(cartOrder, new Order());
				break;
			}
		}
		return cartOrder;
	}

	public double makeTotalPrice(List<Order> cartOrder) {
		double totalAmount = 0.0;
		for (Order o : cartOrder) {
			totalAmount += o.getDishprice();
		}
		return totalAmount;
	}

	@Transactional
	public String signUp(Customer c) {
		Customer customer = c;
		customer.setIsactivated(true);
		String pass = customer.getPassword();
		String hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());
		customer.setPassword(hashedPass);
		customerDao.signUp(customer);
		return "ok";
	}

	public boolean check(String pass) {
		boolean ans = customerDao.check(pass);
		if (ans == true) {
			return true;
		}
		return false;
	}

	public CustomerDAO getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}

	public User signIn(String email, String pass) {
		User u = customerDao.signIn(email);
		if (u == null) {
			return null;
		} else {
			String userPass = u.getPassword();
			boolean flag = BCrypt.checkpw(pass, userPass);
			System.out.println(flag);
			if (flag) {
				return u;
			} else {
				return null;
			}
		}
	}

	@Transactional
	public int submitOrder(List<Order> dishes, Customer c) {
		Map<Integer, Integer> m = new HashMap<>();
		for (Order o : dishes) {
			int id = o.getDishId();
			int am = o.getDishAmount();
			m.put(id, am);
		}

		int id = customerDao.submitOrder(c);
		int counter = customerDao.addToOrderrmenuitem(m, id);
		if (counter == m.size()) {
			customerDao.sendToDelivery(id);
		}
		return id;
	}

	@Override
	public String getWineInfo(int i) {
		String result = customerDao.getDishInfo(i);
		if (result == null) {
			return "Description";
		} else {
			return result;
		}
	}
}
