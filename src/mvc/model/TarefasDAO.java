package mvc.model;
import java.sql.Date;
import java.sql.*;
import java.util.*;

public class TarefasDAO {
	private Connection connection = null;

	public void TarefaDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/meus_dados",
					"root", "123456");
		} catch (SQLException | ClassNotFoundException e)
		{e.printStackTrace();}
	}
	public void adicionaDescricao(Tarefa tarefa) {
		try {
			String sql = "INSERT INTO Tarefa (descricao) values(?)";
					PreparedStatement stmt =
					connection.prepareStatement(sql);
			stmt.setString(1,tarefa.getDescricao());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {e.printStackTrace();}
	}
	public void adiciona(Tarefa tarefa) {
		try {
			String sql = "INSERT INTO Tarefa" +
					"(descricao,finalizado,dataFinalizacao) values(?,?,?)";
			PreparedStatement stmt =
			connection.prepareStatement(sql);
			stmt.setString(1,tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.setDate(3, new
					Date(tarefa.getDataFinalizacao().getTimeInMillis()));
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {e.printStackTrace();}}
	public List<Tarefa> getLista() {
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		try {
			PreparedStatement stmt = connection.
					prepareStatement("SELECT * FROM Tarefa");
							ResultSet rs = stmt.executeQuery();
							while (rs.next()) {
								Tarefa tarefa = new Tarefa();
								tarefa.setId(rs.getLong("id"));
								tarefa.setDescricao(rs.getString("descricao"));

								tarefa.setFinalizado(rs.getBoolean("finalizado"));
								Calendar data = Calendar.getInstance();
								Date dataFinalizacao =
										rs.getDate("dataFinalizacao");
								if(dataFinalizacao!=null) {
									data.setTime(dataFinalizacao);
									tarefa.setDataFinalizacao(data);
								}
								tarefas.add(tarefa);
							}
							rs.close();
							stmt.close();
		} catch(SQLException e) {System.out.println(e);}
		return tarefas;
	}
	public void remove(Tarefa tarefa) {
		try {
			PreparedStatement stmt =
					connection.prepareStatement("DELETE FROM Tarefa WHERE id=?");
			stmt.setLong(1, tarefa.getId());
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {System.out.println(e);}
	}
	public Tarefa buscaPorId(Long id) {
		Tarefa tarefa = new Tarefa();
		try {
			PreparedStatement stmt =
					connection.prepareStatement("SELECT * FROM Tarefa WHERE id=? ");
							stmt.setLong(1, id);
					ResultSet rs = stmt.executeQuery();
					if(rs.next()) {
						tarefa.setId(rs.getLong("id"));
						tarefa.setDescricao(rs.getString("descricao"));

						tarefa.setFinalizado(rs.getBoolean("finalizado"));
						Calendar data = Calendar.getInstance();
						Date dataFinalizacao =
								rs.getDate("dataFinalizacao");
						if(dataFinalizacao!=null) {
							data.setTime(dataFinalizacao);
							tarefa.setDataFinalizacao(data);
						}
					}
					rs.close();
					stmt.close();
		} catch(SQLException e) {System.out.println(e);}
		return tarefa;
	}
	public void altera(Tarefa tarefa) {
		try {
			String sql = "UPDATE Tarefa SET descricao=?, finalizado=?, " +
							"dataFinalizacao=? WHERE id=?";
			PreparedStatement stmt =
					connection.prepareStatement(sql);
			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			if(tarefa.getDataFinalizacao()!=null) {
				stmt.setDate(3, new
						Date(tarefa.getDataFinalizacao().getTimeInMillis()));
			} else {
				stmt.setDate(3, new
						Date(Calendar.getInstance().getTimeInMillis()));
			}
			stmt.setLong(4, tarefa.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {System.out.println(e);}
	}
	public void finaliza(Long id) {
		try {
			String sql = "UPDATE Tarefa SET finalizado=?, dataFinalizacao=? " +
							"WHERE id=?";
			PreparedStatement stmt =
					connection.prepareStatement(sql);
			stmt.setBoolean(1, true);
			stmt.setDate(2, new
					Date(Calendar.getInstance().getTimeInMillis()));
			stmt.setLong(3, id);
			stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {System.out.println(e);}
	}
	public void close() {
		try { connection.close();}
		catch (SQLException e) {e.printStackTrace();}
	}
}

