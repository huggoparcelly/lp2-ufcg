package BibliTex.Intefaces;

/**
 * Interface de transformacao, que dará origem às demais transformações
 */
public interface Transformacao {
    /**
     * Método genérico que realiza a transformação dentro de cada classe que transforma.
     * @param textoOriginal Texto original passado para ser transformado
     * @return String com o texto transformado
     */
    public String transformar(String textoOriginal);

    /**
     * Método genérico que captura o nome de cada transformaçõa
     * @return String com o nome da transformação
     */
    public String getNome();
}
