package BibliTex.Intefaces;

/**
 * Interface de logger que dará origem aos demais tipos de logs
 */
public interface Logger {
    /**
     * Méotodo genérico para realizar os diferentes tipos de logs
     * @param tipoOperacao tipo de operação passada
     * @param primeiroParâmentro primeiro parâmetro recebido pela operação, passar vazio caso não exista
     * @return String no formato do logger selecionado.
     */
    String getLogger(String tipoOperacao, String primeiroParâmentro);
}
