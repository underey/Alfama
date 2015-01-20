package edu.bionic.sverkunov.com.mbeans;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named("msgs")
@Scope("session")
public class MessageBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String nameRequired = "Name field could not be empty";
	private final String nameConverter = "Name should has only letters";
	private final String nameRange = "Name field lenght between (2 - 15) characters";
	private final String surnameRequired = "Surname field could not be empty";
	private final String surnameConverter = "Surname should has only letters";
	private final String surnameRange = "Surname field lenght between (2 - 15) characters";
	private final String birthdayRequired = "Birthday field could not be empty";
	private final String birthdayConverter = "Birthday field has wrong date format";
	private final String cityRequired = "City field could not be empty";
	private final String cityConverter = "City field should has only letters";
	private final String streetRequired = "Street field could not be empty";
	private final String streetConverter = "Street field should has only letters and white spaces";
	private final String buildingRequired = "Building field could not be empty";
	private final String buildingConverter = "Building field should has only letters, numbers and /";
	private final String appConverter = "App field should has only letters and numbers";
	private final String paymentConverter = "Payment field should has strictly digits";
	private final String phoneRequired = "Phone field could not be empty";
	private final String emailRequired = "Email field could not be empty";
	private final String emailConverter = "Invalid email format";
	private final String emailRange = "Email field lenght at least 6 characters and consist of digits, letters, points or _";
	private final String loginRequired = "Login field could not be empty";
	private final String loginConverter = "Invalid login format. Only digits, letters, points and _ are available";
	private final String loginRange = "Login field lenght between (5 - 12) characters";
	private final String passwordRequired = "Password field could not be empty";
	private final String passwordRange1 = "Password field lenght between (6 - 20) characters and has at least one digit, one upper case letter, one lower case letter";
	private final String passwordRange2 = "Password 1 should match with Password 2";
	private final String activationRequired = "Activated field could not be empty";
	private final String staffroleRequired = "Role field could not be empty";
	private final String phoneEx = "Example: xxx-yyyy-zzz";
	private final String weightRequired = "Weight field could not be empty";
	private final String weightRange = "Weight range should be between 0.005 and 9.999";
	private final String priceRequired = "Price field could not be empty";
	private final String priceConverter = "Invalid price format, reload page";
	private final String priceRange = "Price should be between $0.50 and $10 000.00";
	private final String descriptionRequired = "Description field could not be empty";
	private final String descriptionRange = "Max description length are 200 characters";
	
	public MessageBean() {
	}

	public String getNameRequired() {
		return nameRequired;
	}

	public String getNameConverter() {
		return nameConverter;
	}

	public String getNameRange() {
		return nameRange;
	}

	public String getSurnameRequired() {
		return surnameRequired;
	}

	public String getSurnameConverter() {
		return surnameConverter;
	}

	public String getSurnameRange() {
		return surnameRange;
	}

	public String getBirthdayRequired() {
		return birthdayRequired;
	}

	public String getBirthdayConverter() {
		return birthdayConverter;
	}

	public String getCityRequired() {
		return cityRequired;
	}

	public String getCityConverter() {
		return cityConverter;
	}

	public String getStreetConverter() {
		return streetConverter;
	}

	public String getBuildingConverter() {
		return buildingConverter;
	}

	public String getAppConverter() {
		return appConverter;
	}

	public String getPaymentConverter() {
		return paymentConverter;
	}

	public String getPhoneRequired() {
		return phoneRequired;
	}

	public String getEmailRequired() {
		return emailRequired;
	}

	public String getEmailConverter() {
		return emailConverter;
	}

	public String getLoginRequired() {
		return loginRequired;
	}

	public String getLoginConverter() {
		return loginConverter;
	}

	public String getLoginRange() {
		return loginRange;
	}

	public String getPasswordRequired() {
		return passwordRequired;
	}

	public String getPasswordRange1() {
		return passwordRange1;
	}

	public String getPasswordRange2() {
		return passwordRange2;
	}

	public String getActivationRequired() {
		return activationRequired;
	}

	public String getStaffroleRequired() {
		return staffroleRequired;
	}

	public String getEmailRange() {
		return emailRange;
	}

	public String getPhoneEx() {
		return phoneEx;
	}

	public String getStreetRequired() {
		return streetRequired;
	}

	public String getBuildingRequired() {
		return buildingRequired;
	}

	public String getWeightRequired() {
		return weightRequired;
	}

	public String getWeightRange() {
		return weightRange;
	}

	public String getPriceRequired() {
		return priceRequired;
	}

	public String getPriceConverter() {
		return priceConverter;
	}

	public String getPriceRange() {
		return priceRange;
	}

	public String getDescriptionRequired() {
		return descriptionRequired;
	}

	public String getDescriptionRange() {
		return descriptionRange;
	}

}
