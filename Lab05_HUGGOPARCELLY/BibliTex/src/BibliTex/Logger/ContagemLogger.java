package BibliTex.Logger;

import BibliTex.Intefaces.Logger;

import java.util.*;

/**
 * Representação do logger de contagem
 * É mostrado o nome da operação e quantas vezes ela foi realizada
 *
 * A classe é implementada no Logger, para seus métodos serem utilizados no tipo genérico
 * @author Huggo Parcelly - 120210155
 */
public class ContagemLogger implements Logger {
    /**
     * Método ArrayLista para armazenar e manipular as operações realizadas
     */
    private final ArrayList<String> listaOperacoes;
    /**
     * Método Map para armazenar as operações e a contagem de cada uma
     */
    private final Map<String, Integer> countMap;
    /**
     * Variável String para retornar a lista de operações e a contagem.
     */
    private String operacoes = "";

    /**
     * Construtor do logger
     * Inicializa a lista e o map
     */
    public ContagemLogger() {
        this.listaOperacoes = new ArrayList<>();
        this.countMap = new HashMap<>();
    }

    /**
     * Método toString retorna cada operação e a contagem
     *
     * @return String no formato "operação - contagem"
     */
    public String toString() {
        for (String item: listaOperacoes) {
            if(countMap.containsKey(item)) {
                countMap.put(item, countMap.get(item) + 1);
            }
            else {
                countMap.put(item, 1);
            }
        }

        for (String item: countMap.keySet()) {
            operacoes += item + " - " + countMap.get(item) + System.lineSeparator();
        }
        return operacoes;
    }

    /**
     * Método que adiciona o tipo de operação na lista de operações
     *
     * @param tipoOperacao tipo de operação passada
     * @param primeiroParâmentro primeiro parâmetro recebido pela operação, passar vazio caso não exista
     * @return String vazia
     */
    @Override
    public String getLogger(String tipoOperacao, String primeiroParâmentro) {
        if(tipoOperacao != null) {
            this.listaOperacoes.add(tipoOperacao);
        }
        return "";
    }

}
