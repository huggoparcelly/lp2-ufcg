package controle;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        Scanner scanner = new Scanner(System.in);
        String escolha = "";
        while (!escolha.equals("S")) {
            escolha = menu(scanner);
            comando(escolha, sistema, scanner);
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
                        "(A)locar Aluno no Grupo e Verificar pertinência a Grupos\n" +
                        "(R)egistrar Aluno que Respondeu\n" +
                        "(I)mprimir Alunos que Responderam\n" +
                        "(O)lhaí quais Grupos o Aluno Tá.\n" +
                        "(S)im, quero Fechar o Programa!\n" +
                        "\n" +
                        "Opção> ");
        return scanner.next().toUpperCase();
    }

    private static void comando(String opcao, Sistema sistema, Scanner scanner) {
        switch (opcao) {
            case "C":
                cadastraAluno(sistema, scanner);
                break;
            case "E":
                exibeAluno(sistema, scanner);
                break;
            case "N":
                cadastraGrupo(sistema, scanner);
                break;
            case "A":
                alocarOuPertinencia(sistema, scanner);
                break;
            case "R":
                registraAlunoParticipante(sistema, scanner);
                break;
            case "I":
                exibeAlunoParticipante(sistema);
                break;
            case "O":
                exibeGruposPorAluno(sistema, scanner);
                break;
            case "S":
                sai();
                break;
            default:
                System.out.println("OPÇÃO INVÁLIDA!");
        }
    }

    private static void exibeAlunoParticipante(Sistema sistema) {
        System.out.print("\nAlunos: ");
        System.out.println(sistema.alunosParticipantes());
    }

    private static void registraAlunoParticipante(Sistema sistema, Scanner scanner) {
        System.out.print("\nRegistrar aluno participante");
        scanner.nextLine();
        System.out.print("\nMatricula: ");
        String matricula = scanner.nextLine();
        try {
            if(sistema.registrarAlunoParticipante(matricula)) {
                System.out.println("ALUNO REGISTRADO!");
            }
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
    }

    private static void exibeGruposPorAluno(Sistema sistema, Scanner scanner) {
        System.out.print("\nGrupos que o aluno faz parte");
        scanner.nextLine();
        System.out.print("\nAluno: ");
        String matricula = scanner.nextLine();
        String grupos = sistema.gruposDoAluno(matricula);
        System.out.print("Grupos: \n" + grupos);
    }

    private static void alocarOuPertinencia(Sistema sistema, Scanner scanner) {
        System.out.print("\n(A)locar Aluno ou (P)ertinência a Grupo? ");
        scanner.nextLine();
        String resposta = scanner.nextLine().toUpperCase();
        if ("A".equals(resposta)) {
            adicionaAlunoGrupo(sistema, scanner);
        } else if("P".equals(resposta)) {
            verificaAlunoNoGrupo(sistema, scanner);
        } else {
            System.out.println("OPÇÃO INVÁLIDA!");
        }
    }

    private static void verificaAlunoNoGrupo(Sistema sistema, Scanner scanner) {
        System.out.print("\nVerificar pertinencia do aluno no grupo");
        System.out.print("\nGrupo: ");
        String nomeGrupo = scanner.nextLine();
        System.out.print("Aluno: ");
        String matricula = scanner.nextLine();
        System.out.println(sistema.verificarAlunoNoGrupo(matricula, nomeGrupo));
    }

    private static void adicionaAlunoGrupo(Sistema sistema, Scanner scanner) {
        System.out.print("\nAlocar aluno em grupo");
        System.out.print("\nMatricula: ");
        String matricula = scanner.nextLine();
        System.out.print("Grupo: ");
        String grupo = scanner.nextLine();
        System.out.println(sistema.adicionarAlunoNoGrupo(matricula, grupo));
    }

    private static void cadastraGrupo(Sistema sistema, Scanner scanner) {
        System.out.print("\nCadastrar Grupo");
        System.out.print("\nGrupo: ");
        scanner.nextLine();
        String nomeGrupo = scanner.nextLine().toLowerCase();
        System.out.print("Tamanho: ");
        String tamanho = scanner.nextLine();
        if(sistema.cadastrarGrupo(nomeGrupo, tamanho)) {
            System.out.println("CADASTRO REALIZADO!");
        } else {
            System.out.println("GRUPO JÁ CADASTRADO!");
        }

    }

    private static void cadastraAluno(Sistema sistema, Scanner scanner) {
        System.out.print("\nCadastro de Aluno");
        System.out.print("\nMatricula: ");
        scanner.nextLine();
        String matricula = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Curso: ");
        String curso = scanner.nextLine();
        if(!matricula.isBlank() && !nome.isBlank() && !curso.isBlank()) {
            if(sistema.cadastrarAluno(matricula, nome, curso)) {
                System.out.println("CADASTRO REALIZADO!");
            } else {
                System.out.println("MATRÍCULA JÁ CADASTRADA");
            }
        } else {
            System.out.println("DADOS INVÁLIDOS!");
        }
    }

    private static void exibeAluno(Sistema sistema, Scanner scanner) {
        System.out.print("\nConsultar de Aluno");
        System.out.print("\nMatricula: ");
        scanner.nextLine();
        String matricula = scanner.nextLine();
        Aluno aluno = sistema.recuperaAluno(matricula);
        if(aluno == null) {
            System.out.println("ALUNO NÃO CADASTRADO.");
        } else {
            String curso = sistema.getCurso(matricula);
            System.out.println("Aluno: " + matricula + " - " + aluno + " - " + curso);
        }
    }

    private static void sai() {
        System.out.println("\nAté logo...");
        System.exit(0);
    }
}
