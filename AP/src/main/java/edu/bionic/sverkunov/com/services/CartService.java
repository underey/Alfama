package edu.bionic.sverkunov.com.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.bionic.sverkunov.com.DAODB3.classes.Order;

public class CartService {

	private Map<Integer, Integer> calc = new HashMap<Integer,Integer>();
	private List<Order> preOrders = new ArrayList<Order>();
	private List<Order> postOrders = new ArrayList<Order>();

	public CartService() {
	}

	public void makeCalculation() {
		for (Order o : preOrders) {
			int id = o.getDishId();
			if (calc.containsKey(id)) {
				int i = calc.get(id);
				calc.put(id, ++i);
			} else {
				calc.put(id, o.getDishAmount());
			}
		}
		for (Map.Entry<Integer, Integer> e : calc.entrySet()) {
			int id = e.getKey();
			int amount = e.getValue();
			double price = 0.0;
			String name = null;

			for (Order o : preOrders) {
				if (o.getDishId() == id) {
					price = amount * (o.getDishprice()/o.getDishAmount());
					name = o.getDishName();
					break;
				}
			}
			postOrders.add(new Order(id, name, price, amount));
		}
		Collections.sort(postOrders, new Order());
	}

	public Map<Integer, Integer> getCalc() {
		return calc;
	}

	public void setCalc(Map<Integer, Integer> calc) {
		this.calc = calc;
	}

	public List<Order> getPreOrders() {
		return preOrders;
	}

	public void setPreOrders(List<Order> preOrders) {
		this.preOrders = preOrders;
	}

	public List<Order> getPostOrders() {
		return postOrders;
	}

	public void setPostOrders(List<Order> postOrders) {
		this.postOrders = postOrders;
	}
	
}