package BibliTex.Logger;

import BibliTex.Intefaces.Logger;

/**
 * Representação do logger de console
 * É mostrado o nome da operação e o primeiro parâmetro passado na operação
 *
 * A classe é implementada no Logger, para seus métodos serem utilizados no tipo genérico
 * @author Huggo Parcelly - 120210155
 */
public class ConsoleLogger implements Logger {

    /**
     * Método que retorna o logger do tipo console
     *
     * @param tipoOperacao tipo de operação passada
     * @param primeiroParâmentro primeiro parâmetro recebido pela operação, passar vazio caso não exista
     * @return String no formato "[tipo de operação] primeiro parâmetro"
     */
    public String getLogger(String tipoOperacao, String primeiroParâmentro) {
        return "["+tipoOperacao+"]" + " " + primeiroParâmentro + System.lineSeparator();
    }
}
