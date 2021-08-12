package agenda;

import java.util.Objects;

/**
 * Uma agenda que mantém uma lista de contatos com posições. Podem existir 100 contatos. 
 * 
 * @author nazareno
 * @author Huggo Parcelly - 120210155
 *
 */
public class Agenda {
	/**
	 * Indica a quantidade de contatos que podem ser salvos na agenda.
	 */
	private static final int TAMANHO_AGENDA = 100;
	/**
	 * Indica o quantos contatos podem ser favoritados.
	 */
	private static final int TAMANHO_FAVORITOS = 10;

	/**
	 * Armazena todos os contatos da agenda.
	 */
	private final Contato[] contatos;
	/**
	 * Armazena os contatos favoritados.
	 */
	private final Favorito[] favoritos;

	/**
	 * Construtor para criar uma agenda com espaço para 100 contatos e 10 para favoritos.
	 */
	public Agenda() {
		this.contatos = new Contato[TAMANHO_AGENDA];
		this.favoritos = new Favorito[TAMANHO_FAVORITOS];
	}

	/**
	 * Cadastra um contato em uma posição. Um cadastro em uma posição que já existe sobrescreve o anterior.
	 *
	 * @param posicao   Posição do contato.
	 * @param nome      Nome do contato.
	 * @param sobrenome Sobrenome do contato.
	 * @param telefone  Telefone do contato.
	 * @return booleano quando o cadastro é ralizado.
	 */
	public boolean cadastraContato(int posicao, String nome, String sobrenome, String telefone) {
        posicao -= 1;
		if(validaPosicaoContato(posicao)) {
			throw new IllegalArgumentException("POSIÇÃO INVÁLIDA");
		}
        if (nome == null || nome.isBlank() || telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("CONTATO INVALIDO");
        } else if(verificaContato(nome, sobrenome, telefone)) {
			throw new IllegalArgumentException("CONTATO JA CADASTRADO");
		}
        else {
            this.contatos[posicao] = new Contato(nome, sobrenome, telefone);
            return true;
        }

	}

	/**
	 * Método que verifica se um contato já existe dentro da agenda.
	 * Usa como forma de verificação o nome e sobrenome.
	 * Retornando verdadeiro ou falso.
	 * @param nome string com nome do contato.
	 * @param sobrenome string com sobrenome do contato.
	 * @param telefone string com telefone do contato.
	 * @return booleano que indica a existência ou não do contato.
	 */
	public boolean verificaContato(String nome, String sobrenome, String telefone) {
		Contato novoContato = new Contato(nome, sobrenome, telefone);
		for (Contato contato : contatos) {
            if (contato != null) {
                if (novoContato.equals(contato)) {
                    return true;
                }
            }
        }
		return false;
	}

	private Contato[] getContatos() {
		return this.contatos.clone();
	}

	/**
	 * Método que verifica os contatos não nulos e concatena em uma string para retorna-lá.
	 *
	 * @return string com nome e sobrenome dos contatos salvos na agenda, cada contato em uma linha.
	 */

	public String listaContato() {
		String listaContatos = "";
		Contato[] contatos = getContatos();
		for(int i = 0; i < contatos.length; i++){
			if(contatos[i] != null){
				listaContatos += (i + 1) + " - " + contatos[i] + System.lineSeparator();
			}
		}
		return listaContatos;
	}


	/**
	 * Acessa os dados de um contato específico.
	 *
	 * @param posicao inteiro com a posição do contato na agenda.
	 * @return Dados do contato. Null se não há contato na posição.
	 */
	public String getContato(int posicao) {
		if(validaPosicaoContato(posicao)) {
			throw new IllegalArgumentException("POSIÇÃO INVÁLIDA");
		}
		return contatos[posicao].toString();
	}

    private boolean validaPosicaoContato(int posicao) {
		return posicao < 0 || posicao > 99;
    }

	private String getTelefone(int posicao) {
		if(validaPosicaoContato(posicao)) {
			throw new IllegalArgumentException("POSIÇÃO INVÁLIDA");
		}
		return contatos[posicao].telefone();
	}

	/**
	 * Método que cadastra um contato nos favoritos, através da posição do contato e a posição no favorito.
	 *
	 * @param posicaoContato inteiro com a posição do contato.
	 * @param posicaoFavorito inteiro com a posição do favorito.
	 * @return booleano true quando o cadastro é realizado.
	 */
	public boolean cadastraFavorito(int posicaoContato, int posicaoFavorito) {
		posicaoContato -= 1;
        String contato = getContato(posicaoContato);
		if (posicaoFavorito < 1 || posicaoFavorito > 10) {
            throw new IllegalArgumentException("POSIÇÃO INVÁLIDA");
        } else if (verificaFavoritos(contato)) {
			throw new IllegalArgumentException("CONTATO JÁ CADASTRADO");
		}
        else {
            posicaoFavorito -= 1;
            this.favoritos[posicaoFavorito] = new Favorito(contato);
            return true;
        }
	}

