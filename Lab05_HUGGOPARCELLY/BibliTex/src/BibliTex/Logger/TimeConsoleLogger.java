package BibliTex.Logger;

import BibliTex.Intefaces.Logger;

/**
 * Representação do logger de time
 * É mostrado o nome da operação e tempo em milissegundo em que é realizada
 *
 * A classe é implementada no Logger, para seus métodos serem utilizados no tipo genérico
 * @author Huggo Parcelly - 120210155
 */
public class TimeConsoleLogger implements Logger {

    /**
     * Método que retorna o logger do tipo time
     *
     * @param tipoOperacao tipo de operação passada
     * @param primeiroParâmentro primeiro parâmetro recebido pela operação, passar vazio caso não exista
     * @return String no formato "[tipo de operação - time ms] primeiro parâmetro passado".
     */
    @Override
    public String getLogger(String tipoOperacao, String primeiroParâmentro) {
        long timer = (long) (System.currentTimeMillis() / Math.pow(10, 11));
        return "[" + tipoOperacao + " - " + timer + "ms] " + primeiroParâmentro + System.lineSeparator();
    }
}
