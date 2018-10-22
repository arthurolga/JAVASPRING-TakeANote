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
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;











@Controller
@ControllerAdvice
public class GeneralController {
	@RequestMapping("index")
	public String execute(HttpServletRequest request) {
		System.out.println("Lógica do MVC");
		HttpSession session = request.getSession();
		session.setAttribute( "error", null );
		return "index";
	}
	@RequestMapping("login")
	public String show(HttpServletRequest request) {
		System.out.println("Lógica do MVC");
		HttpSession session = request.getSession();
		session.setAttribute( "error", null );
		return "login";
	}
	@RequestMapping("editAccount")
	public String editAccount(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute( "error", null );
		return "changePassword";
	}
	@RequestMapping("deleteAccount")
	public String deleteAccount(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute( "error", null );
		return "deleteAccount";
	}
	@RequestMapping("createAccount")
	public String createAccount(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute( "error", null );
		return "register";
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
							session.setAttribute( "error", null );
							return "index";
						}
						else {
							session.setAttribute( "error", "Incorrect Password" );
							return "login";
						}
					}
					else {
						System.out.println("user nao existente");
						session.setAttribute( "error", "User doesn't exist" );
						return "login";
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("NAO Tentou fazer login");
					session.setAttribute( "error", "Server rejected login" );
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
	@RequestMapping("removeTag")
	protected String removeTag (HttpServletRequest request,
			 HttpServletResponse response) throws IOException, ServletException {
		
			int id =Integer.parseInt(request.getParameter("id"));
			DAO dao = new DAO();
			System.out.println("Conectou cm o DAO");
			try {
				System.out.println("Tentou rodar o remove");
				dao.removeTag(id);
				
			} catch (SQLException e) {
				System.out.println("Nem tentou rodar o remove");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "index";
	}
	@RequestMapping("changePassword")
	protected String changePassword (Usuarios usuario, HttpServletRequest request,
			 HttpServletResponse response) throws IOException, ServletException {
		

			
			//Usuarios usuario = new Usuarios();
			//String name = request.getParameter("nome");
			//String senha = request.getParameter("senha");
			
			String novasenha = request.getParameter("novasenha");
			HttpSession session = request.getSession();
			//usuario.setUser(name);
			//usuario.setPassword(senha);
			DAO dao = new DAO();
			try {
				if (dao.login(usuario)){
					dao.altera(usuario, novasenha);
					session.setAttribute( "error", null );
					return "login";

				}else {
					session.setAttribute( "error", "Password is wrong" );
					return "changePassword";
					//RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/changePassword.jsp?error='Username or password not indentified'");
					//try {
					//	RequetsDispatcherObj.forward(request, response);
					//	
					//} catch (ServletException e) {
						// TODO Auto-generated catch block
					//	e.printStackTrace();
					//}
				}
			} catch (SQLException e) {
				session.setAttribute( "error", "User doesn't exist" );
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			return "changePassword";
	}
	@RequestMapping("removeUsuario")
	protected String removeUsuario (Usuarios usuario, HttpServletRequest request,
			 HttpServletResponse response) throws IOException, ServletException {
		
			//PrintWriter out = response.getWriter();
			//Usuarios usuario = new Usuarios();
			//String name = request.getParameter("nome");
			//String senha = request.getParameter("senha");
			//usuario.setUser(name);
			//usuario.setPassword(senha);
			HttpSession session = request.getSession();
			DAO dao = new DAO();
			try {
				if (dao.login(usuario)){

					dao.remove(usuario);
					System.out.println("deletou");
					session.setAttribute( "error", "User deleted" );
					return "login";

				} else {
					session.setAttribute( "error", "Password is wrong" );
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute( "error", "User doesn't exist" );
			}
			
			return "deleteAccount";
	}
	@RequestMapping("registraUsuario")
	protected String registraUsuario (Usuarios usuario,HttpServletRequest request,
			 HttpServletResponse response) throws IOException {
		
		//PrintWriter out = response.getWriter();
		DAO dao = new DAO();
		String error;
		HttpSession session = request.getSession();
		//Usuarios usuario = new Usuarios();
		//usuario.setUser(request.getParameter("nome"));
		//usuario.setPassword(request.getParameter("senha"));
		String confirm = request.getParameter("confirm");
		try {
			if(!dao.checkUser(usuario) && usuario.getPassword().equals(confirm)  ) {
				System.out.println("usuario adicionado");
				dao.adiciona(usuario);
				session.setAttribute( "username", usuario.getUser() );
				
				return "index";
				
			}
			else {
				System.out.println("esse user ja existe");
				
				if (dao.checkUser(usuario)) {
					error = "User already exists";
				} else {
					error = "Please make sure that both passwords are equal";
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = "Server rejected the Sign Up";
		}
		session.setAttribute( "error", error );
		return "register";
	}





	}
	