	private boolean verificaFavoritos(String contato) {
		Favorito novoFavorito = new Favorito(contato);
		for (Favorito favorito : favoritos) {
			if (novoFavorito.equals(favorito)) {
				return true;
			}
		}
		return false;
	}

	private Favorito[] getFavoritos() {
		return this.favoritos.clone();
	}

	/**
	 * Método que verifica os contatos favoritos não nulos e concatena em uma string para retorna-lá.
	 *
	 * @return string com os contatos favoritados, cada contato em uma linha.
	 */
	public String listarFavoritos() {
		String listaFavoritos = "";
		Favorito[] favoritos = getFavoritos();
		for(int i = 0; i < favoritos.length; i++){
			if(favoritos[i] != null){
				listaFavoritos += (i + 1) + " - " + favoritos[i] + System.lineSeparator();
			}
		}
		return listaFavoritos;
	}

	/**
	 * Método que realiza o cadastro das tags.
	 * Podem ser até 5 tags por contato.
	 * Cada tag pode ser cadastrada em vários contatos de uma só vez.
	 * São feitas validações se o contato já contém a tag, posição da tag e contato existente.
	 * Ao cadastrar uma tag em uma posição existente no contato a anterior é sobrescrita.
	 *
	 * @param posicoesContatos string com uma ou mais posições de contatos para cadastrar a tag.
	 * @param nomeTag string com o nome da tag, não aceita espaços.
	 * @param posicaoTag int posição da tag, podendo ser de 1 a 5.
	 * @return booleano true quando o cadastro é realizado.
	 */
	public boolean cadastraTag(String posicoesContatos, String nomeTag , int posicaoTag) {

		if (posicaoTag < 1 || posicaoTag > 5) {
			throw new IllegalArgumentException("POSIÇÃO DA TAG INVÁLIDA");
		} else {
			String[] posicoes = posicoesContatos.split(" ");
			for (String pos : posicoes) {
				int posicaoContato = Integer.parseInt(pos);
				posicaoContato -= 1;
				if(contatos[posicaoContato] == null) {
					throw new IllegalArgumentException("CONTATO INEXISTENTE");
				}
				if(validaPosicaoContato(posicaoContato)) {
					throw new IllegalArgumentException("POSIÇÃO INVÁLIDA");
				}
				if (verificaTags(nomeTag, posicaoContato)) {
					throw new IllegalArgumentException("TAG JÁ CADASTRADA");
				}
				else {
					contatos[posicaoContato].cadastraTag(nomeTag, posicaoTag);
				}
			}
			return true;
		}
	}

	private boolean verificaTags(String nomeTag, int posicaoContato) {
		Tag novaTag = new Tag(nomeTag);
		if(validaPosicaoContato(posicaoContato)) {
			throw new IllegalArgumentException("POSIÇÃO INVÁLIDA");
		}
			for (Tag tag : contatos[posicaoContato].tags) {
				if (novaTag.equals(tag)) {
					return true;
				}
			}
		return false;
	}

	private String getTag(int posicaoContato) {
		if(validaPosicaoContato(posicaoContato)) {
			throw new IllegalArgumentException("POSIÇÃO INVÁLIDA");
		}
		return contatos[posicaoContato].nomeTag();
	}

	/**
	 * Método que exibe os contatos de várias formas, através da sua posição.
	 * O contato é exibido na forma contato + telefone.
	 * Se adicionado aos favoritos a exibição mostra ❤ + contato + telefone.
	 * Se adicionada uma tag a exibição mostra contato + telefone + tag.
	 * Um contato favoritado com tag é exibido ❤ + contato + tag + telefone.
	 *
	 * @param posicaoContato inteiro que recebe a posição do contato.
	 * @return string com a exibição do contato.
	 */
	public String exibicaoContato(int posicaoContato) {
		posicaoContato -= 1;
		if (validaPosicaoContato(posicaoContato)) {
			throw new IllegalArgumentException("POSIÇÃO INVÁLIDA");
		}
		if (contatos[posicaoContato] == null){
			throw new IllegalArgumentException("CONTATO INEXISTENTE");
		} else {
			String contato = this.getContato(posicaoContato);
			String telefone = this.getTelefone(posicaoContato);
			String tag = this.getTag(posicaoContato);

			if (this.verificaFavoritos(contato) && !Objects.equals(tag, "") && verificaTags(tag, posicaoContato)) {
				return "❤ " + contato + "\n" + telefone + "\n" + tag + System.lineSeparator();
			} else if (this.verificaFavoritos(contato)) {
				return "❤ " + contato + "\n" + telefone + System.lineSeparator();
			} else if (!Objects.equals(tag, "") && verificaTags(tag, posicaoContato)) {
				return contato + "\n" + telefone + "\n" + tag + System.lineSeparator();
			} else {
				return contato + "\n" + telefone + System.lineSeparator();
			}
		}
	}
}
