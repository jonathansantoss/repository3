package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Jogo.Cadastro;

// classe para acessar o banco de dados
public class CadastroDAO {
	private Connection conexao;

	public CadastroDAO() {

		try {
			conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void save(String nome) {
		String sql = "INSERT INTO RANKING(nome) VALUES('" + nome + "')";

		try {
			Statement stmt = conexao.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Testar
	public void atualizar(int pontos, int id ) {
		String sql = "UPDATE TABLE RANKING set score = " + pontos + " WHERE id = " + id;

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
		String sql = "SELECT * FROM RANKING ORDER BY";
		LinkedList<Cadastro> listaCadastro = new LinkedList<Cadastro>();

		try {
			Statement stmt = conexao.createStatement();
			ResultSet result = stmt.executeQuery(sql);

			while (result.next()) {
				Cadastro cadastro = new Cadastro();

				int pontos = result.getInt("score");
				String nome = result.getString("nome");

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
				Cadastro cadastro = new Cadastro();

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

//	public void buscar(int id) {
//
//	}
}
