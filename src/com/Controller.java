package com;

import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

/**
 * This class act as the center class for all operations.
 * Controls all other classes.
 * Implements important logic operations
 * 
 * @author Vipul Krishnan
 * @version 1.0
 *
 */
public class Controller {
	DataAccess da = new DataAccess();
	ArrayList<Conference> wholeList = new ArrayList<Conference>();
	
	/**
	 * Default constructor
	 */
	public Controller(){
		da = new DataAccess();
		wholeList = new ArrayList<Conference>();
	}
	
	/**
	 * Constructor
	 */
	public Controller(ArrayList<Conference> wholeList){
		this.wholeList = wholeList;
	}
	
	/**
	 * getter for the list all conferences containing all other associates objects
	 * @return wholeList
	 */
	public ArrayList<Conference> getWholeList() {
		return wholeList;
	}
	
	/**
	 * setter for the wholeList
	 * 
	 */
	public void setWholeList(ArrayList<Conference> wholeList) {
		this.wholeList = wholeList;
	}
	
	/**
	 * This function implements the actual logic behind the login operation
	 * 
	 */
	public boolean logIn(String userId, String pwd){
		String realPwd = da.getPassword(userId);
		System.out.println(realPwd);
		if(realPwd == null)
			return false;
		if(realPwd.equals(pwd))
			return true;
		else
			return false;
		
	}
	
	/**
	 * This function calls the dataAccess object and updates the wholelist
	 * also returns the wholeList
	 * 
	 */
	public ArrayList<Conference> searchConference(){
		wholeList = da.getAllConferences();
		System.out.println(wholeList.size());
		return wholeList;
	}
	
	/**
	 * This function adds a new conference to the list of conferences and database
	 * 
	 */
	public boolean createNewConference(String name, String title, String description, String venue, Date date, Date paperDeadline,
			Date reviewDeadline, int uid) {
		wholeList = da.getAllConferences();
		//adding the new conference to local list
		System.out.println("wholeList length = " + wholeList.size());
		Conference conference = new Conference((wholeList.get(wholeList.size()-1).getCid()+1), name, title, description, venue, date, paperDeadline, reviewDeadline, da.getUser(uid), "new");//test
		wholeList.add(conference);
		return da.addNewConference(conference);
		
	}
	
	/**
	 * This method adds a new paper in the particular conference
	 */
	public boolean createNewPaper(String title, String keywords, int cid, int uid, InputStream inputStream) {
		String[] keys = keywords.split(",");
		ArrayList<String> keyw = new ArrayList<String>();
		for(int i=0;i<keys.length;i++){
			keyw.add(keys[i]);
		}
		Paper paper = new Paper(0, cid, title,keyw, "new", new ArrayList<Review>(), da.getUser(uid),new ArrayList<User>(),inputStream);
		for(Conference con : wholeList){
			if(con.getCid() == cid){
				con.getPapers().add(paper);
			}
		}
		return da.addNewPaper(paper);
	}
	
	/**
	 * This method accepts the conference id and returns the corresponding conferernce object
	 */
	public Conference getConference(int i) {
		wholeList = da.getAllConferences();
		for(Conference con: wholeList){
			if(con.getCid() == i){
				return con;
			}
		}
		return null;
	}
	
	/**
	 * This method accepts the Paper ID and Conference ID and fetches the corresponding paper object
	 * Taking conference id as input decreases the search time
	 */
	public Paper getPaper(int cid, int pid) {
		wholeList = da.getAllConferences();
		Paper r = null;
		for(Conference con: wholeList){
			if(con.getCid() == cid){
				for(Paper p:con.getPapers()){
					System.out.println("inside getpaper fun :" + p.getReviews().size());
					if(p.getPid() == pid){
						r = p;
					}
				}
			}
		}
		return r;
	}
	
