package com.example.demo43;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User implements Serializable {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
	private String country;
	private String bornDate;
	private String profileName;
	private Profile profile;
	private LocalDate signUpDate;
	private UserAbility ability;
	private Direct direct = new Direct();

	public User(){}

	public User(String id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(String id, String firstName, String lastName, String email, String phoneNumber, String password, String country, String bornDate, LocalDate ld) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.country = country;
		this.bornDate = bornDate;
		profile = new Profile();
		signUpDate = ld;
		ability = new UserAbility();

	}
	public User(String id, String firstName, String lastName,String phoneNumber) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.ability = new UserAbility();
	}
	
	public String getID() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getPass() {
		return password;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String getBornDate() {
		return bornDate;
	}
	
	public String getProfileName() {
		return profileName;
	}
	
	public UserAbility getAbility() {
		return ability;
	}
	
	public Profile getProfile() {
		return profile;
	}
	
	public void setFirstName(String first) {
		firstName = first;
	}
	
	public void setLastName(String last) {
		lastName = last;
	}
	
	public void setEmail(String e) {
		email = e;
	}
	
	public void setPhoneNumber(String phone) {
		phoneNumber = phone;
	}

	public void setID(String id) {
		this.id = id;
	}
	
	public void setPassword(String pass) {
		password = pass;
	}
	
	public void setProfileName(String name) {
		profileName = name;
	}
	
	public void setProfile(Profile p) {
		profile = p;
	}
	
	public void setAbility(UserAbility ua) {
		ability = ua;
	}
	
	public void setBornDate(String date) {
		bornDate = date;
	}
	
	public void setCountry(String c) {
		country = c;
	}

	public Direct getDirect() {
		return direct;
	}




	
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String user =  "  " + id + "    " + firstName + "  " + lastName + "  " ;
		return user;
	}
}
