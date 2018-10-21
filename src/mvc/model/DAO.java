package mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.lang.System.out;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 


public class DAO {
	private Connection connection = null;

	public DAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/takeanote?useTimezone=true&serverTimezone=UTC","root","toyboy");
			System.out.println("DAO Conectado!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DAO nao Conectado!");
		}
	}

	public List<Usuarios> getLista() throws SQLException {
		List<Usuarios> usuarios = new ArrayList<Usuarios>();
		PreparedStatement stmt = null;
		try {
			stmt = connection.
					prepareStatement("SELECT * FROM usuarios");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				Usuarios usuario = new Usuarios();
				usuario.setId(rs.getInt("id"));
				usuario.setUser(rs.getString("username"));
				usuario.setPassword(rs.getString("password"));
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs.close();
		stmt.close();
		return usuarios;

	}
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void adiciona(Usuarios usuario) throws SQLException {
		String sql = "INSERT INTO usuarios "+"(username,password) VALUES (?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, usuario.getUser());
		stmt.setString(2, usuario.getPassword());
		stmt.execute();
		stmt.close();
	}
	public void altera(Usuarios usuario, String novasenha) throws SQLException {
		String sql = "UPDATE usuarios SET "+"password=? WHERE username=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		System.out.println("senha alterada");
		stmt.setString(1,novasenha);
		stmt.setString(2,usuario.getUser());
		stmt.execute();
		stmt.close();

	}
	
	public void remove(Usuarios usuario) throws SQLException {
		PreparedStatement stmt = connection
				.prepareStatement("DELETE FROM usuarios WHERE username=?");
		stmt.setString(1, usuario.getUser());
		stmt.execute();
		stmt.close();
	}

	public boolean login(Usuarios usuario) throws SQLException {
		String password;
		String sql = "SELECT password FROM usuarios WHERE username=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, usuario.getUser());
		System.out.println(">>>>>>>1");
		ResultSet rs = null;
		rs = stmt.executeQuery();

		System.out.println(">>>>>>>2");
		rs.next();
		password = rs.getString("password");
		System.out.println(password);

		if (password.equals(usuario.getPassword())) {
			System.out.println("Senha correta");
			rs.close();
			stmt.close();
			return true;
		}
		
		else {
			System.out.println("Senha incorreta");
			rs.close();
			stmt.close();
			return false;
		}

	}
	public boolean checkUser(Usuarios usuario) throws SQLException{
		
		String sql = "SELECT username FROM usuarios";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = null;
		rs = stmt.executeQuery();
		while (rs.next()) {
			String nome=rs.getString("username");
			if (nome.equals(usuario.getUser())){
				return true;
			}
			
		}
		return false;
		
		
		
		
	}
	public List<Notas> getListaNotas() throws SQLException {
		List<Notas> notas = new ArrayList<Notas>();
		PreparedStatement stmt = null;
		try {
			stmt = connection.
					prepareStatement("SELECT * FROM notas");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				Notas nota = new Notas();
				nota.setId(rs.getInt("id"));
				nota.setTitulo(rs.getString("titulo"));
				nota.setContent(rs.getString("content"));
				notas.add(nota);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs.close();
		stmt.close();
		return notas;

	}
	public List<Notas> getListaNotasUser(String username) throws SQLException {
		List<Notas> notas = new ArrayList<Notas>();
		PreparedStatement stmt = null;
		try {
			stmt = connection.
					prepareStatement("SELECT * FROM notas WHERE user=? ORDER BY time DESC");
			stmt.setString(1, username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				Notas nota = new Notas();
				nota.setId(rs.getInt("id"));
				nota.setTitulo(rs.getString("titulo"));
				nota.setContent(rs.getString("content"));
				nota.setTag(rs.getString("tag"));
				nota.setTime(rs.getString("time"));
				nota.setColor(rs.getString("color"));
				notas.add(nota);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs.close();
		stmt.close();
		return notas;

	}
	public List<Notas> getListaNotasTags(String tag, String username) throws SQLException {
		List<Notas> notas = new ArrayList<Notas>();
		PreparedStatement stmt = null;
		try {
			stmt = connection.
					prepareStatement("SELECT * FROM notas WHERE tag LIKE ? AND user=?");
			stmt.setString(1, tag);
			stmt.setString(2, username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				Notas nota = new Notas();
				nota.setId(rs.getInt("id"));
				nota.setTitulo(rs.getString("titulo"));
				nota.setContent(rs.getString("content"));
				nota.setTag(rs.getString("tag"));
				nota.setTime(rs.getString("time"));
				nota.setColor(rs.getString("color"));
				notas.add(nota);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs.close();
		stmt.close();
		return notas;

		
	
	}
	public List<Notas> getListaNotasSearch(String search, String username) throws SQLException {
		List<Notas> notas = new ArrayList<Notas>();
		PreparedStatement stmt = null;
		search = "%"+search+"%";
		try {
			stmt = connection.
					prepareStatement("SELECT * FROM notas WHERE (titulo LIKE ? OR content LIKE ?) AND user=?");
			
					//SELECT * FROM notas WHERE LIKE {%?%}
			stmt.setString(1, search);
			stmt.setString(2, search);
			stmt.setString(3, username);
			System.out.println(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				Notas nota = new Notas();
				nota.setId(rs.getInt("id"));
				nota.setTitulo(rs.getString("titulo"));
				nota.setContent(rs.getString("content"));
				nota.setTag(rs.getString("tag"));
				nota.setTime(rs.getString("time"));
				nota.setColor(rs.getString("color"));
				notas.add(nota);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs.close();
		stmt.close();
		return notas;
	}


	public void adicionaNota(Notas nota, String usuario) throws SQLException {
		String sql = "INSERT INTO notas "+"(titulo,content,user,time,color) VALUES (?,?,?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		stmt.setString(1, nota.getTitulo());
		stmt.setString(2, nota.getContent());
		stmt.setString(3, usuario);
		stmt.setString(4,dtf.format(now));
		stmt.setString(5, "yellow");
		stmt.execute();
		stmt.close();
	}
	public void removeNota(Integer id) throws SQLException {
		PreparedStatement stmt = connection
				.prepareStatement("DELETE FROM notas WHERE id=?");
		stmt.setLong(1, id);
		stmt.execute();
		stmt.close();
	}
	public void colorNota(Integer id, String color) throws SQLException {
		PreparedStatement stmt = connection
				.prepareStatement("UPDATE notas SET color=? WHERE id=?");
		stmt.setString(1, color);
		stmt.setInt(2, id);
		stmt.execute();
		stmt.close();
	}
	public void alteraNota(Notas nota) throws SQLException {
		String sql = "UPDATE notas SET "+"titulo=?, content=?, tag=?, time=? WHERE id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		stmt.setString(1, nota.getTitulo() );
		stmt.setString(2, nota.getContent() );
		stmt.setString(3, nota.getTag() );
		stmt.setString(4, dtf.format(now));
		stmt.setInt(5, nota.getId() );
		stmt.execute();
		stmt.close();
	}
	public void alteraTagNota(Notas nota) throws SQLException {
		String sql = "UPDATE notas SET "+" tag=? WHERE id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, nota.getTag() );
		stmt.setInt(2, nota.getId() );
		stmt.execute();
		stmt.close();
	}
	
	
	public List<Tags> getListaTagsUser(String username) throws SQLException {
		List<Tags> tags = new ArrayList<Tags>();
		PreparedStatement stmt = null;
		try {
			stmt = connection.
					prepareStatement("SELECT * FROM tags WHERE user=?");
			stmt.setString(1, username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				Tags tag = new Tags();
				tag.setTagId(rs.getInt("id"));
				tag.setName(rs.getString("name"));
				tag.setUser(rs.getString("user"));
				tags.add(tag);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs.close();
		stmt.close();
		return tags;

	}
	public void adicionaTag(Tags tag) throws SQLException {
		String sql = "INSERT INTO tags "+"(name,user) VALUES (?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, tag.getName());
		stmt.setString(2, tag.getUser());
		stmt.execute();
		stmt.close();
	}
	public void removeTag(Integer id) throws SQLException {
		PreparedStatement stmt = connection
				.prepareStatement("DELETE FROM tags WHERE id=?");
		stmt.setLong(1, id);
		stmt.execute();
		stmt.close();
	}
	public boolean checkTag(Tags tag) throws SQLException{
		
		PreparedStatement stmt = connection.
				prepareStatement("SELECT * FROM tags WHERE user=?");
		stmt.setString(1, tag.getUser());
		ResultSet rs = null;
		rs = stmt.executeQuery();
		while (rs.next()) {
			if ((tag.getName()).equals(rs.getString("name"))){
				return true;
			}
			
		}
		return false;
		
		
		
		
	}


	




	
	
}

