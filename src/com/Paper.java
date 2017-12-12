package com;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Paper {
	private int pid = 0;
	private int cid = 0;
	private String title = "";
	private ArrayList<String> keywords = new ArrayList<String>();
	private String status = "new";
	private ArrayList<Review> reviews = new ArrayList<Review>();
	private User author = null;
	private ArrayList<User> reviewers = new ArrayList<User>();
	private InputStream inputStream = null;
	
	/**
	 * Default constructor
	 */
	public Paper(){
		pid = 0;
		cid = 0;
		title = "";
		keywords = new ArrayList<String>();
		status = "new";
		reviews = new ArrayList<Review>();
		author = new User();
		reviewers = new ArrayList<User>();
		inputStream = new InputStream() {

			@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
		};
	}
	/**
	 * Constructor method
	 */
	public Paper(int pid, int cid, String title, ArrayList<String> keywords, String status, ArrayList<Review> reviews,
			User author, ArrayList<User> reviewers, InputStream inputStream) {
		super();
		this.pid = pid;
		this.cid = cid;
		this.title = title;
		this.keywords = keywords;
		this.status = status;
		this.reviews = reviews;
		this.author = author;
		this.reviewers = reviewers;
		this.inputStream = inputStream;
	}
	
	/**
	 * Getter for the Conference ID
	 */
	public int getCid() {
		return cid;
	}
	
	/**
	 * Setter for the conference ID
	 */
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	/**
	 * Getter for the paper ID
	 */
	public int getPid() {
		return pid;
	}
	
	/**
	 * Setter for the paper ID
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	/**
	 * Getter for the file stored in as InputStream object
	 */
	public InputStream getInputStream() {
		return inputStream;
	}
	
	/**
	 * Setter for the file stored in as InputStream object
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	/**
	 * Getter for the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Setter for the title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Getter for the keyword list
	 */
	public ArrayList<String> getKeywords() {
		return keywords;
	}
	
	/**
	 * Setter for the keyword list
	 */
	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}
	
	/*
	 * Getter for the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Setter for the status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Getter for the review
	 */
	public ArrayList<Review> getReviews() {
		return reviews;
	}
	
	/**
	 * Setter for the review
	 */
	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}
	
	/**
	 * Getter for the author
	 */
	public User getAuthor() {
		return author;
	}
	
	/**
	 * Setter for the author
	 */
	public void setAuthor(User author) {
		this.author = author;
	}
	
	/**
	 * Getter for reviewers list
	 */
	public ArrayList<User> getReviewers() {
		return reviewers;
	}
	
	/**
	 * Setter for the reviewers list
	 */
	public void setReviewers(ArrayList<User> reviewers) {
		this.reviewers = reviewers;
	}
	
}
