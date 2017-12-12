package com;

import java.util.Date;

public class Review {
	private int rid = 0;
	private int pid = 0;
	private String description = "";
	private int rating = 0;
	private User reviewer = new User();
	
	/**
	 * Default constructor
	 */
	public Review(){
		rid = 0;
		pid = 0;
		description = "";
		rating = 0;
		reviewer = new User();
	}
	/**
	 * Constructor for the Review 
	 */
	public Review(int rid, int pid, String description, int rating, User reviewer) {
		super();
		this.rid = rid;
		this.pid = pid;
		this.description = description;
		this.rating = rating;
		this.reviewer = reviewer;
	}
	
	/**
	 * Getter for paper id
	 */
	public int getPid() {
		return pid;
	}
	
	/**
	 * Setter for paper id  
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	/**
	 * Getter for review id
	 */
	public int getRid() {
		return rid;
	}
	
	/**
	 * Setter for review id
	 */
	public void setRid(int rid) {
		this.rid = rid;
	}
	
	/**
	 * Getter for description (Review text)
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Setter for description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Getter for rating
	 */
	public int getRating() {
		return rating;
	}
	
	/**
	 * Setter for rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	/**
	 * Getter for reviewer
	 */
	public User getReviewer() {
		return reviewer;
	}
	
	/**
	 * Setter for reviewer
	 */
	public void setReviewer(User reviewer) {
		this.reviewer = reviewer;
	}	
	
}
