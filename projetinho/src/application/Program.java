package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import entities.Aluno;
import jdbc.AlunoJDBC;


public class Program {

	public static void main(String[] args) {
	    Scanner console = new Scanner(System.in);
	    AlunoJDBC acao = new AlunoJDBC();
	    
	    int opcao = 0;
	    do {
	        try {
	            System.out.println("####### Menu #######"
	                                + "\n1 - Cadastrar"
	                                + "\n2 - Listar"
	                                + "\n3 - Alterar"
	                                + "\n4 - Excluir"
	                                + "\n5 - Sair");
	            System.out.println("\n\tOpção:");
	            opcao = Integer.parseInt(console.nextLine());
	            
	            if (opcao == 1) {
	                Aluno a = new Aluno();
	                System.out.println("\n ### Cadastrar Aluno ### \n");
	                
	                System.out.print("Nome: ");
	                a.setNome(console.nextLine());
	                
	                System.out.print("Sexo: ");
	                a.setSexo(console.nextLine());
	            
	                System.out.print("Data de Nascimento (dd-MM-yyyy): ");
	                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	                a.setDt_nasc(LocalDate.parse(console.nextLine(), formato));
	                
	                acao.salvar(a);
	                System.out.println("\n\n\n\n");
	            }
	            else if (opcao == 2) {
	                List<Aluno> alunos = acao.listar();
	                System.out.println("\n ### Lista de Alunos ### \n");
	                for (Aluno aluno : alunos) {
	                    System.out.println("ID: " + aluno.getId());
	                    System.out.println("Nome: " + aluno.getNome());
	                    System.out.println("Sexo: " + aluno.getSexo());
	                    System.out.println("Data de Nascimento: " + aluno.getDt_nasc().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	                    System.out.println("----------------------------");
	                }
	                System.out.println("\n\n\n\n");
	            }
	            else if (opcao == 3) {
	                Aluno a = new Aluno();
	                System.out.println("\n ### Alterar Aluno ### \n");
	                
	                System.out.print("ID do aluno: ");
	                a.setId(Integer.parseInt(console.nextLine()));
	                
	                System.out.print("Novo Nome: ");
	                a.setNome(console.nextLine());
	                
	                System.out.print("Novo Sexo: ");
	                a.setSexo(console.nextLine());
	                
	                System.out.print("Nova Data de Nascimento (dd-MM-yyyy): ");
	                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	                a.setDt_nasc(LocalDate.parse(console.nextLine(), formato));
	                
	                acao.alterar(a);
	                System.out.println("\n\n\n\n");
	            }
	            else if (opcao == 4) {
	                System.out.println("\n ### Excluir Aluno ### \n");
	                
	                System.out.print("ID do aluno: ");
	                int id = Integer.parseInt(console.nextLine());
	                
	                acao.apagar(id);
	                System.out.println("\n\n\n\n");
	            }
	            
	        } catch (NumberFormatException e) {
	            System.out.println("Por favor, insira um número válido.");
	        } catch (Exception e) {
	            System.out.println("Erro: " + e.getMessage());
	        }
	    } while(opcao != 5);

	    console.close();
	}

}
