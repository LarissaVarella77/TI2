package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;

public class AlunoDAO extends DAO {
	
	public AlunoDAO() {
		super();
		conectar();
	}
	
	// Inserir
	public boolean insert(Aluno aluno) {
		boolean status = false;
		String sql = "INSERT INTO aluno (matricula, nome, endereco, sexo) VALUES (?, ?, ?, ?)";
		try (PreparedStatement ps = conexao.prepareStatement(sql)) {
			ps.setInt(1, aluno.getMatricula());
			ps.setString(2, aluno.getNome());
			ps.setString(3, aluno.getEndereco());
			ps.setString(4, String.valueOf(aluno.getSexo()));
			ps.executeUpdate();
			status = true;
		} catch (SQLException e) {
			System.err.println("Erro ao inserir aluno: " + e.getMessage());
		}
		return status;
	}
	
	// Buscar por matrícula
	public Aluno get(int matricula) {
		Aluno aluno = null;
		String sql = "SELECT * FROM aluno WHERE matricula = ?";
		try (PreparedStatement ps = conexao.prepareStatement(sql)) {
			ps.setInt(1, matricula);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				aluno = new Aluno(
					rs.getInt("matricula"),
					rs.getString("nome"),
					rs.getString("endereco"),
					rs.getString("sexo").charAt(0)
				);
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("Erro ao buscar aluno: " + e.getMessage());
		}
		return aluno;
	}
	
	// Listar todos
	public List<Aluno> get() {
		return get("");
	}
	
	// Listar ordenados
	public List<Aluno> getOrderByMatricula() { return get("matricula"); }
	public List<Aluno> getOrderByNome() { return get("nome"); }
	public List<Aluno> getOrderBySexo() { return get("sexo"); }
	
	// Listagem genérica
	private List<Aluno> get(String orderBy) {
		List<Aluno> alunos = new ArrayList<>();
		String sql = "SELECT * FROM aluno" + 
		             (orderBy.trim().length() == 0 ? "" : (" ORDER BY " + orderBy));
		try (Statement st = conexao.createStatement();
		     ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				Aluno aluno = new Aluno(
					rs.getInt("matricula"),
					rs.getString("nome"),
					rs.getString("endereco"),
					rs.getString("sexo").charAt(0)
				);
				alunos.add(aluno);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao listar alunos: " + e.getMessage());
		}
		return alunos;
	}
	
	// Listar só os masculinos
	public List<Aluno> getSexoMasculino() {
		List<Aluno> alunos = new ArrayList<>();
		String sql = "SELECT * FROM aluno WHERE sexo = 'M'";
		try (Statement st = conexao.createStatement();
		     ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				Aluno aluno = new Aluno(
					rs.getInt("matricula"),
					rs.getString("nome"),
					rs.getString("endereco"),
					rs.getString("sexo").charAt(0)
				);
				alunos.add(aluno);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao listar alunos masculinos: " + e.getMessage());
		}
		return alunos;
	}
	
	// Atualizar
	public boolean update(Aluno aluno) {
		boolean status = false;
		String sql = "UPDATE aluno SET nome = ?, endereco = ?, sexo = ? WHERE matricula = ?";
		try (PreparedStatement ps = conexao.prepareStatement(sql)) {
			ps.setString(1, aluno.getNome());
			ps.setString(2, aluno.getEndereco());
			ps.setString(3, String.valueOf(aluno.getSexo()));
			ps.setInt(4, aluno.getMatricula());
			ps.executeUpdate();
			status = true;
		} catch (SQLException e) {
			System.err.println("Erro ao atualizar aluno: " + e.getMessage());
		}
		return status;
	}
	
	// Deletar
	public boolean delete(int matricula) {
		boolean status = false;
		String sql = "DELETE FROM aluno WHERE matricula = ?";
		try (PreparedStatement ps = conexao.prepareStatement(sql)) {
			ps.setInt(1, matricula);
			ps.executeUpdate();
			status = true;
		} catch (SQLException e) {
			System.err.println("Erro ao excluir aluno: " + e.getMessage());
		}
		return status;
	}
}
