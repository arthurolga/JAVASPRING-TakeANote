<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>Notas - Take-A-Note!</title>

<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
	crossorigin="anonymous">
<link href="css/materialize.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="../css/style.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css" />
<style>
::placeholder {
	color: #7f7f7f;
}
.cards-container {
  column-break-inside: avoid;
  .card-panel {
    display: inline-block;
    overflow: visible;
  }
}
@media only screen and (min-width: 760px) {
 
     #cardAdd {
     border-radius: 15px;
     }
 }


</style>
</head>

<body>
	<%@ page import="java.util.*,mvc.model.*"%>
	<nav>
		<div class="nav-wrapper light-blue accent-3 z-depth-2">
			<a href="index" class="brand-logo hide-on-med-and-down"
				style="margin-left: 3%">Take-a-Note!</a> <a href="index"
				class="brand-logo hide-on-large-only">Take-a-Note!</a>
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li><a href="login">Login</a></li>
				<li><a href="editAccount">Change Password</a></li>
				<li><a href="deleteAccount">Delete User</a></li>
			</ul>
		</div>

	</nav>
	<%
  String user = (String)session.getAttribute( "username" );
  DAO dao = new DAO();
  String searchTag = (String)session.getAttribute( "searchTag" );
  if((user)== null){
  	RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("login.jsp");
  	try{
  	RequetsDispatcherObj.forward(request, response);
  	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
  };
  %>


	<div class="section  blue-grey lighten-4">

		<!--   Icon Section   -->

		<div class="row">

			<div class="col l2  grey lighten-5 z-depth-1 hide-on-med-and-down"
				style="margin-top: -1%; padding-bottom: 100%;">
				<div class="row">
					<div class="col s12 ">
						<div class="row" style="margin-bottom: -10px">
							<div class="input-field col s12">
								<form action="search" method="get">
									<input type="text" name="search" id="autocomplete-input">
									<label for="autocomplete-input"><i class="fa fa-search"
										style="margin-right: 5px;"></i> Pesquisar</label>
								</form>
							</div>
						</div>
					</div>
				</div>
				
				
				<p id="tags" class="light-blue-text"
					style="margin-left: 2%; margin-bottom: 6%;">Tags</p>
				<ul>
					<li>
						<form class="col s12" style="margin-bottom: 10%" action="AddTag">
							<input name="name" type="text" placeholder="Novo marcador">
						</form>
					</li>
					<%  List<Tags> tags = dao.getListaTagsUser(user);
			
        	for (Tags tag : tags ) { %>
					<li>
						<div class="col s7">
							<a href="searchTag?tag=<%=tag.getName() %>" class="grey-text"
								style="margin-left: 5%"> <i
								class="material-icons light-blue-text" style="margin-right: 5%">check_box_outline_blank</i>
								<font size="4px" style="vertical-align: top;"><%=tag.getName() %></font>
							</a>
						</div>
						<div class="col s1">
							<a href="removeTag?id=<%=tag.getId()%>"><i
								class="material-icons grey-text">close</i></a>
						</div>
					</li>
					<% } %>
				</ul>

			</div>

			<div class="col s12 l10">
				<div class="row">
					<div id="cardAdd"
						class="card col s12 l8 offset-l2 z-depth-2 ">
						<form class="col s12" action="adicionaCard" method="post">
							<div class="row">
								<div class="input-field col s12 m6">
									<input name="titulo" id="title" type="text" class="validate">
									<label for="title">Titulo</label>
								</div>
								<div class="input-field col s12 m6 hide-on-small-only">
									<input name="content" id="content" type="text" class="validate">
									<label for="content">Conteúdo</label>
								</div>
								<div class="input-field col s12 hide-on-small-only" style="margin-top: -2%">
									<input type="submit" value='Add'>
								</div>
								<div class="input-field col s12 hide-on-med-and-up" style="margin-top: -2%">
									<input type="submit" value='Add'>
								</div>
							</div>
						</form>
					</div>

					<div class="card col s12 l8 offset-l2 z-depth-2 hide-on-med-and-up"
						style="margin-top: -3%">
						<div class="input-field col s11 hide-on-small-only">
							<form action="search">
								<i class="material-icons prefix grey-text text-darken-1">search</i>
								<input id="newNote" name="search" placeholder="Pesquisa"
									type="text" class="validate">
							</form>
						</div>
					</div>

				</div>
				<div class="row hide-on-med-and-up">
					<div class="col s12 ">
						<div class="row" style="margin-bottom: -10px">
							<div class="input-field col s12">
								<form action="Search">
									<input type="text" name="search" id="autocomplete-input">
									<label for="autocomplete-input"><i class="fa fa-search"
										style="margin-right: 5px;"></i> Pesquisar</label>
								</form>
							</div>
						</div>
					</div>
				</div>
				<h5 style="color: #424242; padding-left: 2%;">
					Welcome,
					<%= user %>
				</h5>
				<%List<Notas> notas = dao.getListaNotasUser(user);
	        	System.out.println(searchTag);
		        
				if((String)request.getParameter("search") != null){
		        	%>
		        	<a style="font-size:20px; padding: 2%;" href="index" value="voltar">Back</a>
		        	<%
		        	notas = dao.getListaNotasSearch(request.getParameter("search"),user);
		        	session.setAttribute( "searchTag", null );
		        } else if(searchTag != null){
		        	System.out.println(searchTag);
		        	%>
		        	<a style="font-size:20px; padding: 2%;" href="index" value="voltar">Back</a>
		        	<%
		        	session.setAttribute( "searchTag", null );
		        	notas = dao.getListaNotasTags(searchTag, user);
		        }
				%>
				<div class="col s12 cards-container" >
				<%  
        
        
        
        for (Notas nota : notas ) { 
        String tag = nota.getTag();%>
				
					<div class="col s12 m6 l3">
						<div class="card large <%=nota.getColor() %> lighten-3 z-depth-2"
							style="border-radius: 10px;">
							
							
								
							<form action="editaCard" method="post">
							<input type="hidden" name="id" value="<%=nota.getId() %>">
							<div style="height: 50px;">
								<textarea class="materialize-textarea" name="titulo" placeholder="Adicionar título"
									style="color: black; font-size: 18pt; margin-left:10%; margin-top:5%; border-bottom: none !important;"
									><%=nota.getTitulo()%></textarea>
							</div>
							<div class="card-content">
							<div style="height: 100px;">
								<div class="input-field col s12">
									<textarea class="materialize-textarea" name="content" style=" border-bottom: none !important;"><%=nota.getContent()%></textarea>
								</div>
							</div>
							
							<% if (tag != null && tag.length() >1) {
				%>
							<input type="hidden" id="tag" name="tag"
								value="<%=nota.getTag() %>">
							<div class="chip">
								<%=tag %>
								<a href="closeTag?id=<%=nota.getId() %>"><i
									class="close material-icons">close</i></a>
							</div>
							<%
			} else {
				%>

							<input type="text" name="tag"
								placeholder="Adicionar marcador"
								id="tag<%=nota.getId() %>"> 
				</form>
				
				<% 
			}
			 %>
				<div class="row">

					<input class="btn-flat" type="submit" value="Save"> 
					</form>
					
					
					<form action="removeCard" method="post"><input type="hidden" name="id" value="<%=nota.getId() %>"> <button type="submit"
						class="btn-flat btn-large right waves-effect"><i
						class="large material-icons">close</i></button>
					</form>
				</div>
				<div class="row">
				
					<a href="changeColor?id=<%=nota.getId() %>&color=red" style="margin:5px; margin-left:20px; max-width:15px; max-height:15px; border:0.1px; border-style: solid; border-color:black;" class="btn-floating btn-small waves-effect waves-light red lighten-2"></a>
					<a href="changeColor?id=<%=nota.getId() %>&color=blue" style="margin:5px; max-width:15px; max-height:15px; border:0.1px; border-style: solid; border-color:black;" class="btn-floating btn-small waves-effect waves-light blue lighten-2"></a>
					<a href="changeColor?id=<%=nota.getId() %>&color=yellow" style="margin:5px; max-width:15px; max-height:15px; border:0.1px; border-style: solid; border-color:black;" class="btn-floating btn-small waves-effect waves-light yellow lighten-2"></a>
					<a href="changeColor?id=<%=nota.getId() %>&color=teal" style="margin:5px; max-width:15px; max-height:15px; border:0.1px; border-style: solid; border-color:black;" class="btn-floating btn-small waves-effect waves-light teal lighten-2"></a>
				
				</div>
				</div>
							



			</div>

		</div>
		



		<%
      }
      %>
</div>

	</div>




	</div>
	

	<br>
	<br>
	</div>
	<!--
  <footer class="page-footer indigo lighten-1">
    <div class="container">
      <div class="row">
        <div class="col l6 s12">
          <h5 class="white-text">Company Bio</h5>
          <p class="grey-text text-lighten-4">We are a team of college students working on this project like it's our full time job. Any amount would help support and continue development on this project and is greatly appreciated.</p>


        </div>
        <div class="col l3 s12">
          <h5 class="white-text">Settings</h5>
          <ul>
            <li><a class="white-text" href="#!">Link 1</a></li>
            <li><a class="white-text" href="#!">Link 2</a></li>
            <li><a class="white-text" href="#!">Link 3</a></li>
            <li><a class="white-text" href="#!">Link 4</a></li>
          </ul>
        </div>
        <div class="col l3 s12">
          <h5 class="white-text">Connect</h5>
          <ul>
            <li><a class="white-text" href="#!">Link 1</a></li>
            <li><a class="white-text" href="#!">Link 2</a></li>
            <li><a class="white-text" href="#!">Link 3</a></li>
            <li><a class="white-text" href="#!">Link 4</a></li>
          </ul>
        </div>
      </div>
    </div>
    <div class="footer-copyright">
      <div class="container">
      Made by <a class="orange-text text-lighten-3" href="http://materializecss.com">Materialize</a>
      </div>
    </div>
  </footer>
-->

	<!--  Scripts-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="js/materialize.js"></script>
	<script src="js/init.js"></script>

</body>

</html>