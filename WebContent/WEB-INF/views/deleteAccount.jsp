<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0" />
  <title>Delete Account</title>

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
</head>

<body>
<%@ page import="java.util.*,mvc.controller.*" %>
	<form action="removeUsuario" method="post" onsubmit="return confirm('Are you sure you want to DELETE this account?');">

  <nav>
    <div class="nav-wrapper light-blue accent-3 z-depth-2">
      <a href="index" class="brand-logo hide-on-med-and-down" style="margin-left:3%">Take-a-Note!</a>
      <a href="index" class="brand-logo hide-on-large-only">Take-a-Note!</a>
      <ul id="nav-mobile" class="right hide-on-med-and-down">
        <li>
          <a href="login">Login</a>
        </li>
        <li>
          <a href="register">Sign Up</a>
        </li>
      </ul>
    </div>

  </nav>


  <div class="section  blue-grey lighten-4">

    <!--   Icon Section   -->
    
    <div class="row">
      
      <div class="col s8 offset-s2 grey lighten-5 z-depth-1 " style="margin-top:-1%; padding-bottom:100%;" >
        <form class="col s12">
          <div class="row">
            <div>
            <% String error = (String)session.getAttribute( "error" ); 
            if(error != null){ %>
            <br>
    		<h5 class="center-align" style="color:red;"> <%=error %></h5>
    
    
    		<%} %>
                <br>
                <h4 class="center-align" >Delete Account</h4>
            </div>  
          </div>
          
          <div class="row">
            <br>
            <br>
            
            <div class="input-field col s8 offset-s2">
              Username:<input id="username" type="text" class="validate" name='user'>
            </div>
  
        </div>
        <div class="row">
          <div class="input-field col s8 offset-s2">
            Senha:<input id="password" type="password" class="validate" name='password'>
        
          </div>
        </div>
      <div class="row">
        <div class="center-align">
          <input type="submit" class="waves-effect waves-light btn" value='Submit'></input>
        </div>
      </div>
        
    </form>
    </div>
    </div>
  </div>

  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script src="js/init.js"></script>
  </form>

</body>

</html>