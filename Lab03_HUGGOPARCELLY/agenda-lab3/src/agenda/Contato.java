package agenda;

import java.util.Objects;

/**
 * Representa de um contato no sistema.
 * Cada contato é identificado unicamente pelo nome e sobrenome.
 * Cada contato pode ter um telefone e até 5 tags.
 *
 * @author Huggo Parcelly - 120210155
 */

public class Contato {
    /**
     * Indica a quantidade de tags que um contato pode ter.
     */
    private static final int TAMANHO_TAG = 5;

    /**
     * O nome do contato.
     */
    private final String nome;
    /**
     * O sobrenome do contato
     */
    private final String sobrenome;
    /**
     * O telefone do contato
     */
    private final String telefone;
    /**
     * Armazena as tags do contato.
     */
    public final Tag[] tags;

    /**
     * Construtor para criar a representação do contato, com seu nome, sobrenome e telefone.
     *
     * @param nome string com o nome do contato.
     * @param sobrenome string com o sobrenome do contato.
     * @param telefone string com o telefone do contato.
     */
    public Contato(String nome, String sobrenome, String telefone){
        if (nome == null || nome.isBlank() || telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("CONTATO INVALIDO");
        }
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.tags = new Tag[TAMANHO_TAG];
    }

    /**
     * Método que retorna o nome do contato
     *
     * @return o nome do contato
     */
    public String getNome() { return this.nome; }

    /**
     * Método que retorna o sobrenome do contato
     *
     * @return o telefone do sobrenome
     */
    public String getSobrenome() { return this.sobrenome; }

    /**
     * Método que retorna o telefone do contato
     *
     * @return o telefone do contato
     */
    public String telefone() {
        return this.telefone;
    }

    /**
     * Método que retorna uma string com nome e sobrenome do contato de separados por um espaço.
     *
     * @return string com nome completo do contato.
     */
    public String toString() {
        return this.getNome() + " " + this.getSobrenome();
    }

    /**
     * Método para casatrar uma tag, recebendo o nome e a posição da tag.
     *
     * @param nomeTag string com o nome da tag.
     * @param posicaoTag inteiro com a posição da tag.
     * @return booleano true quando a tag é cadastrada.
     */
    public boolean cadastraTag(String nomeTag , int posicaoTag) {
        posicaoTag -= 1;
        this.tags[posicaoTag] = new Tag(nomeTag);
        return true;
    }

    /**
     * Método que retorna uma sting com o nome da tag.
     * É feito uma verificação se a tag existe, para ser concatenada com uma variáavel para saída da mesma.
     *
     * @return string com o nome das tags.
     */
    public String nomeTag() {
        String tagContato = "";
        for (Tag tag : tags) {
            if(tag != null) {
                tagContato += tag;
            }
        }
        return tagContato;
    }

    /**
     * Método de Override que vai comparar dois objetos contato.
     * Verifica se não é nulo.
     * Verifica se são da mesma classe.
     *
     * @param o Objeto para comparação
     * @return booleano.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!o.getClass().equals(this.getClass())) return false;
        Contato contato = (Contato) o;
        return nome.equals(contato.nome) && sobrenome.equals(contato.sobrenome);
    }

    /**
     * Método de Override do hashCode do objeto.
     * Localização do objeto na memória.
     *
     * @return inteiro com a localização do objeto
     */
    @Override
    public int hashCode() {
        return Objects.hash(nome, sobrenome);
    }

}

