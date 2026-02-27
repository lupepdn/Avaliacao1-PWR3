import dao.AlunoDAO;
import model.Aluno;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AlunoDAO dao = new AlunoDAO();
        int opcao;

        /*
Trabalho JPA – Cadastro de Alunos
Aluno: Lucas Pereira Dias do Nascimento SC3039382
Curso: ADS – PWR3 Noturno
*/

        do {
            System.out.println("\n** CADASTRO DE ALUNOS **");
            System.out.println("1 - CADASTRAR ALUNOS");
            System.out.println("2 - EXCLUIR ALUNOS");
            System.out.println("3 - ALTERAR ALUNOS");
            System.out.println("4 - BUSCAR ALUNO PELO NOME");
            System.out.println("5 - LISTAR ALUNOS (COM STATUS DE APROVACAO)");
            System.out.println("6 - FIM");
            System.out.print("Opção: ");

            opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {

                case 1: 
                    Aluno novo = new Aluno();

                    System.out.print("Nome: ");
                    novo.setNome(sc.nextLine());

                    System.out.print("RA: ");
                    novo.setRa(sc.nextLine());

                    System.out.print("Email: ");
                    novo.setEmail(sc.nextLine());

                    System.out.print("Nota 1: ");
                    novo.setNota1(sc.nextBigDecimal());

                    System.out.print("Nota 2: ");
                    novo.setNota2(sc.nextBigDecimal());

                    System.out.print("Nota 3: ");
                    novo.setNota3(sc.nextBigDecimal());
                    sc.nextLine();

                    dao.salvar(novo);
                    break;

                case 2: 
                    System.out.print("Digite o nome: ");
                    String nomeExcluir = sc.nextLine();

                    Aluno alunoExcluir = dao.buscarPorNome(nomeExcluir);
                    if (alunoExcluir == null) {
                        System.out.println("Aluno não encontrado!");
                    } else {
                        dao.excluir(alunoExcluir);
                        System.out.println("Aluno removido com sucesso!");
                    }
                    break;

                case 3: 
                    System.out.print("Digite o nome: ");
                    String nomeAlterar = sc.nextLine();

                    Aluno alunoAlterar = dao.buscarPorNome(nomeAlterar);
                    if (alunoAlterar == null) {
                        System.out.println("Aluno não encontrado!");
                    } else {
                        System.out.println("Nome: " + alunoAlterar.getNome());
                        System.out.println("Email: " + alunoAlterar.getEmail());
                        System.out.println("RA: " + alunoAlterar.getRa());
                        System.out.println("Notas: " +
                                alunoAlterar.getNota1() + " - " +
                                alunoAlterar.getNota2() + " - " +
                                alunoAlterar.getNota3());

                        System.out.println("\nNOVOS DADOS:");

                        System.out.print("Nome: ");
                        alunoAlterar.setNome(sc.nextLine());

                        System.out.print("RA: ");
                        alunoAlterar.setRa(sc.nextLine());

                        System.out.print("Email: ");
                        alunoAlterar.setEmail(sc.nextLine());

                        System.out.print("Nota 1: ");
                        alunoAlterar.setNota1(sc.nextBigDecimal());

                        System.out.print("Nota 2: ");
                        alunoAlterar.setNota2(sc.nextBigDecimal());

                        System.out.print("Nota 3: ");
                        alunoAlterar.setNota3(sc.nextBigDecimal());
                        sc.nextLine();

                        dao.atualizar(alunoAlterar);
                        System.out.println("Aluno Alterado com sucesso!");
                    }
                    break;

                case 4: 
                    System.out.println("CONSULTAR ALUNO:");
                    System.out.print("Digite o nome: ");
                    String nomeBuscar = sc.nextLine();

                    Aluno alunoBuscar = dao.buscarPorNome(nomeBuscar);
                    if (alunoBuscar == null) {
                        System.out.println("Aluno não encontrado!");
                    } else {
                        System.out.println("Nome: " + alunoBuscar.getNome());
                        System.out.println("Email: " + alunoBuscar.getEmail());
                        System.out.println("RA: " + alunoBuscar.getRa());
                        System.out.println("Notas: " +
                                alunoBuscar.getNota1() + " - " +
                                alunoBuscar.getNota2() + " - " +
                                alunoBuscar.getNota3());
                    }
                    break;

                case 5: // LISTAR
                    System.out.println("Exibindo todos os alunos:");
                    List<Aluno> alunos = dao.listarTodos();

                    for (Aluno a : alunos) {
                        BigDecimal media = a.getNota1()
                                .add(a.getNota2())
                                .add(a.getNota3())
                                .divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);

                        String situacao;
                        if (media.compareTo(BigDecimal.valueOf(4)) < 0) {
                            situacao = "Reprovado";
                        } else if (media.compareTo(BigDecimal.valueOf(6)) < 0) {
                            situacao = "Recuperação";
                        } else {
                            situacao = "Aprovado";
                        }

                        System.out.println("Nome: " + a.getNome());
                        System.out.println("Email: " + a.getEmail());
                        System.out.println("RA: " + a.getRa());
                        System.out.println("Notas: " +
                                a.getNota1() + " - " +
                                a.getNota2() + " - " +
                                a.getNota3());
                        System.out.println("Média: " + media);
                        System.out.println("Situação: " + situacao);
                        System.out.println();
                    }
                    break;

                case 6:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 6);

        sc.close();
    }
}
