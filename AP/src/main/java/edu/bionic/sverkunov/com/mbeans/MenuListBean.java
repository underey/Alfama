package edu.bionic.sverkunov.com.mbeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import edu.bionic.sverkunov.com.DAODB3.classes.Customer;
import edu.bionic.sverkunov.com.DAODB3.classes.Dish;
import edu.bionic.sverkunov.com.DAODB3.classes.Order;
import edu.bionic.sverkunov.com.services.CartService;
import edu.bionic.sverkunov.com.services.CustomerServiceI;

@Named("mlb")
@Scope("session")
public class MenuListBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CustomerServiceI customerService;

	@Inject
	private SignInBean customerBean;

	private List<Dish> dishes;
	private String menuItemId;
	private List<Order> cartOrder;
	private List<Order> receipt;
	private double totalAmount;
	private int orderId;
	private Customer customer;

	public MenuListBean() {
		cartOrder = new ArrayList<Order>();
	}

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}

	public String getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(String menuItemId) {
		this.menuItemId = menuItemId;
	}

	public CustomerServiceI getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerServiceI customerService) {
		this.customerService = customerService;
	}

	public List<Order> getCartOrder() {
		return cartOrder;
	}

	public void setCartOrder(List<Order> cartOrder) {
		this.cartOrder = cartOrder;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public SignInBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(SignInBean customerBean) {
		this.customerBean = customerBean;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public List<Order> getReceipt() {
		return receipt;
	}

	public void setReceipt(List<Order> receipt) {
		this.receipt = receipt;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String generateMenuList() {
		dishes = new ArrayList<Dish>();
		int id = Integer.parseInt(menuItemId);
		dishes = customerService.selectMenusection(id,
				customerBean.getAccessLevel());
		return "sectionPage";
	}

	public String calculateCart() {
		CartService cb = new CartService();
		cb.setPreOrders(cartOrder);
		cb.makeCalculation();
		cartOrder = cb.getPostOrders();
		totalAmount = customerService.makeTotalPrice(cartOrder);

		FacesContext ctx = FacesContext.getCurrentInstance();

		ExternalContext extContext = ctx.getExternalContext();
		String url = extContext.encodeActionURL(ctx.getApplication()
				.getViewHandler().getActionURL(ctx, "/cartPage.xhtml"));
		try {
			extContext.redirect(url);
		} catch (IOException ioe) {
			throw new FacesException(ioe);

		}
		return null;
	}

	public void addOne(Order order) {
		boolean a = true;
		cartOrder = customerService.buttonAction(cartOrder, order, a);
		totalAmount = customerService.makeTotalPrice(cartOrder);
	}

	public void removeOne(Order order) {
		if (order.getDishAmount() == 1) {
			cartOrder.remove(order);
		} else {
			boolean a = false;
			cartOrder = customerService.buttonAction(cartOrder, order, a);
		}
		totalAmount = customerService.makeTotalPrice(cartOrder);
	}

	public String placeOrder(int al) {
		String page = "/sign-inPage.xhtml";
		if (al == 0) {
		} else if (!cartOrder.isEmpty()) {
			customer = customerBean.getCustomer();
			if (customer == null) {
				customer = new Customer();
			}
			page = "/place-orderPage.xhtml";
		}
		FacesContext ctx = FacesContext.getCurrentInstance();

		ExternalContext extContext = ctx.getExternalContext();
		String url = extContext.encodeActionURL(ctx.getApplication()
				.getViewHandler().getActionURL(ctx, page));
		try {
			extContext.redirect(url);
		} catch (IOException ioe) {
			throw new FacesException(ioe);

		}
		return null;
	}

	public void addToCart(Dish dish) {
		Order o = new Order();
		o.setDishId(dish.getId());
		o.setDishName(dish.getName());
		o.setDishAmount(1);
		o.setDishprice(dish.getPrice());
		cartOrder.add(o);
	}

	public String submitOrder() {
		orderId = customerService.submitOrder(cartOrder, customer);
		receipt = cartOrder;
		cartOrder = new ArrayList<Order>();
		return "receiptPage";
	}

	public String getDishInfo(int i) {
		String result = customerService.getWineInfo(i);
		return result;
	}

}
