package edu.bionic.sverkunov.com.DAODB3.classes;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Staff extends User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String surname;
	private String login;
	private String password;
	private String email;
	private boolean isactivated;

	@ManyToOne
	@JoinColumn(name = "access_id")
	private Accesslevel accesslevel;

	@ManyToMany
	@JoinTable(name = "Stafforderr", joinColumns = @JoinColumn(name = "staff_id"), inverseJoinColumns = @JoinColumn(name = "orderr_id"))
	private Collection<Orderr> orderrs;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection<Orderr> getOrderrs() {
		return orderrs;
	}

	public void setOrderrs(Collection<Orderr> orderrs) {
		this.orderrs = orderrs;
	}

	public boolean isIsactivated() {
		return isactivated;
	}

	public void setIsactivated(boolean isactivated) {
		this.isactivated = isactivated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Accesslevel getAccesslevel() {
		return accesslevel;
	}

	public void setAccesslevel(Accesslevel accesslevel) {
		this.accesslevel = accesslevel;
	}

	@Override
	public String toString() {
		return "surname: " + surname + "\n name: " + name + "\n staff id: "
				+ id + "\n login: " + login + "\n email: " + email
				// + "\n password: " + password
				+ "\n isactivated: " + isactivated + "\n accesslevel:"
				+ accesslevel + "\n" + "\n";
	}

}