	/**
	 * This method accepts the conference id and paper id, and returns the paper file
	 * Files are stored and transfered as an InputStream objects
	 */
	public InputStream getPdf(int cid, int pid) {
		wholeList = da.getAllConferences();
		for(Conference con: wholeList){
			if(con.getCid() == cid){
				for(Paper p:con.getPapers()){
					if(p.getPid() == pid){
						return p.getInputStream();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * This function inserts a review in a paper object
	 */
	public boolean insertReview(int pid, int cid, String description, int rating, int uid) {
		Review review = new Review(0, pid, description, rating, da.getUser(uid));
		for(Conference con: wholeList){
			if(con.getCid() == cid){
				for(Paper p:con.getPapers()){
					if(p.getPid() == pid){
						p.getReviews().add(review);
					}
				}
			}
		}
		return da.insertReview(review);
	}
	
	/**
	 * This function creates a new user with the details given
	 */
	public int createUser(String fname, String lname, String email, String password, String occupation, ArrayList<String> exp) {
		User user = new User(0,fname,lname,password,email,occupation,exp, false);
		System.out.println(password);
		return da.addUser(user);
	}
	
	/**
	 * This method returns the User object corresponding to the input ID
	 */
	public User getUser(int uid){
		return da.getUser(uid);
	}
	
	/**
	 * This method return the list of all users in the system
	 */
	public ArrayList<User> getAllUsers() {
		return da.getAllUsers();
	}

	/**
	 * This method is used to update the details of a  particular user
	 */
	public int updateUser(String fname, String lname, String email, String password, String occupation, ArrayList<String> exp){
		User user = new User(0,fname,lname,password,email,occupation,exp, false);
		System.out.println(password);
		return da.updateUser(user);
	}
	
	/**
	 * This method is used to remove a user from the system
	 * However the details remains, but will be marked as deactivated
	 */
	public boolean deleteUser(int uid) {
		return da.deleteUser(uid);
	}
	
	/**
	 * To get Papers submitted by a particular person
	 */
	public ArrayList<Paper> getMyPapers(int uid) {
		return da.getMyPapers(uid);
	}

	/**
	 * To get all conferences  created by a person
	 */
	public ArrayList<Conference> getMyConferences(int uid) {
		wholeList = da.getAllConferences();
		ArrayList<Conference> myCon = new ArrayList<Conference>();
		for(Conference con: wholeList){
			if(con.getChair().getId() == uid){
				myCon.add(con);
			}
		}
		return myCon;
	}
	
	/**
	 * To update a conference
	 */
	public boolean updateConference(int cid, String name, String title, String description, String venue, Date date,
			Date paperDeadline, Date reviewDeadline) {
		return da.updateConference(cid, name, title, description, venue, date, paperDeadline, reviewDeadline);
	}
	
	/**
	 * To deactivate a conference
	 */
	public boolean deactivateConference(int cid) {
		System.out.println(cid);
		for(Conference con:wholeList){
			if(con.getCid() == cid){
				System.out.println("reaching at deactivateCOnference");
				con.setStatus("deactivated");
			}
		}
		return da.setConferenceStatus("deactivated", cid);
	}

	/**
	 * To assign reviewer for a paper
	 */
	public boolean assignReviewer(int uid, int cid, int pid) {
		wholeList = da.getAllConferences();
		for(Conference con:wholeList){
			if(con.getCid() == cid){
				for(Paper p:con.getPapers()){
					if(p.getPid() == pid){
						if(p.getReviewers().size()<4){
							p.getReviewers().add(getUser(uid));
							return da.addReviewer(pid, uid);
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * To approve paper
	 */
	public boolean approvePaper(int cid, int pid) {
		wholeList = da.getAllConferences();
		for(Conference con:wholeList){
			if(con.getCid() == cid){
				for(Paper p:con.getPapers()){
					if(p.getPid() == pid){
						p.setStatus("approved");
						return da.approvePaper(cid,pid);
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * To reject paper
	 */
	public boolean rejectPaper(int cid, int pid) {
		wholeList = da.getAllConferences();
		for(Conference con:wholeList){
			if(con.getCid() == cid){
				for(Paper p:con.getPapers()){
					if(p.getPid() == pid){
						p.setStatus("rejected");
						return da.rejectPaper(cid,pid);
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * TO get all messages for a user
	 */
	public ArrayList<Notification> getAllMessages(int uid) {
		return da.getAllMessages(uid);
	}
	
	/**
	 * To create a new notification
	 */
	public boolean newNotification(int sender, ArrayList<Integer> receivers, String message){
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		ArrayList<User> r = new ArrayList<User>();
		for(int rec:receivers){
			System.out.println("one user added to recList");
			r.add(da.getUser(rec));
		}
		System.out.println("recList" + r);
		return da.newNotification(new Notification(0, sqlDate, message, da.getUser(sender), r));
		
	}

	/**
	 * To automatically find the suitable reviewer for a paper
	 * Works by comparing the expertise of user and keywords of the paper
	 */
	public ArrayList<User> getSuitableRev(int cid, int pid) {
		wholeList = da.getAllConferences();
		for(Conference con:wholeList){
			if(con.getCid() == cid){
				for(Paper p:con.getPapers()){
					if(p.getPid() == pid){
						return da.getSuitableRev(p.getKeywords());
					}
				}
			}
		}
		return null;
	}
}
