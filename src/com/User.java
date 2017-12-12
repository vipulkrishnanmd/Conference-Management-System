package com;

import java.util.ArrayList;

public class User {
	int id = 0;
	String firstName = "";
	String lastName = "";
	String password = "";
	String email = "";
	String occupation = "";
	ArrayList<String> expertise = new ArrayList();
	boolean isAdmin = false;
	
	/**
	 * Constructor 
	 */
	public User(int id, String firstName, String lastName, String password, String email, String occupation,
			ArrayList<String> expertise, boolean isAdmin) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.occupation = occupation;
		this.expertise = expertise;
		this.isAdmin = isAdmin;
	}
	
	/**
	 * Default constructor
	 */
	public User() {
		id = 0;
		firstName = "";
		lastName = "";
		password = "";
		email = "";
		occupation = "";
		expertise = new ArrayList();
		isAdmin = false;
	}
	
	/**
	 * Getter for ID
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter for ID
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter for first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Setter for first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Getter for last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Setter for last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Getter for password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Setter for password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Getter for email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Setter for email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gette for occupation
	 */
	public String getOccupation() {
		return occupation;
	}
	
	/**
	 * Setter for occupation
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	/**
	 * Getter for expertise
	 */
	public ArrayList<String> getExpertise() {
		return expertise;
	}
	
	/**
	 * Setter for expertise
	 */
	public void setExpertise(ArrayList<String> expertise) {
		this.expertise = expertise;
	}
	
	/**
	 * getter for isAdmin
	 * Return true is the user is admin
	 */
	public boolean isAdmin() {
		return isAdmin;
	}
	
	/**
	 * Setter for isAdmin
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
