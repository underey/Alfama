package edu.bionic.sverkunov.com.mbeans;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.springframework.context.annotation.Scope;

import edu.bionic.sverkunov.com.DAODB3.classes.Dish;
import edu.bionic.sverkunov.com.services.RestaurantAdministratorServiceI;

@Named("rab")
@Scope("request")
public class RestaurantAdministratorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private RestaurantAdministratorServiceI ras;

	private Dish dish;
	private int menusectionId;

	public RestaurantAdministratorBean() {
		dish = new Dish();
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public int getMenusectionId() {
		return menusectionId;
	}

	public void setMenusectionId(int menusectionId) {
		this.menusectionId = menusectionId;
	}

	public RestaurantAdministratorServiceI getRas() {
		return ras;
	}

	public void setRas(RestaurantAdministratorServiceI ras) {
		this.ras = ras;
	}

	public void handleFileUpload(FileUploadEvent event) {

	}

	public String insert() {
		String page = "addDishRestAdmPage";
		boolean f = check(dish);

		if (f) {
			ras.addDishToMenu(dish, menusectionId);
			dish = new Dish();
		}
		return page;
	}

	public void editDish(Dish d) {
		boolean f = check(d);
		if (f) {
			ras.editDish(d);
		}
	}

	private boolean check(Dish d) {
		boolean flag = true;
		if (d.getDescription() == null
				|| d.getDescription().trim().length() == 0
				|| d.getName() == null
				|| d.getName().trim().length() == 0 
				|| d.getPrice() < 0.5) {
			flag = false;
		}
		return flag;
	}
}
