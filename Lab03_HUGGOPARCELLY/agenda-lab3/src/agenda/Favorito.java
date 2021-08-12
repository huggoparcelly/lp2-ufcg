package agenda;

import java.util.Objects;

/**
 * Representa de um contato favorito no sistema.
 * Podendo ser favoritados até 10 contatos.
 *
 * @author Huggo Parcelly - 120210155
 */
public class Favorito {

    /**
     * Contato favoritado.
     */
    private final String favorito;

    /**
     * Construtor para criar a representação de um contato favoritado.
     *
     * @param contato string com nome e sobrenome do contato.
     */
    public Favorito(String contato) {
        if (contato == null || contato.isBlank()) {
            throw new IllegalArgumentException("CONTATO INVALIDO");
        }
        this.favorito = contato;
    }

    /**
     * Método que retorna uma string com o nome do contato favoritado.
     *
     * @return string nome do contato favoritado.
     */
    public String toString() {
        return this.favorito;
    }

    /**
     * Método de Override que vai comparar dois objetos favoritos.
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
        Favorito favorito1 = (Favorito) o;
        return favorito.equals(favorito1.favorito);
    }

    /**
     * Método de Override do hashCode do objeto.
     * Localização do objeto na memória.
     *
     * @return inteiro com a localização do objeto
     */
    @Override
    public int hashCode() {
        return Objects.hash(favorito);
    }
}
