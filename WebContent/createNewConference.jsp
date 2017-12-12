<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
   <%if(msg!=null){ %>
   <div align=center><font color=#00FF00 size=4px>
 <%=msg%>   
 </font></div>
 <%} %>
 <br><br>
<form name="createNewConference" method="post" action="Display" onsubmit="return isDateSelected()">
<div style="margin-left:40%"></div>
<table style="margin-left:40%" >
<tr>
<table  align="center" class="flat-table flat-table-1">


  
<tr>
	<td>Name</td>
   <td><input type="Textbox" name="name" id="name" required></td>
   <td> <span id="name" style="color:red"></span> </td>
</tr>

   
<tr>
	<td>Title</td>
   <td><input type="Textbox" name="title" id="title" required></td>
    <td> <span id="title" style="color:red"></span> </td>
</tr>

<tr>
	<td>Description</td>
   <td><input type="Textbox" name="description" id="description" required></td>
    <td> <span id="description" style="color:red"></span> </td>
</tr>

   
<tr>
	<td>Venue</td>
	<td><input type="Textbox" name="venue" id="venue" required></td>
    <td> <span id="venue" style="color:red"></span> </td>
</tr>

	
<tr>
	<td>Date</td>
    <td><input type="date" name="date" id="date" required></td>
      <td> <span id="date" style="color:red"></span> </td>
</tr>

<tr>
	<td>Paper Submission Deadline</td>
    <td><input type="date" name="paperDeadline" id="paperDeadline" required></td>
      <td> <span id="paperDeadline" style="color:red"></span> </td>
</tr>

<tr>
	<td>Review Submission Deadline</td>
    <td><input type="date" name="reviewDeadline" id="reviewDeadline" required></td>
      <td> <span id="reviewDeadline" style="color:red"></span> </td>
</tr>

 

<tr>
 
	 <input type="hidden" name="fun" value="createNewConference">
	 
	<td><input type="reset" value="Reset"></td>	
	<td><input type="submit" value ="Submit"></td>
</tr>

</table>
</form>

<script src="js/indexside.js"></script>
<script>
function isDateSelected(){ 
    var today =new Date();
    var date = new Date(document.createNewConference.date.value);
    var pdate = new Date(document.createNewConference.paperDeadline.value);
    var rdate = new Date(document.createNewConference.reviewDeadline.value);
    if (date.value == " " || pdate.value == "" || rdate.value == ""){
    	alert("Please enter valid dates");
    	return false;
    } else if (pdate > date) {
    	alert("Paper deadline should be before the conference date");
    	return false;
    } else if (rdate > date) {
    	alert("Review deadline should be before the conference date");
    	return false;
    } else if (pdate > rdate) {
    	alert("Paper deadline should be before the review deadline");
    	return false;
    } else {
    	return true;
    }
}
</script>
</body>

</html>
<%}  %>