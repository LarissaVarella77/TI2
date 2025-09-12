package app;

import java.util.List;
import java.util.Scanner;

import dao.AlunoDAO;
import model.Aluno;

public class Aplicacao {
	
	

	
	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		AlunoDAO alunoDAO = new AlunoDAO();
		List<Aluno> alunos = alunoDAO.getSexoMasculino();
		Aluno aluno = new Aluno();
		int opcaoMenu = 0;
		int matricula;
		
		while (opcaoMenu != 9) {
			System.out.println("\n-- Gestão de Alunos --\n");
			System.out.println("Digite a opção desejada:");
			System.out.println("  1 - Listar");
			System.out.println("  2 - Inserir");
			System.out.println("  3 - Excluir");
			System.out.println("  4 - Atualizar");
			System.out.println("  9 - Sair");
			System.out.print("Opção escolhida: ");
			opcaoMenu = sc.nextInt();
			sc.nextLine();
			System.out.println();
			switch (opcaoMenu) {
			case 1:
				System.out.println("\n==== Alunos ordenados por matrícula === ");
				alunos = alunoDAO.getOrderByMatricula();
				for (Aluno u: alunos) {
					System.out.println(u.toString());
				}
				break;
			case 2:
				System.out.println("\n==== Inserir aluno === ");
				System.out.print("Informe a matrícula: ");
				aluno.setMatricula(sc.nextInt());
				sc.nextLine();
				System.out.print("Informe o nome: ");
				aluno.setNome(sc.nextLine());
				System.out.print("Informe o endereço: ");
				aluno.setEndereco(sc.nextLine());
				System.out.print("Informe a sexo [M|F]: ");
				aluno.setSexo(sc.nextLine().toUpperCase().charAt(0));
				if(alunoDAO.insert(aluno)) {
					System.out.println("Inserção com sucesso -> " + aluno.toString());
				} else {
					System.out.println("Não foi possível completar a operação. Favor conferir as informações e tentar mais tarde.");
				}
				break;
			case 3:
				System.out.println("\n==== Excluir aluno === ");
				System.out.print("Informe a matrícula do aluno a ser excluído: ");
				matricula = sc.nextInt();
				sc.nextLine();
				aluno = alunoDAO.get(matricula);
				if (aluno == null) {
					System.out.println("Aluno não encontrado.");
					break;
				}
				if (alunoDAO.delete(matricula)) {
					System.out.println("Aluno excluído com sucesso.");
				} else {
					System.out.println("Não foi possível completar a operação. Favor conferir as informações e tentar mais tarde.");
				}
				break;
			case 4:
				System.out.println("\n==== Atualizar endereco === ");
				System.out.print("Informe a matrícula do aluno: ");
				matricula = sc.nextInt();
				sc.nextLine();
				aluno = alunoDAO.get(matricula);
				if (aluno == null) {
					System.out.println("Aluno não encontrado.");
					break;
				}
				System.out.print("Informe o nome: ");
				aluno.setNome(sc.nextLine());
				System.out.print("Informe o endereço: ");
				aluno.setEndereco(sc.nextLine());
				System.out.print("Informe a sexo [M|F]: ");
				aluno.setSexo(sc.nextLine().toUpperCase().charAt(0));
				if(alunoDAO.update(aluno)) {
					System.out.println("Dados atualizados com sucesso -> " + aluno.toString());
				} else {
					System.out.println("Não foi possível completar a operação. Favor conferir as informações e tentar mais tarde.");
				}
				break;
			case 9:
				System.out.println("\nObrigado pela preferência. FIM!\n");
				break;
			default:
				System.out.println("Opção inválida!");
				break;
			}
			System.out.println("\nTecle ENTER para continuar...");
			sc.nextLine();
		}
				
		sc.close();
	}
}