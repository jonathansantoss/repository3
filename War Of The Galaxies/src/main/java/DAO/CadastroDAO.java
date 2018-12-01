package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Jogo.Cadastro;
import Jogo.JanelaCadastro;

// classe para acessar o banco de dados
public class CadastroDAO {
	private Connection conexao;
	private LinkedList<Cadastro> listaCadastro = new LinkedList<Cadastro>();

	public CadastroDAO() {

		try {
			conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void save(String nome) {
		String sql = "INSERT INTO RANKING(nome,score) VALUES('" + nome + "'" + "," + 0 +")";

		try {
			Statement stmt = conexao.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void atualizar(int pontos, String nome ) {
		String sql = "UPDATE RANKING SET score = " + pontos + " WHERE nome = '" + nome + "'";

		try {
			Statement stmt = conexao.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Mostra ranking de todos jogadores cadastrados
	public LinkedList<Cadastro> buscar() {
		String sql = "select * from Ranking ORDER BY score desc limit 10";
		

		try {
			Statement stmt = conexao.createStatement();
			ResultSet result = stmt.executeQuery(sql);

			while (result.next()) {
				int pontos = result.getInt("score");
				String nome = result.getString("nome");
								
				Cadastro cadastro = new Cadastro();

				cadastro.score = pontos;
				cadastro.nome = nome;

				listaCadastro.add(cadastro);
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCadastro;
	}

	// Testar
	public LinkedList<Integer> buscar(String nome) {
		String sql = "SELECT score FROM RANKING WHERE nome =" + nome;
		LinkedList<Integer> listaPontos = new LinkedList<Integer>();

		try {
			Statement stmt = conexao.createStatement();
			ResultSet result = stmt.executeQuery(sql);

			while (result.next()) {
				JanelaCadastro cadastro = new JanelaCadastro();

				int pontos = result.getInt("score");
				cadastro.score = pontos;
				listaPontos.add(pontos);
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPontos;

	}

}
