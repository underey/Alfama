package edu.bionic.sverkunov.com.DAODB3.classes;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Menusection implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
		
	@OneToMany(mappedBy="menusection", fetch=FetchType.LAZY)
	private Collection<Dish> dishes;
	
	public Collection<Dish> getDishes() {
		return dishes;
	}
	public void setDishes(Collection<Dish> dishes) {
		this.dishes = dishes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
