package BibliTex.Logger;

import BibliTex.Intefaces.Logger;

import java.util.Objects;

/**
 * Representação do logger de Breakpoint
 * É mostrado o nome da operação que é invocada, passada por parâmetro do construtor da classe
 *
 * A classe é implementada no Logger, para seus métodos serem utilizados no tipo genérico
 * @author Huggo Parcelly - 120210155
 */
public class BreakpointLogger implements Logger {
    /**
     * variável Método recebida por parâmetro no construtor.
     */
    private String metodo;

    /**
     * Construtor que inicializa a variável método
     * @param metodoInvocado tipo de método passado
     */
    public BreakpointLogger(String metodoInvocado) {
        this.metodo = metodoInvocado;
    }

    /**
     * Método que retorna o logger do tipo breakpoint
     * @param tipoOperacao tipo de operação passada
     * @param primeiroParâmentro primeiro parâmetro recebido pela operação, passar vazio caso não exista
     * @return String no formato "[INVOCADO - nome do método]"
     */
    @Override
    public String getLogger(String tipoOperacao, String primeiroParâmentro) {
        if(Objects.equals(tipoOperacao, metodo)) {
            return "[INVOCADO - " + metodo + "]" + System.lineSeparator();
        }
        return "";
    }

}
