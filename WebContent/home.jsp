

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%if(session.getAttribute("username")==null){response.sendRedirect("login.jsp");}
    else{
     %>
    
<!DOCTYPE html>
<html>
<head>
<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
 <style>
.button {
  position: relative;
  display: block;
  width: 75%;
  margin: 10px auto;
  padding: 10px 10px;
  border-radius: 50px;
  box-shadow: 0 5px 0 0 #e9edee, 0 5px 0 2px #ccd2d2, 0 12px 20px 6px #d5dbdb;
  text-align: center;
  color: dimgrey;
  text-decoration: none;
  font-weight: bold;
  font-size: 24px;
  -webkit-tap-highlight-color: transparent;
}
.button:active {
  top: 5px;
  box-shadow: 0 1px 0 2px #ccd2d2, 0 7px 20px 6px #d5dbdb;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Conference Management System</title>

  <link rel="stylesheet" href="css/demo-styles.css" />
  <script src="js/modernizr-1.5.min.js"></script>
 <script src="js/indexbutton.js"></script>
 
</head>

<body>
 <header>
  Hello <%=session.getAttribute("name") %> (<%=session.getAttribute("role") %>)
 </header>
 
<div class="demo-wrapper">
  <div class="s-page random-restored-page">
    <h2 class="page-title">Some minimized App</h2>
    <a href="home.jsp"><div class="close-button s-close-button">x</div></a>
  </div>
	<div class="r-page andom-r-page">

    <div class="page-content">
      <h2 class="page-title">Update My Profile</h2>
      <p>
      <%if(session.getAttribute("role").equals("Admin")){ %>
         <form action="Display" method="get"> 
<input type="hidden" name="fun" value="updateUser">
<button class="button" onclick="submit">Show all Users</button></form>
    <%} else{ %>
      <form action="Display" method="get"> 
<input type="hidden" name="fun" value="updateUser">
<button class="button" onclick="submit">Update Now</button></form>
	<%} %>
		
      </p>
    </div>
<a href="home.jsp"><div class="close-button r-close-button">x</div></a>
  </div>


<div class="r-page work-r-page">

    <div class="page-content">
      <h2 class="page-title">Conferences</h2><br><br>
      <p>
    
  <form action="Display" method="get">
<input type="hidden" name="fun" value="searchConference">
<button class="button" onclick="submit">Search Conference</button><br>
</form>

<form action="createNewConference.jsp" method="get">
<input type="hidden" name="fun" value="createNewConference.jsp">
<button class="button" onclick="submit">Create New Conference</button><br>
</form>
<form action="Display" method="get">
<input type="hidden" name="fun" value="showMyConferences">
<input type="hidden" name="uid" value="<%=session.getAttribute("username")%>">
<button class="button" onclick="submit">Show My Conferences</button><br>
</form>     

      </p>
		
      
    </div>
<a href="home.jsp"><div class="close-button r-close-button">x</div></a>
  </div>
  
  
  <div class="r-page bill-r-page">

    <div class="page-content">
      <h2 class="page-title">Reviews</h2><br><br><br>
       <p>
      </p>
    </div>
<a href="home.jsp"><div class="close-button r-close-button">x</div></a>
  </div>
  
  
  
  <div class="r-page about-r-page">

    <div class="page-content">
      <h2 class="page-title">About your Company</h2><br><br><br>
       <p>Add details about the company
       
       
        </p>
    </div>
<a href="home.jsp"><div class="close-button r-close-button">x</div></a>
  </div>
  
  
  
  <div class="r-page help-r-page">

    <div class="page-content">
      <h2 class="page-title">Help</h2><br><br><br>
       <p>
      <b>Functions:</b>
      <br>
      <br>
      <br>
      Under construction    
        </p>
    </div>
<a href="home.jsp"><div class="close-button r-close-button">x</div></a>
  </div>
  
  
  
    <div class="r-page other-r-page">

    <div class="page-content">
      <h2 class="page-title">Others</h2><br><br><br>
       <p>
       Some other items
       
        </p>
    </div>
<a href="home.jsp"><div class="close-button r-close-button">x</div></a>
  </div>
       
  
  
    
  <div class="r-page employee-r-page">

    <div class="page-content">
      <h2 class="page-title">Messages</h2><br><br><br>
             <p>
<form action="Display" method="get">
<input type="hidden" name="fun" value="showInbox">
<button class="button" onclick="submit">Inbox</button><br>
</form>
<form action="Display" method="get">
<input type="hidden" name="fun" value="sendMessage">
<button class="button" onclick="submit">Send Message</button><br>
</form>
             </p>
    </div>
<a href="home.jsp"><div class="close-button r-close-button">x</div></a>
  </div>
  
  
  



<div class="r-page random-r-page">

    <div class="page-content">
      <h2 class="page-title"><b>Papers</b></h2>
      <p>
      
    <form action="Display" method="get">
<input type="hidden" name="fun" value="viewMyPapers">
<input type="hidden" name="uid" value="<%=session.getAttribute("username")%>">
<button class="button" onclick="submit">View My Papers</button></form><br>
    
     	

      </p>
    </div>
<a href="home.jsp"><div class="close-button r-close-button">x</div></a>
  </div>


<div class="r-page logout-r-page">

    <div class="page-content">
      <h2 class="page-title">Log Out</h2><br><br>
      <p>
        <form action="Display" method="post">
  <input type="hidden" name="fun" value="logout" >
  <button class="button" onclick="submit">Log Out</button>
  </form>
      
       </p>
    </div>
      <a href="home.jsp"><div class="close-button r-close-button">x</div></a>
  </div>





<!--each tile should specify what page type it opens (to determine which animation) and the corresponding page name it should open-->
  <div class="dashboard clearfix">
    <ul class="tiles">
      <div class="col1 clearfix">
        <li class="tile tile-big tile-1 slideTextUp" data-page-type="r-page" data-page-name="andom-r-page">
          <div><p>User</p></div>
          <div><p>-^-</p></div>
        </li>
        <li class="tile tile-small tile tile-2 slideTextRight" data-page-type="r-page" data-page-name ="work-r-page">
          <div><p class="icon-arrow-right"></p></div>
          <div><p>Conferences</p></div>
        </li>
        <li class="tile tile-small last tile-3" data-page-type="r-page" data-page-name="logout-r-page">
		<p1><br><i class="fa fa-power-off fa-5x"></i></i></p1>
        </li>
        <li class="tile tile-big tile-4 fig-tile" data-page-type="r-page" data-page-name="employee-r-page">
          <figure>
            <img src="images/blue2.jpg" />
            <figcaption class="tile-caption caption-left">Messages & Notifications</figcaption>
          </figure>
        </li>
      </div>

      <div class="col2 clearfix">
        <li class="tile tile-big tile-5" data-page-type="r-page" data-page-name="random-r-page">
          <div><p>Papers</span></p></div>
        </li>
        <li class="tile tile-big tile-6 slideTextLeft" data-page-type="r-page" data-page-name="bill-r-page">
          <div><p>Reviews</p></div>
          <div><p>My Reviews</p></div>
        </li>
        <!--Tiles with a 3D effect should have the following structure:
            1) a container inside the tile with class of .faces
            2) 2 figure elements, one with class .front and the other with class .back-->
        <li class="tile tile-small tile-7 rotate3d rotate3dX" data-page-type="r-page" data-page-name="other-r-page">
          <div class="faces">
            <div class="front"><p>Other</p></div>
            <div class="back"><p>another</p></div>
          </div>
        </li>
       <li class="tile tile-small last tile-8 rotate3d rotate3dY" data-page-type="r-page" data-page-name="random-r-page">
          <a href="game.html"> <div class="faces">
            <div class="front"><p><font size="5">Stress Relief</font></p></div>
            <div class="back"><p>Memory game</p></div>
          </div>
        </li></a>
      </div>

      <div class="col3 clearfix">      
        <li class="tile tile-2xbig tile-9 fig-tile" data-page-type="custom-page" data-page-name="about-r-page">
          <a href="http://www.monash.edu">
          <figure>
            <img src="images/monash.jpg" />
            <figcaption class="tile-caption caption-bottom"> <b>GoMonash</b>
            </figure></a>
        </li>
        <li class="tile tile-big tile-10" data-page-type="r-page" data-page-name="help-r-page">
          <div><p><b><font size="20">Help</font></b></p></div>
        </li>
      </div>
    </ul>
  </div><!--end dashboard-->

</div>
<!--====================================end demo wrapper================================================-->
  <script src="js/jquery-1.8.2.min.js"></script>
  <script src="js/scripts.js"></script>

</body>
</html>
<%}  %>
