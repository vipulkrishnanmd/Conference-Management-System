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
<form name="insertReview" method="post" action="Display">
<div style="margin-left:40%"></div>
<table style="margin-left:40%" >
<tr>
<table  align="center" class="flat-table flat-table-1">


   
<tr>
	<td>Detailed Review</td>
   <td><textarea rows="5" cols="80" id="review" name="review"></textarea></td>
    <td> <span id="title" style="color:red"></span> </td>
</tr>

   
<tr>
	<td>Rating</td>
	<td><input type="Textbox" name="rating" id="rating" required></td>
    <td> <span id="rating" style="color:red"></span> </td>
</tr>



<tr>
 
	 <input type="hidden" name="fun" value="insertReview">
	  <input type="hidden" name="cid" value="<%=(String)request.getAttribute("cid")%>">
	  <input type="hidden" name="pid" value="<%=(String)request.getAttribute("pid")%>">
	  <input type="hidden" name="uid" value="<%=session.getAttribute("username")%>">
	<td><input type="reset" value="Reset"></td>	
	<td><input type="submit" value="Submit" ></td>
</tr>

</table>
</form>

<script src="js/indexside.js"></script>
</body>
</html>
<%}  %>