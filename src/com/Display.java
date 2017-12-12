package com;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Display
 * @author Vipul Krishnan
 * @version 1.0
 */
@MultipartConfig(maxFileSize = 16177215)
public class Display extends HttpServlet {
	private Controller controller = new Controller();
	private static final long serialVersionUID = 1L;
       
    /**
     * Constructor method for servlet class.
     * @see HttpServlet#HttpServlet()
     */
    public Display() {
        super();
        controller = new Controller();
    }

	/**
	 * Method for get operations
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		String fun=request.getParameter("fun");
		//The variable 'fun' is passed from the calling JSP page.
		//It helps to determine the function that need to be implemented in each call.
		if(fun != null){
		//This code executes for updating the user details.
		if(fun.equals("updateUser")){
			HttpSession session=request.getSession();
			if(((String)session.getAttribute("role")).equals("User")){
				request.setAttribute("user", controller.getUser(Integer.parseInt((String) session.getAttribute("username"))));
				RequestDispatcher rd = request.getRequestDispatcher("updateUser.jsp");
				rd.include(request, response);
			}
			else if(session.getAttribute("role").equals("Admin")){
				System.out.println("hello");
				request.setAttribute("users", controller.getAllUsers());
				RequestDispatcher rd = request.getRequestDispatcher("showAllUsers.jsp");
				rd.include(request, response);
			}
		}
		//This code execute when admin try to update a user
		else if(fun.equals("adminUpdateUser")){
			request.setAttribute("user", controller.getUser(Integer.parseInt((String) request.getParameter("uid"))));
			RequestDispatcher rd = request.getRequestDispatcher("updateUser.jsp");
			rd.include(request, response);
		}
		//This code executes to show the current users papers
		else if(fun.equals("viewMyPapers")){
			request.setAttribute("papers", controller.getMyPapers(Integer.parseInt((String) request.getParameter("uid"))));
			RequestDispatcher rd = request.getRequestDispatcher("showAllPapers.jsp");
			rd.include(request, response);
		}
		//This code executes to show the current user's conferences
		else if(fun.equals("showMyConferences")){
			request.setAttribute("tree", controller.getMyConferences(Integer.parseInt((String) request.getParameter("uid"))));
			RequestDispatcher rd = request.getRequestDispatcher("showingAllConference.jsp");
			rd.include(request, response);
		}
		//This code execute for updating the conference
		else if(fun.equals("updateConference")){
			request.setAttribute("con", controller.getConference(Integer.parseInt((String) request.getParameter("cid"))));
			RequestDispatcher rd = request.getRequestDispatcher("editConference.jsp");
			rd.include(request, response);
		}
		//This code execute for assigning a reviewer for paper
		else if(fun.equals("assignReviewer")){
			int cid = Integer.parseInt((String) request.getParameter("cid"));
			int pid = Integer.parseInt((String) request.getParameter("pid"));
			request.setAttribute("cid", cid);
			request.setAttribute("pid", pid);
			request.setAttribute("users", controller.getAllUsers());
			request.setAttribute("thisPaper", controller.getPaper(cid, pid));
			RequestDispatcher rd = request.getRequestDispatcher("assignReviewer.jsp");
			rd.include(request, response);
		}
		//This code is for showing the inbox to the user
		else if(fun.equals("showInbox")){
			HttpSession session=request.getSession(); 
			request.setAttribute("messages", controller.getAllMessages(Integer.parseInt((String)session.getAttribute("username"))));
			RequestDispatcher rd = request.getRequestDispatcher("inbox.jsp");
			rd.include(request, response);
		}
		
		//code for searching conference
		else if(fun.equals("searchConference"))
		{
	         
	         ArrayList<Conference> conferences = controller.searchConference();
	         request.setAttribute("tree", conferences);
	         request.getRequestDispatcher("showingAllConference.jsp").forward(request, response);
		}
		
		//code for submitting paper
		else if(fun.equals("submitPaper"))
		{
			request.setAttribute("cid", request.getParameter("cid"));
	        request.getRequestDispatcher("createNewPaper.jsp").forward(request, response); 
		}
		
		//code for showing a particular conference
		else if(fun.equals("showConference"))
		{
			System.out.println("Hello" + request.getParameter("cid"));
			request.setAttribute("con", controller.getConference(Integer.parseInt(request.getParameter("cid"))));
	        request.getRequestDispatcher("showConference.jsp").forward(request, response);
		}
		
		//code for showing a particular paper
		else if(fun.equals("showPaper"))
		{
			System.out.println(request.getParameter("pid"));
			System.out.println("number or review " + controller.getPaper(Integer.parseInt(request.getParameter("cid")), Integer.parseInt(request.getParameter("pid"))).getReviews().size());
			request.setAttribute("paper", controller.getPaper(Integer.parseInt(request.getParameter("cid")), Integer.parseInt(request.getParameter("pid"))));
	        request.getRequestDispatcher("showPaper.jsp").forward(request, response);
		}
		
		//code to view a paper
		else if(fun.equals("viewPaper"))
		{
			ServletOutputStream ou = response.getOutputStream();
			System.out.println(request.getParameter("pid"));
			InputStream inputStream = controller.getPdf(Integer.parseInt(request.getParameter("cid")), Integer.parseInt(request.getParameter("pid")));
			byte[] bytes = new byte[1024];

			int read = 0;
			while ((read = inputStream.read(bytes)) != -1) {
				ou.write(bytes, 0, read);

			}
		}
		
		//code to review a paper
		else if(fun.equals("reviewPaper"))
		{
			request.setAttribute("pid", request.getParameter("pid"));
			request.setAttribute("cid", request.getParameter("cid"));
	        request.getRequestDispatcher("reviewPaper.jsp").forward(request, response);
		}
		
		//code to show page for selecting receivers 
		else if(fun.equals("sendMessage"))
		{
			request.setAttribute("users", controller.getAllUsers());
			RequestDispatcher rd = request.getRequestDispatcher("selectReceiver.jsp");
			rd.include(request, response);
		}
		

		
		//code to send message
		else if(fun.equals("sendMessageToUser"))
		{
			int uid = Integer.parseInt((String) request.getParameter("uid"));
			request.setAttribute("receiver", uid);
			RequestDispatcher rd = request.getRequestDispatcher("sendMessage.jsp");
			rd.include(request, response);
		}
		}
		
		//if the 'fun' variable is not submitted by the calling page, or null, application just shows home page
		else{
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.include(request, response);
		}
		}
		catch(Exception e){
			request.setAttribute("title", "Something went wrong :/");
	        request.setAttribute("message", "Sorry for the inconvieience");
	        request.getRequestDispatcher("showMessage.jsp").forward(request, response);
		}
	}

	/**
	 * Method for post operations
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		String fun=request.getParameter("fun");
		//The variable 'fun' is passed from the calling JSP page.
		//It helps to determine the function that need to be implemented in each call.
		
		//Code for loging into the system
		if(fun.equals("login")){
			String userId=request.getParameter("uname");
			String pwd=request.getParameter("pwd");
			boolean isOk = controller.logIn(userId, pwd);
			if(isOk == true){
				HttpSession session=request.getSession();
				session.setAttribute("username",userId);
			    session.setAttribute("role",controller.getUser(Integer.parseInt(userId)).isAdmin() ? "Admin":"User");
			    session.setAttribute("name",controller.getUser(Integer.parseInt(userId)).getFirstName());
			    
				request.setAttribute("empId", "userId");
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				rd.include(request, response);
			}
			else{
				request.setAttribute("msg", "Sorry UserName or Password Error");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.include(request, response);
			}
			
		}
		
		//Code for loging out
		else if(fun.equals("logout"))
		{
	         
	         HttpSession session=request.getSession();  
	         session.invalidate(); 
	         request.setAttribute("msg", "Successfully Logged Out");
	         request.getRequestDispatcher("login.jsp").forward(request, response);
				
		}
		
		//code for creating new conference
		else if(fun.equals("createNewConference"))
		{
			String name=request.getParameter("name"); 
			String title=request.getParameter("title");
			String description=request.getParameter("description");
			String venue=request.getParameter("venue");
			java.sql.Date date=java.sql.Date.valueOf(request.getParameter("date"));
			java.sql.Date paperDeadline=java.sql.Date.valueOf(request.getParameter("paperDeadline"));
			java.sql.Date reviewDeadline=java.sql.Date.valueOf(request.getParameter("reviewDeadline"));
			HttpSession session=request.getSession();
			int uid = Integer.parseInt((String)session.getAttribute("username"));
			if (controller.createNewConference(name, title, description, venue, date, paperDeadline, reviewDeadline, uid) == true){
				
		         request.setAttribute("msg", "Successfully Created the Conference");
		         request.getRequestDispatcher("createNewConference.jsp").forward(request, response);
			}
			else{ 
		         request.setAttribute("msg", "Sorry, Something went wrong!!");
		         request.getRequestDispatcher("createNewConference.jsp").forward(request, response);
			}
				
		}
		
		//code for creating new paper
		else if(fun.equals("createNewPaper"))
		{
			String title=request.getParameter("title"); 
			String keywords=request.getParameter("keywords");
			System.out.println(keywords);
			int cid=Integer.parseInt(request.getParameter("cid"));
			int uid=Integer.parseInt(request.getParameter("uid"));
            InputStream inputStream = null;
			 Part filePart = request.getPart("file");
		        if (filePart != null) {
		            // prints out some information for debugging
		        	System.out.println("hii");
		            System.out.println(filePart.getName());
		            System.out.println(filePart.getSize());
		            System.out.println(filePart.getContentType());
		            
		            // obtains input stream of the upload file
		            inputStream = filePart.getInputStream();
		            
		            }
		        
		        if (controller.createNewPaper(title, keywords, cid, uid, inputStream) == true){
		        	 ArrayList<Integer> recList = new ArrayList<Integer>();
		        	 recList.add(controller.getConference(cid).getChair().getId());
					 controller.newNotification(0, recList, 
							 controller.getUser(uid).getFirstName() + " has submitted a paper in " + controller.getConference(cid).getName());
			         request.setAttribute("msg", "Successfully submitted the paper");
			         request.getRequestDispatcher("createNewPaper.jsp").forward(request, response);
				}
				else{ 
			         request.setAttribute("msg", "Sorry, Something went wrong!!");
			         request.getRequestDispatcher("createNewPaper.jsp").forward(request, response);
				}
		        
		}
		
		//code for inserting review to the database 
		else if(fun.equals("insertReview"))
		{
	        if(controller.insertReview(Integer.parseInt(request.getParameter("pid")),Integer.parseInt(request.getParameter("cid")), (String)request.getParameter("review"), Integer.parseInt(request.getParameter("rating")), Integer.parseInt(request.getParameter("uid")))){
	        	ArrayList<Integer> recList = new ArrayList<Integer>();
	        	 recList.add(controller.getConference(Integer.parseInt(request.getParameter("cid"))).getChair().getId());
				 controller.newNotification(0, recList,controller.getUser(Integer.parseInt(request.getParameter("uid")))+
						 " has submitted a review for the paper " + controller.getPaper(Integer.parseInt(request.getParameter("cid")), Integer.parseInt(request.getParameter("pid"))));
	        	request.setAttribute("title", "Thank you for your Contribution");
		        request.setAttribute("message", "Your review is submitted successfully.");
		        request.getRequestDispatcher("showMessage.jsp").forward(request, response);
	        }
		}
		
		//code for sign up
		else if(fun.equals("signup"))
		{
			
			String[] st =  request.getParameter("expertise").split(",");
			ArrayList<String> exp = new ArrayList<String>();
			for(int i=0; i<st.length; i++){
				exp.add(st[i]);
			}
			int uid = controller.createUser((String)(request.getParameter("fname")),(String)(request.getParameter("lname")), (String)request.getParameter("email"), (String)(request.getParameter("pwd")), (String)(request.getParameter("occupation")), exp);
	        request.setAttribute("msg", "Sign Up Successful");
		    request.getRequestDispatcher("login.jsp").forward(request, response);
	        
		}
		
		//code for updating uer in the database
		else if(fun.equals("updateUserDb")){
			String[] st =  request.getParameter("expertise").split(",");
			ArrayList<String> exp = new ArrayList<String>();
			for(int i=0; i<st.length; i++){
				exp.add(st[i]);
			}
			controller.updateUser((String)(request.getParameter("fname")),(String)(request.getParameter("lname")), (String)request.getParameter("email"), (String)(request.getParameter("password")), (String)(request.getParameter("occupation")), exp);
	        request.setAttribute("title", "Update Successful");
	        request.setAttribute("message", "Successfully updated the profile.");
		    request.getRequestDispatcher("showMessage.jsp").forward(request, response);
		}
		
		//code for deleting(deactivating) a user
		else if(fun.equals("deleteUser")){
			if(controller.deleteUser(Integer.parseInt(request.getParameter("uid")))){
				request.setAttribute("title", "User Deleted");
		        request.setAttribute("message", "Successfully deleted the user");
			    request.getRequestDispatcher("showMessage.jsp").forward(request, response);
			}
		}
		
		//code for updating conference
		else if(fun.equals("updateConference"))
		{
			int cid = Integer.parseInt(request.getParameter("cid"));
			String name=request.getParameter("name"); 
			String title=request.getParameter("title");
			String description=request.getParameter("description");
			String venue=request.getParameter("venue");
			java.sql.Date date=java.sql.Date.valueOf(request.getParameter("date"));
			java.sql.Date paperDeadline=java.sql.Date.valueOf(request.getParameter("paperDeadline"));
			java.sql.Date reviewDeadline=java.sql.Date.valueOf(request.getParameter("reviewDeadline"));
			if (controller.updateConference(cid, name, title, description, venue, date, paperDeadline, reviewDeadline) == true){
				
		         request.setAttribute("title", "Update Successful");
		         request.setAttribute("message", "Successfully updated the conference");
		         request.getRequestDispatcher("showMessage.jsp").forward(request, response);
			}
			else{ 
				request.setAttribute("title", "Update Failed");
		         request.setAttribute("message", "Something went wrong");
		         request.getRequestDispatcher("showMessage.jsp").forward(request, response);
			}
				
		}
		
		//code for deactivating conference
		else if(fun.equals("deactivateConference")){
			int cid = Integer.parseInt(request.getParameter("cid"));
			if (controller.deactivateConference(cid)){
				 request.setAttribute("title", "Deactivated");
		         request.setAttribute("message", "Successfully deactivated the conference");
		         request.getRequestDispatcher("showMessage.jsp").forward(request, response);
			}
			else{ 
				request.setAttribute("title", "Error");
		         request.setAttribute("message", "Something went wrong");
		         request.getRequestDispatcher("showMessage.jsp").forward(request, response);
			}
		}
		
		//code for assigning the selected user as the reviewer
		else if(fun.equals("assignThisUser")){
			HttpSession session=request.getSession();
			int cid = Integer.parseInt((String) request.getParameter("cid"));
			int pid = Integer.parseInt((String) request.getParameter("pid"));
			int uid = Integer.parseInt((String) request.getParameter("uid"));
			if(controller.assignReviewer(uid, cid, pid)){
				ArrayList<Integer> recList = new ArrayList<Integer>();
	        	 recList.add(uid);
				 controller.newNotification(Integer.parseInt((String)session.getAttribute("username")), recList, 
						 "Hi, Could you contribute to the conference: " + controller.getConference(cid).getName() +
				 " by reviewing the paper: " + controller.getPaper(cid, pid).getTitle());
				request.setAttribute("cid", Integer.parseInt((String) request.getParameter("cid")));
				request.setAttribute("pid", Integer.parseInt((String) request.getParameter("pid")));
				request.setAttribute("users", controller.getAllUsers());
				request.setAttribute("thisPaper", controller.getPaper(cid, pid));
				RequestDispatcher rd = request.getRequestDispatcher("assignReviewer.jsp");
				rd.include(request, response);
			}
			else{
				request.setAttribute("title", "Error");
		         request.setAttribute("message", "Something went wrong");
		         request.getRequestDispatcher("showMessage.jsp").forward(request, response);
			}
			
		}
		
		//code for approving paper
		else if(fun.equals("approvePaper")){
			int cid = Integer.parseInt((String) request.getParameter("cid"));
			int pid = Integer.parseInt((String) request.getParameter("pid"));
			if(controller.approvePaper(cid, pid)){
				ArrayList<Integer> recList = new ArrayList<Integer>();
	        	 recList.add(controller.getPaper(cid, pid).getAuthor().getId());
				 controller.newNotification(0, recList, "Congrats! Your paper :" +
	        	 controller.getPaper(cid, pid).getTitle() + " is approved");
				request.setAttribute("title", "Approved");
		        request.setAttribute("message", "Paper approved successfully");
		        request.getRequestDispatcher("showMessage.jsp").forward(request, response);
			}
			else{
				request.setAttribute("title", "Error");
		        request.setAttribute("message", "Something went wrong");
		        request.getRequestDispatcher("showMessage.jsp").forward(request, response);
			}
		}
		
		//code for rejecting paper
		else if(fun.equals("rejectPaper")){
			int cid = Integer.parseInt((String) request.getParameter("cid"));
			int pid = Integer.parseInt((String) request.getParameter("pid"));
			if(controller.approvePaper(cid, pid)){
				ArrayList<Integer> recList = new ArrayList<Integer>();
	        	 recList.add(controller.getPaper(cid, pid).getAuthor().getId());
				 controller.newNotification(0, recList, "Sorry! Your paper :" +
	        	 controller.getPaper(cid, pid).getTitle() + " is rejected");
				request.setAttribute("title", "Rejected");
		        request.setAttribute("message", "Paper rejected");
		        request.getRequestDispatcher("showMessage.jsp").forward(request, response);
			}
			else{
				request.setAttribute("title", "Error");
		        request.setAttribute("message", "Something went wrong");
		        request.getRequestDispatcher("showMessage.jsp").forward(request, response);
			}
		}
		
		//code for implementing auto find feature to find the suitable reviewer
		else if(fun.equals("autoFindRev")){
			int cid = Integer.parseInt((String) request.getParameter("cid"));
			int pid = Integer.parseInt((String) request.getParameter("pid"));
			ArrayList<User>  bestReviewers = controller.getSuitableRev(cid, pid);
			if(bestReviewers != null){
				request.setAttribute("users", bestReviewers);
				request.setAttribute("thisPaper", controller.getPaper(cid, pid));
				request.setAttribute("cid", cid);
				request.setAttribute("pid", pid);
				RequestDispatcher rd = request.getRequestDispatcher("assignReviewer.jsp");
				rd.include(request, response);
			}
			else{
				request.setAttribute("title", "Sorry");
		        request.setAttribute("message", "Couldnt find anyone");
		        request.getRequestDispatcher("showMessage.jsp").forward(request, response);
			}
			
		}
		
		else if(fun.equals("sendMessage")){
			int uid = Integer.parseInt((String) request.getParameter("uid"));
			int receiver = Integer.parseInt((String) request.getParameter("receiver"));
			String message = (String) request.getParameter("message");
			ArrayList<Integer> recList = new ArrayList<Integer>();
       	 	recList.add(receiver);
       	 	controller.newNotification(uid, recList, message);
       	 	request.setAttribute("title", "Message Sent");
	        request.setAttribute("message", "Your message has been sent");
	        request.getRequestDispatcher("showMessage.jsp").forward(request, response);
		}
		}
		catch(Exception e){
			request.setAttribute("title", "Something went wrong :/");
	        request.setAttribute("message", "Sorry for the inconvenience");
	        request.getRequestDispatcher("showMessage.jsp").forward(request, response);
		}
		
	}

}
