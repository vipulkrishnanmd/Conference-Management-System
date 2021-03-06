<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
    <%@page import="com.Conference" %>
    <%@page import="com.Paper" %>
     <%if(session.getAttribute("username")==null){response.sendRedirect("login.jsp");}
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
<%Conference con=(Conference)request.getAttribute("con"); %>
   <%if(msg!=null){ %>
   <div align=center><font color=#00FF00 size=4px>
 <%=msg%>   
 </font></div>
 <%} %>
 <br><br>
<form name="updateConference" method="post" action="Display" >
<div style="margin-left:40%"></div>
<table style="margin-left:40%" >
<tr>
<table  align="center" class="flat-table flat-table-1">


  
<tr>
	<td>Name</td>
   <td><input type="Textbox" name="name" id="name" value="<%=con.getName()%>" required></td>
   <td> <span id="name" style="color:red"></span> </td>
</tr>

   
<tr>
	<td>Title</td>
   <td><input type="Textbox" name="title" id="title" value="<%=con.getTitle()%>" required></td>
    <td> <span id="title" style="color:red"></span> </td>
</tr>

<tr>
	<td>Description</td>
   <td><input type="Textbox" name="description" id="description" value="<%=con.getDescription()%>" required></td>
    <td> <span id="description" style="color:red"></span> </td>
</tr>

   
<tr>
	<td>Venue</td>
	<td><input type="Textbox" name="venue" id="venue" value="<%=con.getVenue()%>" required></td>
    <td> <span id="venue" style="color:red"></span> </td>
</tr>

	
<tr>
	<td>Date</td>
    <td><input type="date" name="date" id="date" value="<%=con.getDate()%>" required></td>
      <td> <span id="date" style="color:red"></span> </td>
</tr>

<tr>
	<td>Paper Submission Deadline</td>
    <td><input type="date" name="paperDeadline" id="paperDeadline" value="<%=con.getPaperDeadline()%>" required></td>
      <td> <span id="paperDeadline" style="color:red"></span> </td>
</tr>

<tr>
	<td>Review Submission Deadline</td>
    <td><input type="date" name="reviewDeadline" id="reviewDeadline" value="<%=con.getReviewDeadline()%>" required></td>
      <td> <span id="reviewDeadline" style="color:red"></span> </td>
</tr>

 

<tr>
 
	 <input type="hidden" name="fun" value="updateConference">
	 <input type="hidden" name="cid" value="<%=con.getCid()%>">
	 
	<td><input type="reset" value="Reset"></td>	
	<td><input type="submit" value="Submit" ></td>
</tr>

</table>
</form>

<table align="center" class="flat-table flat-table-1">
<th>
<td> Papers </td>
</th>

<%for(Paper p: con.getPapers()){ %>
<tr>
<td> <%=p.getTitle() %> </td>
<td>
	<font color=black>
	<form id = "two" action="Display" method="post">
	
	<input type="hidden" name="pid" value="<%=p.getPid()%>">
	<input type="hidden" name="cid" value="<%=p.getCid()%>">
      <input type="hidden" name="fun" value="showPaper">
      <input type=submit value="Show Paper">
      </form>
      </font>
      </td>
</tr>
<%}}  %>
</table>
<script src="js/indexside.js"></script>
</body>
</html>