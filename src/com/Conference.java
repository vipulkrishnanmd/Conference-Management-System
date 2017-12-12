package com;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class is used to create Conference objects
 * @author Vipul Krishnan
 * @version 1.0
 */
public class Conference {
	private int cid = 0;
	private String name = "";
	private String title = "";
	private String description= "";
	private String venue = "";
	private Date date;
	private Date paperDeadline;
	private Date reviewDeadline;
	private User chair = new User();
	private ArrayList<Paper> papers = new ArrayList<Paper>();
	private String status = "new";
	
	/**
	 * Default constructor
	 */
	public Conference(){
		cid = 0;
		name = "";
		title = "";
		description= "";
		venue = "";
		date = new Date();
		paperDeadline = new Date();
		reviewDeadline = new Date();
		chair = new User();
		papers = new ArrayList<Paper>();
		status = "new";
	}
	/**
	 * Constructor for conference
	 */
	public Conference(int cid, String name, String title, String description, String venue, Date date,
			Date paperDeadline, Date reviewDeadline, User chair, String status) {
		super();
		this.cid = cid;
		this.name = name;
		this.title = title;
		this.description = description;
		this.venue = venue;
		this.date = date;
		this.paperDeadline = paperDeadline;
		this.reviewDeadline = reviewDeadline;
		this.chair = chair;
		this.status = status;
	}
	
	/**
	 * Getter for cid(Conference ID)
	 */
	public int getCid() {
		return cid;
	}
	
	/**
	 * Setter for cid (Conference ID)
	 */
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	/**
	 * Getter for conference description
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
	 * Getter for name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter for name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for title
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
	 * Getter for the venue
	 */
	public String getVenue() {
		return venue;
	}
	
	/**
	 * Setter for the venue
	 */
	public void setVenue(String venue) {
		this.venue = venue;
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
	 * Getter for the paper submission deadline
	 */
	public Date getPaperDeadline() {
		return paperDeadline;
	}
	
	/**
	 * Seetter for the paper submission deadline
	 */
	public void setPaperDeadline(Date paperDeadline) {
		this.paperDeadline = paperDeadline;
	}
	
	/**
	 * Getter for review deadline
	 */
	public Date getReviewDeadline() {
		return reviewDeadline;
	}
	
	/**
	 * Setter for the review deadline
	 */
	public void setReviewDeadline(Date reviewDeadline) {
		this.reviewDeadline = reviewDeadline;
	}
	
	/**
	 * Getter for the chair user object
	 */
	public User getChair() {
		return chair;
	}
	
	/**
	 * Setter for the chair user object
	 */
	public void setChair(User chair) {
		this.chair = chair;
	}
	
	/**
	 * Getter for the list of paper objects
	 */
	public ArrayList<Paper> getPapers() {
		return papers;
	}
	
	/**
	 * Setter for the list of paper objects
	 */
	public void setPapers(ArrayList<Paper> papers) {
		this.papers = papers;
	}
	
	/**
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
	
}
