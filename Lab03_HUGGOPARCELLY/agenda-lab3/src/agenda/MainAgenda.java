package agenda;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular uma agenda de contatos.
 * 
 * @author nazarenoandrade
 * @author Huggo Parcelly - 120210155
 *
 */
public class MainAgenda {

	public static void main(String[] args) {
		Agenda agenda = new Agenda();

		System.out.println("Carregando agenda inicial");
		try {
			/*
			 * Essa é a maneira de lidar com possíveis erros por falta do arquivo. 
			 */
			carregaAgenda("agenda_inicial.csv", agenda);
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro lendo arquivo: " + e.getMessage());
		}

		Scanner scanner = new Scanner(System.in);
		String escolha = "";
		while (!escolha.equals("S")) {
			escolha = menu(scanner); // retorna a opção escolhida
			comando(escolha, agenda, scanner); //
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
						"(C)adastrar Contato\n" + 
						"(L)istar Contatos\n" + 
						"(E)xibir Contato\n" +
						"(F)avoritos\n" +
						"(A)dicionar Favorito\n" +
						"(T)ags\n" +
						"(S)air\n" + 
						"\n" +
						"Opção> ");
		return scanner.next().toUpperCase();
	}

	/**
	 * Interpreta a opção escolhida por quem está usando o sistema.
	 * 
	 * @param opcao   Opção digitada.
	 * @param agenda  A agenda que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	private static void comando(String opcao, Agenda agenda, Scanner scanner) {
		switch (opcao) {
		case "C":
			cadastraContato(agenda, scanner);
			break;
		case "L":
			listaContatos(agenda);
			break;
		case "E":
			exibeContato(agenda, scanner);
			break;
		case "F":
			listaFavorito(agenda);
			break;
		case "A":
			adicionaFavorito(agenda, scanner);
			break;
		case "T":
			adicionaTag(agenda, scanner);
			break;
		case "S":
			sai();
			break;
		default:
			System.out.println("OPÇÃO INVÁLIDA!");
		}
	}

	/**
	 * Adiciona uma tag a um ou vários contatos
	 * @param agenda Agenda sendo manipulada.
	 * @param scanner Objeto scanner para capturar o contato, o nome da tag e a sua posição.
	 */
	private static void adicionaTag(Agenda agenda, Scanner scanner) {
		System.out.print("Contato(s)> ");
		scanner.nextLine();
		String posicaoContatos = scanner.nextLine();
		System.out.print("Tag> ");
		String nomeTag = scanner.next();
		System.out.print("Posição tag> ");
		int posicaoTag = scanner.nextInt();
		agenda.cadastraTag(posicaoContatos, nomeTag, posicaoTag);
	}


	/**
	 * Cadastra um contato na agenda.
	 *
	 * @param agenda A agenda.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void cadastraContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nPosição> ");
		int posicao = scanner.nextInt();
		if (posicao < 1 || posicao > 100) {
			System.out.println("POSIÇÃO INVÁLIDA");
		}else {
			System.out.print("Nome> ");
			scanner.nextLine();
			String nome = scanner.nextLine();
			System.out.print("Sobrenome> ");
			String sobrenome = scanner.next();
			System.out.print("Telefone> ");
			scanner.nextLine();
			String telefone = scanner.nextLine();
			if (nome.isBlank() || telefone.isBlank()) {
				System.out.println("CONTATO INVALIDO");
			}
			else {
				if (agenda.verificaContato(nome, sobrenome, telefone)) {
					System.out.println("CONTATO JA CADASTRADO");
				} else {
					agenda.cadastraContato(posicao, nome, sobrenome, telefone);
					System.out.println("CADASTRO REALIZADO");
				}

			}
		}
	}

	/**
	 * Imprime lista de contatos da agenda.
	 *
	 * @param agenda A agenda sendo manipulada.
	 */
	private static void listaContatos(Agenda agenda) {
		System.out.println("\nLista de contatos: ");
		String contatos = agenda.listaContato();
		System.out.print(contatos);
	}


	/**
	 * Imprime os detalhes de um dos contatos da agenda.
	 *
	 * @param agenda A agenda sendo manipulada.
	 * @param scanner Scanner para capturar qual contato.
	 */
	private static void exibeContato(Agenda agenda, Scanner scanner) {
		System.out.print("Contato> ");
		int posicao = scanner.nextInt();
		if (posicao < 1 || posicao > 100) {
			System.out.println("POSIÇÃO INVÁLIDA");
		}
		else {
			System.out.print(agenda.exibicaoContato(posicao));
		}
	}

	/**
	 * Exibe a lista de contatos favoritados.
	 * @param agenda A agenda sendo manipulada.
	 */
	private static void listaFavorito(Agenda agenda) {
		String favoritos = agenda.listarFavoritos();
		System.out.print(favoritos);
	}

	/**
	 * Adiciona um contato aos favoritos.
	 * @param agenda A agenda sendo manipulada.
	 * @param scanner Scanner para capturar qual contato e a posiçao nos favoritos.
	 */
	private static void adicionaFavorito(Agenda agenda, Scanner scanner) {
		System.out.print("\nContato> ");
		int posicaoContato = scanner.nextInt();
		if (posicaoContato < 1 || posicaoContato > 100) {
			System.out.println("POSIÇÃO INVÁLIDA");
		} else {
			System.out.print("Posição> ");
			int posicaoFavorito = scanner.nextInt();
			if (agenda.cadastraFavorito(posicaoContato, posicaoFavorito)) {
				System.out.println("CONTATO FAVORITADO NA POSIÇÃO " + posicaoFavorito);

			} else {
				System.out.println("CONTATO JÁ FAVORITADO");
			}
		}
	}

	/**
	 * Sai da aplicação.
	 */
	private static void sai() {
		System.out.println("\nVlw flw o/");
		System.exit(0);
	}


	/**
	 * Lê uma agenda de um arquivo csv. 
	 * 
	 * @param arquivoContatos O caminho para o arquivo.
	 * @param agenda A agenda que deve ser populada com os dados.
	 * @throws IOException Caso o arquivo não exista ou não possa ser lido.
	 */
	private static void carregaAgenda(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
		LeitorDeAgenda leitor = new LeitorDeAgenda();
		int carregados =  leitor.carregaContatos(arquivoContatos, agenda);
		System.out.println("Carregamos " + carregados + " registros.");
	}
}
