<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
    <%@page import="com.Paper" %>
    <%@page import="com.Review" %>
    <%@page import="com.Controller" %>
    <%@page import="com.User" %>
     <%Controller cont = new Controller();
     if(session.getAttribute("username")==null){response.sendRedirect("login.jsp");}
    else{
     %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Conference</title>
<%HttpSession sesssion=request.getSession();%>



<meta charset="UTF-8">
    
    <script src="http://s.codepen.io/assets/libs/modernizr.js" type="text/javascript"></script>


 
    <link rel="stylesheet" href="css/styleform.css">
    <link rel="stylesheet" href="css/normalize.css">
<script src="js/prefixfreesearch.min.js"></script>
    <link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css'>

        <link rel="stylesheet" href="css/styleside.css">


<style>

body {
    background: #ffffff url("images/1.png") no-repeat right top;
    background-attachment: fixed;
    
}

header {
  background-color: rgba(0,0,0,0.2);
  height: 35px;
  line-height: 35px;
  padding: 0 10px;
  color: white;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 2
}

</style>
<script type= "text/javascript" src = "js/dd.js"></script>
</head>


<body>

<header>
  Hello <%if(session.getAttribute("name")!=null){%><%=session.getAttribute("name") %><%} %> (<%=session.getAttribute("role") %>)
 </header>


    <div class="box">
  
<nav role='navigation'>
  <ul>
    <li>
      <a href="javascript:history.go(-1)">
        <i class="fa fa-arrow-left fa-fw icon"></i>
        Back
      </a>
    </li>
    <li>
      <a href="home.jsp">
        <i class="fa fa-home fa-fw icon"></i>
        Home
      </a>
    </li>
    <li>
      <a href="Display?fun=logout">
        <i class="fa fa-sign-out fa-fw icon"></i>
        Log Out
      </a>
    </li>
   
  </ul>
</nav>   
</div>


<!-- date -->
<div class="date"> </div>



<br><br>
<%String msg=(String)request.getAttribute("msg"); %>
<%Paper paper=(Paper)request.getAttribute("paper"); %>
   <%if(msg!=null){ %>
   <div align=center><font color=#00FF00 size=4px>
 <%=msg%>   
 </font></div>
 <%} %>
 <br><br>

<div style="margin-left:40%"></div>
<table style="margin-left:40%" >
<tr>
<table  align="center" class="flat-table flat-table-1">


  
<tr>
	<td>Title</td>
   <td><%=paper.getTitle()%></td>
   <td> <span id="name" style="color:red"></span> </td>
</tr>


<tr>
	<td>Author</td>
   <td><%=paper.getAuthor().getFirstName()%></td>
    <td> <span id="description" style="color:red"></span> </td>
</tr>

   
<tr>
	<td>Status</td>
	<td><%=paper.getStatus()%></td>
    <td> <span id="venue" style="color:red"></span> </td>
</tr>
 

<tr>
 	<form name="viewPaper" method="get" action="Display" >
	 <input type="hidden" name="fun" value="viewPaper">
	 <input type="hidden" name="pid" value="<%=paper.getPid()%>">
	<input type="hidden" name="cid" value="<%=paper.getCid()%>">
	 
	<td>View the Paper</td>	
	<td><input type="submit" value="View" ></td>
	</form>
</tr>
<%ArrayList<User> reviewers = paper.getReviewers();
boolean isRev = false;
for(User rev : reviewers){
	if(rev.getId() == Integer.parseInt((String)session.getAttribute("username"))){
		isRev = true;
	}
}
if(isRev == true){
%>
<tr>
	<form name="reviewPaper" method="get" action="Display" >
	 <input type="hidden" name="fun" value="reviewPaper">
	 <input type="hidden" name="pid" value="<%=paper.getPid()%>">
	<input type="hidden" name="cid" value="<%=paper.getCid()%>">
	 
	<td>Review Paper</td>	
	<td><input type="submit" value="Review" ></td>
	</form>
</tr>
<%} %>
<% if(session.getAttribute("role").equals("Admin") || ((Integer.parseInt((String)session.getAttribute("username"))) == cont.getConference(paper.getCid()).getChair().getId())){ %>
<tr>
	<form name="assignReviewer" method="get" action="Display" >
	 <input type="hidden" name="fun" value="assignReviewer">
	 <input type="hidden" name="pid" value="<%=paper.getPid()%>">
	<input type="hidden" name="cid" value="<%=paper.getCid()%>">
	 
	<td>Assign Reviewer</td>	
	<td><input type="submit" value="Assign Reviewer" ></td>
	</form>
</tr>

<tr>
	<form name="approvePaper" method="post" action="Display" >
	 <input type="hidden" name="fun" value="approvePaper">
	 <input type="hidden" name="pid" value="<%=paper.getPid()%>">
	<input type="hidden" name="cid" value="<%=paper.getCid()%>">
	 
	<td>Approve Paper</td>	
	<td><input type="submit" value="Approve" ></td>
	</form>
</tr>
<tr>
	<form name="rejectPaper" method="post" action="Display" >
	 <input type="hidden" name="fun" value="rejectPaper">
	 <input type="hidden" name="pid" value="<%=paper.getPid()%>">
	<input type="hidden" name="cid" value="<%=paper.getCid()%>">
	 
	<td>Reject Paper</td>	
	<td><input type="submit" value="Reject" ></td>
	</form>
</tr>
<%} %>
</table>
<% if(session.getAttribute("role").equals("Admin") || ((Integer.parseInt((String)session.getAttribute("username"))) == cont.getConference(paper.getCid()).getChair().getId())){ %>
<table align="center" class="flat-table flat-table-1" >
<tr><td>Reviewer</td><td>Review</td><td>Rating</td></tr>
<%for(Review review : paper.getReviews()){%>
<tr><td><%=review.getReviewer().getFirstName() %></td><td><%=review.getDescription() %></td><td><%=review.getRating() %></td></tr>
<%} %>
<tr><td>

</table>
<%} %>

<script src="js/indexside.js"></script>
</body>
</html>
<%}  %>