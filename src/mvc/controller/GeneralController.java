package mvc.controller;
import mvc.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class GeneralController {
	@RequestMapping("index")
	public String execute() {
		System.out.println("Lógica do MVC");
		return "index";
	}
	@RequestMapping("login")
	public String show() {
		System.out.println("Lógica do MVC");
		return "login";
	}
	@RequestMapping("fazerLogin")
	protected String service (Usuarios usuario, HttpServletRequest request,
				 HttpServletResponse response) throws IOException, ServletException {
			
				//PrintWriter out = response.getWriter();
				//String name = request.getParameter("nome");
				//usuario.setUser(name);
				//usuario.setPassword(request.getParameter("senha"));
				HttpSession session = request.getSession();
				session.setAttribute( "username", usuario.getUser() );
				
				
				DAO dao = new DAO();
				try {
					System.out.println("Tentou fazer login");
					System.out.println(usuario.getUser());
					System.out.println(usuario);
					if(dao.checkUser(usuario)) {
						if(dao.login(usuario)) {
							return "index";
						}
						else {
							return "login";
						}
					}
					else {
						System.out.println("user nao existente");
						return "login";
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("NAO Tentou fazer login");
					return "login";
				}
	       
		
	}
	@RequestMapping("adicionaCard")
	public String adicionaCard(Notas nota, HttpServletRequest request,
			 HttpServletResponse response) throws IOException {
		DAO dao = new DAO();
		//Notas nota = new Notas();
		//nota.setTitulo(request.getParameter("title"));
		//nota.setContent(request.getParameter("content"));
		HttpSession session = request.getSession();
		String usuario = (String)session.getAttribute( "username" );
		try {
			System.out.println("Nota adicionada");
			dao.adicionaNota(nota,usuario);
			dao.close();
			return "index";	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Nota n adicionada");
			e.printStackTrace();
			dao.close();
			return "index";
		}
		
	}}
	


