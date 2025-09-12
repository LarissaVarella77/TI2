package model;

public class Aluno {
	private int matricula;
	private String nome;
	private String endereco;
	private char sexo;
	
	public Aluno() {
		this.matricula = -1;
		this.nome = "";
		this.endereco = "";
		this.sexo = '*';
	}
	
	public Aluno(int codigo, String login, String senha, char sexo) {
		this.matricula = codigo;
		this.nome = login;
		this.endereco = senha;
		this.sexo = sexo;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Aluno: [Matricula=" + matricula + ", Nome=" + nome + ", Endereço=" + endereco + ", Sexo=" + sexo + "]";
	}	
}