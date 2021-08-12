package agenda;

import java.util.Objects;

public class Tag {
    /**
     * Nome da tag
     */
    private final String nomeTag;

    /**
     * Contrutor que vai criar a representação das tags
     *
     * @param nomeTag string com o nome da tag.
     */
    public Tag(String nomeTag) {
        if (nomeTag == null || nomeTag.isBlank()) {
            throw new IllegalArgumentException("TAG INVALIDA");
        }
        this.nomeTag = nomeTag;
    }

    /**
     * Método que retorna o nome da tag.
     * @return string com o nome da tag.
     */
    public String toString() {
        return this.nomeTag;
    }

    /**
     * Método de Override que vai comparar dois objetos tag.
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
        Tag tag = (Tag) o;
        return nomeTag.equals(tag.nomeTag);
    }

    /**
     * Método de Override do hashCode do objeto.
     * Localização do objeto na memória.
     *
     * @return inteiro com a localização do objeto
     */
    @Override
    public int hashCode() {
        return Objects.hash(nomeTag);
    }
}
