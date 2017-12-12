package com;

import java.sql.Date;
import java.util.ArrayList;

public class Notification {
	private int nid;
	private Date date;
	private String message = "";
	private User sender;
	private ArrayList<User> recievers;

	/**
	 * Default constructor
	 */
	public Notification(){
		nid = 0;
		date = new Date(0);
		message = "";
		sender = new User();
		recievers = new ArrayList<User>();
	}
	/**
	 * Constructor method
	 */
	public Notification(int nid, Date date, String message, User sender, ArrayList<User> recievers) {
		super();
		this.nid = nid;
		this.date = date;
		this.message = message;
		this.sender = sender;
		this.recievers = recievers;
	}

	/**
	 * Getter for the Notification ID
	 */
	public int getNid() {
		return nid;
	}

	/**
	 * Setter for the Notification ID
	 */
	public void setNid(int nid) {
		this.nid = nid;
	}

	/**
	 * Getter for the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Setter for the date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Getter for the message content
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Setter for the message content
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Getter for the sender
	 */
	public User getSender() {
		return sender;
	}

	/**
	 * Setter for the sender
	 */
	public void setSender(User sender) {
		this.sender = sender;
	}

	/**
	 * Getter for the list of recievers
	 */
	public ArrayList<User> getRecievers() {
		return recievers;
	}

	/**
	 * Setter for the list of users
	 */
	public void setRecievers(ArrayList<User> recievers) {
		this.recievers = recievers;
	}
}
