package controle;

import java.util.Scanner;

/**
 * Interface do usuário com menus texto para manipular o cadastro dos alunos e de grupos
 *
 * @author Huggo Parcelly
 */
public class Main {

    /**
     * Função principal do sistema
     * @param args
     */
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
     * Exibe o menu e captura a escolha do usuário.
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

    /**
     * Interpreta a opção escolhida pelo usuário
     *
     * @param opcao Opção digitada
     * @param sistema Sistema que é manipulado
     * @param scanner Objeto scanner para inputs
     */
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

    /**
     * Função que exibe os alunos que participaram na aula.
     *
     * @param sistema O sistema que é manipulado
     */
    private static void exibeAlunoParticipante(Sistema sistema) {
        System.out.print("\nAlunos: ");
        System.out.println(sistema.alunosParticipantes());
    }

    /**
     * Função que registra um aluno que participou durante a aula
     * O registro é feito através da matrícula do aluno
     *
     * @param sistema O sistema que é manipulado
     * @param scanner Objeto scanner para inputs
     */
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

    /**
     * Função que exibe os grupos que o aluno faz parte
     * A verificação é feita através da matrícula do aluno
     *
     * @param sistema O sistema que é manipulado
     * @param scanner Objeto scanner para inputs
     */
    private static void exibeGruposPorAluno(Sistema sistema, Scanner scanner) {
        System.out.print("\nGrupos que o aluno faz parte");
        scanner.nextLine();
        System.out.print("\nAluno: ");
        String matricula = scanner.nextLine();
        String grupos = sistema.gruposDoAluno(matricula);
        System.out.print("Grupos: \n" + grupos);
    }

    /**
     * Função que adiciona e verifica a pertinência de um aluno em um grupo
     * Ao selecionar a opção "A", o usuário pode adicionar um aluno a um grupo,
     * através da função "adicionaAlunoGrupo".
     * Ao selecionar a opção "P", o usuário verifica se o aluno pertence ou não a um grupo,
     * através da função "verificaAlunoNoGrupo"
     *
     * @param sistema O sistema que é manipulado
     * @param scanner Objeto scanner para inputs
     */
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

    /**
     * Função que verifica se o aluno está em um grupo
     * A pesquisa é realizada através do nome do grupo e da matricula do aluno
     *
     * @param sistema O sistema que é manipulado
     * @param scanner Objeto scanner para inputs
     */
    private static void verificaAlunoNoGrupo(Sistema sistema, Scanner scanner) {
        System.out.print("\nVerificar pertinencia do aluno no grupo");
        System.out.print("\nGrupo: ");
        String nomeGrupo = scanner.nextLine();
        System.out.print("Aluno: ");
        String matricula = scanner.nextLine();
        System.out.println(sistema.verificarAlunoNoGrupo(matricula, nomeGrupo));
    }

    /**
     * Função que adiciona o aluno ao grupo
     * O aluno é adicionado ao grupo pela matrícula associando com o nome do grupo
     *
     * @param sistema O sistema que é manipulado
     * @param scanner Objeto scanner para inputs
     */
    private static void adicionaAlunoGrupo(Sistema sistema, Scanner scanner) {
        System.out.print("\nAlocar aluno em grupo");
        System.out.print("\nMatricula: ");
        String matricula = scanner.nextLine();
        System.out.print("Grupo: ");
        String grupo = scanner.nextLine();
        System.out.println(sistema.adicionarAlunoNoGrupo(matricula, grupo));
    }

    /**
     * Função que cadastra um grupo
     * O cadastrado é feito com o nome do grupo e o tamanho que é opcional
     *
     * @param sistema O sistema que é manipulado
     * @param scanner Objeto scanner para inputs
     */
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

    /**
     * Função que cadastra os alunos no sistema
     * O cadastro é feito com a matrícula, nome e curso
     *
     * @param sistema O sistema que é manipulado
     * @param scanner Objeto scanner para inputs
     */
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

    /**
     * Exibe os detalhes dos alunos
     * A busca é feita através da matrícula
     *
     * @param sistema O sistema que é manipulado
     * @param scanner Objeto scanner para inputs
     */
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

    /**
     * Função que sai da aplicação
     */
    private static void sai() {
        System.out.println("\nAté logo...");
        System.exit(0);
    }
}
