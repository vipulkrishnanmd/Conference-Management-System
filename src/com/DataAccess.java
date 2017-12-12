package com;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.*;
/**
 * This is the boundary class which connects the application to the 
 * MySQL database
 * 
 * @author Vipul Krishnan
 * @version 1.0
 *
 */
public class DataAccess {
	private String driver="com.mysql.jdbc.Driver";
	private String url="jdbc:mysql://127.0.0.1:3306/cms";
	private String username="cms";
	private String pass="localhost";
	private Connection con=null;
	private Statement stmt = null;
	
	/**
	 * Default constructor
	 */
	public DataAccess(){
		driver="com.mysql.jdbc.Driver";
		url="jdbc:mysql://127.0.0.1:3306/cms";
		username="cms";
		String pass="localhost";
		Connection con=null;
		Statement stmt = null;
	}
	
	/**
	 * Constructor
	 */
	public DataAccess(String driver, String url, String username, String pass, Connection con, Statement stmt) {
		super();
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.pass = pass;
		this.con = con;
		this.stmt = stmt;
	}

	/**
	 * getter for the driver
	 */
	public String getDriver() {
		return driver;
	}
	/**
	 * Setter for the driver
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}
	/**
	 * getter for the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * setter for the url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * getter for the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * setter for the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * getter for the password
	 */
	public String getPass() {
		return pass;
	}
	/**
	 * setter for the password
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	/**
	 * getter for the connection object
	 */
	public Connection getCon() {
		return con;
	}
	/**
	 * setter for the connection object
	 */
	public void setCon(Connection con) {
		this.con = con;
	}
	/**
	 * getter for the statement
	 */
	public Statement getStmt() {
		return stmt;
	}
	/**
	 * setter for the statement
	 */
	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}
	/**
	 * To get the password in order to perform the login check
	 */
	public String getPassword(String userId){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection(url,username,pass);
			String sql1="select password, active from user where uid=?";
	      	PreparedStatement ps1 = con.prepareStatement(sql1);
	       	ps1.setInt(1,Integer.parseInt(userId));
			ResultSet rs=ps1.executeQuery();
			rs.next();
			
			if(rs.getBoolean(2))
				return rs.getString(1);
			else
				return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
		
	}
	
	/**
	 * To fetch all the conference details including papers, reviews, users etc. from the data base
	 * This function is called many times to update the local conference list, "wholeList", frequently
	 */
	public ArrayList<Conference> getAllConferences(){
		ArrayList<Conference> conferences = new ArrayList<Conference>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection(url,username,pass);
			String sql1="select * from conference";
	      	PreparedStatement ps1 = con.prepareStatement(sql1);
			ResultSet rs=ps1.executeQuery();
			while(rs.next() != false){
				int cid = rs.getInt("cid");
				int uid = rs.getInt("uid");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String venue = rs.getString("venue");
				java.sql.Date date = rs.getDate("date");
				java.sql.Date paperDeadline = rs.getDate("paperDeadline");
				java.sql.Date reviewDeadline = rs.getDate("reviewDeadline");
				String status = rs.getString("status");
				ArrayList<Paper> papers = getPapers(cid);
				User chair = getUser(uid);
				Conference conference = new Conference(cid, name, title, description, venue, date, paperDeadline, reviewDeadline, chair, status);
				conference.setPapers(papers);
				conferences.add(conference);
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conferences;
		
	}
	
	/**
	 * To fetch a paper of a perticular conference from the database
	 */
	public ArrayList<Paper> getPapers(int cid) {
		ArrayList<Paper> papers = new ArrayList<Paper>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection(url,username,pass);
			String sql1="select * from `paper` where cid = ?";
	      	PreparedStatement ps1 = con.prepareStatement(sql1);
	      	ps1.setInt(1,cid);
			ResultSet rs=ps1.executeQuery();
			while(rs.next()){
				int pid = rs.getInt("pid");
				String title = rs.getString("title");
				String combKey = rs.getString("keywords");
				String[] parts = combKey.split(",");
				ArrayList<String> keywords = new ArrayList<String>();
				for(int i=0;i<parts.length;i++){
					keywords.add(parts[i]);
				}
				String status = rs.getString("status");
				User author = getUser(rs.getInt("author"));
				String combRev = rs.getString("reviewers").trim();
				ArrayList<User> reviewers = new ArrayList<User>();
				if(combRev != null && combRev!=""){
				String[] individuals = combRev.split(",");
				System.out.println("The number of reviewers in dba " + individuals.length);
				for(int i=0;i<individuals.length;i++){
					reviewers.add(getUser(Integer.parseInt(individuals[i].trim())));
				}
				}
				ArrayList<Review> reviews = getReviews(pid);
				InputStream inputStream = rs.getBlob("file").getBinaryStream();
				Paper paper = new Paper(pid, cid, title, keywords, status, reviews, author, reviewers, inputStream );
				papers.add(paper);
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return papers;
	}

	/**
	 * To get the reviews for a particular conference from database
	 */
	public ArrayList<Review> getReviews(int pid) {
		ArrayList<Review> reviews = new ArrayList<Review>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection(url,username,pass);
			String sql1="select * from `review` where pid = ?";
	      	PreparedStatement ps1 = con.prepareStatement(sql1);
	      	ps1.setInt(1,pid);
			ResultSet rs=ps1.executeQuery();
			while(rs.next()){
				int rid = rs.getInt("rid");
				String description = rs.getString("description");
				int rating = rs.getInt("rating");
				int uid = rs.getInt("reviewer");
				User reviewer = getUser(uid);
				Review review = new Review(rid, pid, description, rating, reviewer);
				reviews.add(review);
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reviews;
	}
	
	/**
	 * To fetch details and create a user object from database
	 */
	public User getUser(int uid) {
		User user = new User();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection(url,username,pass);
			String sql1="select * from `user` where uid = ?";
	      	PreparedStatement ps1 = con.prepareStatement(sql1);
	      	ps1.setInt(1,uid);
			ResultSet rs=ps1.executeQuery();
			while(rs.next()){
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String occupation = rs.getString("occupation");
				String combExp = rs.getString("expertise");
				String[] parts = combExp.split(",");
				ArrayList<String> expertise = new ArrayList<String>();
				for(int i=0;i<parts.length;i++){
					expertise.add(parts[i]);
				}
				Boolean isadmin = rs.getBoolean("isadmin");
				user = new User(uid, firstname, lastname, password,email,occupation,expertise,isadmin);
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * To add a new conference to database 
	 */
	public boolean addNewConference(Conference conference) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection(url,username,pass);
			String sql1="INSERT INTO `conference` (`cid`, `name`, `title`, `venue`, `date`, `paperDeadline`, `reviewDeadline`, `status`,`uid`, `description`) select max(`cid`)+1, ?,?,?,?,?,?,?,?,? from `conference`";//test
	      	PreparedStatement ps1 = con.prepareStatement(sql1);
	      	ps1.setString(1,conference.getName());
	      	ps1.setString(2,conference.getTitle());
	      	ps1.setString(3,conference.getVenue());
	      	ps1.setDate(4,(Date) conference.getDate());
	      	ps1.setDate(5,(Date) conference.getPaperDeadline());
	      	ps1.setDate(6,(Date) conference.getReviewDeadline());
	      	ps1.setString(7,conference.getStatus());
	      	ps1.setString(9,conference.getDescription());
	      	ps1.setInt(8, conference.getChair().getId());
			ps1.executeUpdate();
			
			return true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * To add a new paper to database
	 */
	public boolean addNewPaper(Paper paper) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection(url,username,pass);
			String sql1="INSERT INTO `paper` (`pid`, `cid`, `title`, `keywords`, `status`, `author`, `file`) select max(`pid`)+1, ?,?,?,?,?,? from `paper`";//test
	      	PreparedStatement ps1 = con.prepareStatement(sql1);
	      	ps1.setInt(1,paper.getCid());
	      	ps1.setString(2,paper.getTitle());
	      	String ke = "";
	      	for(String k:paper.getKeywords()){
	      		ke = ke + k;
	      		ke = ke + ",";
	      	}
	      	ps1.setString(3,ke);
	      	ps1.setString(4,paper.getStatus());
	      	ps1.setInt(5, paper.getAuthor().getId());
	      	ps1.setBlob(6, paper.getInputStream());
			ps1.executeUpdate();
			
			return true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * To add a new review to the database
	 */
	public boolean insertReview(Review review) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection(url,username,pass);
			String sql1="INSERT INTO `review` (`rid`, `pid`, `description`, `rating`, `reviewer`) select max(`rid`)+1, ?,?,?,? from `review`";//test
	      	PreparedStatement ps1 = con.prepareStatement(sql1);
	      	ps1.setInt(1, review.getPid());
	      	ps1.setString(2, review.getDescription());
	      	ps1.setInt(3, review.getRating());
	      	ps1.setInt(4, review.getReviewer().getId());
	      	ps1.executeUpdate();
	      	
			return true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * To add a new user to the database
	 */
	public int addUser(User user) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection(url,username,pass);
			String sql1="INSERT INTO `user` (`uid`, `password`, `firstname`, `lastname`, `email`, `isadmin`, `expertise`, `occupation`) select max(`uid`)+1, ?,?,?,?,?,?,? from `user`";//test
	      	PreparedStatement ps2 = con.prepareStatement(sql1);
	      	System.out.println(user.getPassword());
	      	ps2.setString(1, user.getPassword());
	      	ps2.setString(2, user.getFirstName());
	      	ps2.setString(3, user.getLastName());
	      	ps2.setString(4, user.getEmail());
	      	ps2.setBoolean(5, false);
	      	String exp = "";
	      	for(String s:user.getExpertise()){
	      		exp = exp + s + ",";
	      	}
	      	ps2.setString(6, exp);
	      	ps2.setString(7, user.getOccupation());
	      	ps2.executeUpdate();
	      	
	      	return 1;
	      	
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * To get all users from the database
	 */
	public ArrayList<User> getAllUsers() {
		ArrayList<User> userList = new ArrayList<User>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection(url,username,pass);
			String sql1="select * from `user`";
	      	PreparedStatement ps1 = con.prepareStatement(sql1);
			ResultSet rs=ps1.executeQuery();
			while(rs.next()){
				int uid = rs.getInt("uid");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String occupation = rs.getString("occupation");
				String combExp = rs.getString("expertise");
				String[] parts = combExp.split(",");
				ArrayList<String> expertise = new ArrayList<String>();
				for(int i=0;i<parts.length;i++){
					expertise.add(parts[i]);
				}
				Boolean isadmin = rs.getBoolean("isadmin");
				User user = new User(uid, firstname, lastname, password,email,occupation,expertise,isadmin);
				if(rs.getBoolean("active"))
					userList.add(user);
			
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}
	
	/**
	 * To update a user details in the database
	 */
	public int updateUser(User user) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection(url,username,pass);
			String sql1="UPDATE `user` SET `password`=?, `firstname` = ?, `lastname` = ?,"
					+ "`email` = ?, `isadmin` = ?, `expertise` = ?, `occupation` = ?"
					+ "WHERE `uid`=?";//test
	      	PreparedStatement ps2 = con.prepareStatement(sql1);
	      	System.out.println(user.getPassword());
	      	ps2.setString(1, user.getPassword());
	      	ps2.setString(2, user.getFirstName());
	      	ps2.setString(3, user.getLastName());
	      	ps2.setString(4, user.getEmail());
	      	ps2.setBoolean(5, user.isAdmin());
	      	String exp = "";
	      	for(String s:user.getExpertise()){
	      		exp = exp + s + ",";
	      	}
	      	ps2.setString(6, exp);
	      	ps2.setString(7, user.getOccupation());
	      	ps2.setInt(8, 0);
	      	ps2.executeUpdate();
	      	
	      	return 1;
	      	
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * To delete a user from the database
	 */
	public boolean deleteUser(int uid) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection(url,username,pass);
			String sql1="UPDATE `user` SET `active` = 0 WHERE `uid`=?";
	      	PreparedStatement ps2 = con.prepareStatement(sql1);
	      	ps2.setInt(1, uid);
	      	ps2.executeUpdate();
	      	
	      	return true;	
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * To get all papers submitted by a particular person from the database
	 */
	public ArrayList<Paper> getMyPapers(int uid) {
		ArrayList<Paper> papers = new ArrayList<Paper>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection(url,username,pass);
			String sql1="select * from `paper` where `author` = ?";
	      	PreparedStatement ps1 = con.prepareStatement(sql1);
	      	ps1.setInt(1,uid);
			ResultSet rs=ps1.executeQuery();
			while(rs.next()){
				int pid = rs.getInt("pid");
				String title = rs.getString("title");
				String combKey = rs.getString("keywords");
				String[] parts = combKey.split(",");
				ArrayList<String> keywords = new ArrayList<String>();
				for(int i=0;i<parts.length;i++){
					keywords.add(parts[i]);
				}
				String status = rs.getString("status");
				Integer cid = rs.getInt("cid");
				String combRev = rs.getString("rev");
				ArrayList<User> reviewers = new ArrayList<User>();
				if(combRev != null){
				String[] individuals = combRev.split(",");
				for(int i=0;i<individuals.length;i++){
					reviewers.add(getUser(Integer.parseInt(individuals[i])));
				}
				}
				ArrayList<Review> reviews = getReviews(pid);
				InputStream inputStream = rs.getBlob("file").getBinaryStream();
				Paper paper = new Paper(pid, cid, title, keywords, status, reviews, getUser(uid), reviewers, inputStream );
				papers.add(paper);
			}
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return papers;
	}
	
	/**
	 * To update the details of a conference
	 */
	public boolean updateConference(int cid, String name, String title, String description, String venue, Date date,
			Date paperDeadline, Date reviewDeadline) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection(url,username,pass);
			String sql1="UPDATE `conference` SET `name` = ?, `title` = ?, `venue`= ?, "
					+ "`date`= ?, `paperDeadline`= ?, `reviewDeadline`=?, `description`=?, `status`='new' WHERE `cid` = ?"; 
			PreparedStatement ps1 = con.prepareStatement(sql1);
	      	ps1.setString(1,name);
	      	ps1.setString(2,title);
	      	ps1.setString(3,venue);
	      	ps1.setDate(4,(Date) date);
	      	ps1.setDate(5,(Date) paperDeadline);
	      	ps1.setDate(6,(Date) reviewDeadline);
	      	ps1.setString(7,description);
	      	ps1.setInt(8,cid);
			ps1.executeUpdate();
			
			return true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * To change the status of a conference
	 */
	public boolean setConferenceStatus(String status, int cid) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection(url,username,pass);
			String sql1="UPDATE `conference` SET `status` = ? WHERE `cid` = ?"; 
			PreparedStatement ps1 = con.prepareStatement(sql1);
	      	ps1.setString(1,status);
	      	ps1.setInt(2,cid);
			ps1.executeUpdate();
			
			return true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * To add reviewer for a particular paper
	 */
	public boolean addReviewer(int pid, int uid) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection(url,username,pass);
			String sql1="select `reviewers` from `paper` where pid=?";
	      	PreparedStatement ps1 = con.prepareStatement(sql1);
	       	ps1.setInt(1,pid);
			ResultSet rs=ps1.executeQuery();
			rs.next();
			if(rs.getString(1) != null){
				String sql2="UPDATE `paper` SET `reviewers` = ? WHERE `pid` = ?"; 
				PreparedStatement ps2 = con.prepareStatement(sql2);
				ps2.setString(1,rs.getString(1)+uid+",");
				ps2.setInt(2,pid);
				ps2.executeUpdate();
				
				return true;
			}
			else
				return false;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method sets the paper status "approved" in the datababse
	 */
	public boolean approvePaper(int cid, int pid) {
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		con=DriverManager.getConnection(url,username,pass);
		String sql1="UPDATE `paper` SET `status` = ? WHERE `pid` = ?"; 
		PreparedStatement ps1 = con.prepareStatement(sql1);
      	ps1.setString(1,"approved");
      	ps1.setInt(2,pid);
		ps1.executeUpdate();
		
		return true;
		
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
		return false;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	}
	
	/**
	 * This method sets the paper status "approved" in the datababse
	 */
	public boolean rejectPaper(int cid, int pid) {
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		con=DriverManager.getConnection(url,username,pass);
		String sql1="UPDATE `paper` SET `status` = ? WHERE `pid` = ?"; 
		PreparedStatement ps1 = con.prepareStatement(sql1);
      	ps1.setString(1,"rejected");
      	ps1.setInt(2,pid);
		ps1.executeUpdate();
		
		return true;
		
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
		return false;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	}
	
	/**
	 * To fetch all the messages from the database
	 */
	public ArrayList<Notification> getAllMessages(int uid) {
		ArrayList<Notification> notifications = new ArrayList<Notification>();
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection(url,username,pass);
			String sql1="select * from `notification` ORDER BY date DESC ";
	      	PreparedStatement ps1 = con.prepareStatement(sql1);
			ResultSet rs=ps1.executeQuery();
			while(rs.next() != false){
				flag = false;
				int nid = rs.getInt("nid");
				User sender = getUser(rs.getInt("sender"));
				String message = rs.getString("message");
				java.sql.Date date = rs.getDate("date");
				String combRec = rs.getString("receivers").trim();
				ArrayList<User> receivers = new ArrayList<User>();
				if(combRec != null && combRec!=""){
				String[] individuals = combRec.split(",");
				for(int i=0;i<individuals.length;i++){
					User rev = getUser(Integer.parseInt(individuals[i].trim()));
					if(rev.getId() == uid)
						flag = true;
					receivers.add(rev);
				}
				}
				if(flag == true){
					Notification not = new Notification(nid, date, message, sender, receivers);
					notifications.add(not);
				}
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notifications;
	}
	
	/**
	 * To create a new message 
	 */
	public boolean newNotification(Notification notification) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection(url,username,pass);
			String sql1="INSERT INTO `notification` (`nid`, `date`, `message`, `sender`, `receivers`) select max(`nid`)+1, ?,?,?,? from `notification`";//test
	      	PreparedStatement ps2 = con.prepareStatement(sql1);
	      	ps2.setDate(1, notification.getDate());
	      	ps2.setString(2, notification.getMessage());
	      	ps2.setInt(3, notification.getSender().getId());
	      	String rec = "";
	      	for(User s:notification.getRecievers()){
	      		rec = rec + s.getId() + ",";
	      	}
	      	System.out.println("recList" + rec);
	      	ps2.setString(4, rec);
	      	ps2.executeUpdate();
	      	
	      	return true;
	      	
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * This method compares the keywords of a paper with the expertise of people in the database
	 * returns the list of suitable reviewers for the paper
	 */
	public ArrayList<User> getSuitableRev(ArrayList<String> keywords) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<User> suitableRev = new ArrayList<User>();
		for(String key:keywords){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				con=DriverManager.getConnection(url,username,pass);
				String sql1="select * from `user` where `expertise` like ?";
		      	PreparedStatement ps1 = con.prepareStatement(sql1);
		      	ps1.setString(1,"%"+key.trim()+"%");
		      	System.out.println("%"+key.trim()+"%");
				ResultSet rs=ps1.executeQuery();
				System.out.println("Suitable resultset size " + rs.getFetchSize());
				while(rs.next()){
					int uid = rs.getInt("uid");
					String firstname = rs.getString("firstname");
					String lastname = rs.getString("lastname");
					String email = rs.getString("email");
					String password = rs.getString("password");
					String occupation = rs.getString("occupation");
					String combExp = rs.getString("expertise");
					String[] parts = combExp.split(",");
					ArrayList<String> expertise = new ArrayList<String>();
					for(int i=0;i<parts.length;i++){
						expertise.add(parts[i]);
					}
					Boolean isadmin = rs.getBoolean("isadmin");
					boolean flag = false;
					for(int  i : list){
						if(i==uid){
							flag = true;
						}
					}
					if(!flag){
						User user = new User(uid, firstname, lastname, password,email,occupation,expertise,isadmin);
						suitableRev.add(user);
						list.add(uid);
					}
				}
				
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return suitableRev;
	}
	
}
