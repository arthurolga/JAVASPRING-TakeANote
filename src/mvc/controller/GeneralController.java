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
		
	}
	@RequestMapping("editaCard")
	protected String editaCard (Notas nota, Tags tag,HttpServletRequest request,
			 HttpServletResponse response) throws IOException, ServletException {
			

			//Notas nota = new Notas();
			//Tags tag = new Tags();
			//int id =Integer.parseInt(request.getParameter("id"));
			//nota.setId(id);
			//nota.setTitulo(request.getParameter("titulo"));
			//nota.setContent(request.getParameter("content"));
			if (nota.getTag()!=null) {
				if (nota.getTag().length() > 0) {
				nota.setTag(nota.getTag());
				tag.setName(nota.getTag());
				HttpSession session = request.getSession();
				tag.setUser((String)session.getAttribute( "username" ));
			}
			}
			
			DAO dao = new DAO();
			try {
				//if ((tag.getName() != null)&(!(dao.checkTag(tag))&&(tag.getName().length() > 0) )) {
				if ((tag.getName() != null)) {
					if(tag.getName().length() > 1) {
						
					
					if(!(dao.checkTag(tag))){
						System.out.println("Deu checkotag");
						dao.adicionaTag(tag);
					}
					}else {
						nota.setTag(null);
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Conectou cm o DAO");
			try {
				System.out.println("Tentou rodar o edit");
				
				dao.alteraNota(nota);
				
			} catch (SQLException e) {
				System.out.println("Nem tentou rodar o edit");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "index";

	
	}
	@RequestMapping("removeCard")
	protected String service (Notas nota, HttpServletRequest request,
			 HttpServletResponse response) throws IOException, ServletException {
		
			//int id =Integer.parseInt(request.getParameter("id"));
			int id = nota.getId();
			DAO dao = new DAO();
			System.out.println("Conectou cm o DAO");
			try {
				System.out.println("Tentou rodar o remove");
				dao.removeNota(id);
			} catch (SQLException e) {
				System.out.println("Nem tentou rodar o remove");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "index";
			
	}
	@RequestMapping("closeTag")
	protected String closeTag (HttpServletRequest request,
			 HttpServletResponse response) throws IOException, ServletException {
		
			int id =Integer.parseInt(request.getParameter("id"));
			Notas nota = new Notas();
			nota.setId(id);
			nota.setTag(null);
			
			DAO dao = new DAO();
			System.out.println("Conectou cm o DAO");
			try {
				System.out.println("Tentou rodar o remove");
				dao.alteraTagNota(nota);
			} catch (SQLException e) {
				System.out.println("Nem tentou rodar o remove");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "index";
			
	}   


	}
	


