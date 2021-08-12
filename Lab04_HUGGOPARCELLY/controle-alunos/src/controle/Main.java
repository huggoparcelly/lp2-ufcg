package controle;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Controle controle = new Controle();

        Scanner scanner = new Scanner(System.in);
        String escolha = "";
        while (!escolha.equals("S")) {
            escolha = menu(scanner);
            comando(escolha, controle, scanner);
        }
    }

    /**
     * Exibe o menu e captura a escolha do/a usuário/a.
     *
     * @param scanner Para captura da opção do usuário.
     * @return O comando escolhido.
     */
    private static String menu(Scanner scanner) {
        System.out.print(
                "\n---\nMENU\n" +
                        "(C)adastrar Aluno\n" +
                        "(E)xibir Aluno\n" +
                        "(N)ovo grupo\n" +
                        "(A)locar Aluno no Grupo ou (P)ertinência a Grupos\n" +
                        "(R)egistrar Aluno que Respondeu\n" +
                        "(I)mprimir Alunos que Responderam\n" +
                        "(O)lhaí quais Grupos o Aluno Tá.\n" +
                        "(S)im, quero Fechar o Programa!\n" +
                        "\n" +
                        "Opção> ");
        return scanner.next().toUpperCase();
    }

    private static void comando(String opcao, Controle controle, Scanner scanner) {
        switch (opcao) {
            case "C":
                cadastraAluno(controle, scanner);
                break;
            case "E":
                exibeAluno(controle, scanner);
                break;
//            case "N":
//                novoGrupo();
//                break;
//            case "A":
//                adicionaAlunoGrupo(scanner);
//                break;
//            case "R":
//                registraAlunoParticipante(scanner);
//                break;
//            case "I":
//                exibeAlunoParticipante();
//            case "O":
//                exibeGruposPorAluno();
            case "S":
                sai();
                break;
            default:
                System.out.println("OPÇÃO INVÁLIDA!");
        }
    }

    private static void cadastraAluno(Controle controle, Scanner scanner) {
        System.out.print("\nCadastro de Aluno");
        System.out.print("\nMatricula: ");
        scanner.nextLine();
        String matricula = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Curso: ");
        String curso = scanner.nextLine();
        if(controle.criaObjetoAluno(matricula, nome, curso)) {
            System.out.println("CADASTRO REALIZADO!");
        } else {
            System.out.println("MATRÍCULA JÁ CADASTRADA!");
        }
    }

    private static void exibeAluno(Controle controle, Scanner scanner) {
        System.out.print("\nConsultar de Aluno");
        System.out.print("\nMatricula: ");
        scanner.nextLine();
        String matricula = scanner.nextLine();
        Aluno aluno = controle.recuperaAluno(matricula);
        if(aluno != null) {
            String curso = controle.getCurso();
            System.out.println("Aluno: " + matricula + " - " + aluno.toString() + " - " + curso);
        } else {
            System.out.println("Aluno não cadastrado.");
        }
    }

    private static void sai() {
        System.out.println("\nAté logo...");
        System.exit(0);
    }
}
