package BibliTex.Transforma;

import BibliTex.Intefaces.Transformacao;

/**
 * Representação da transformação de UpperCase no sistema
 * A classe é implementada no transformacao, para seus métodos serem utilizados no tipo genérico
 * além de implementada no Comparable, para realizar a comparação no nome da classe com as outras
 * deixando ordenado alfabeticamente.
 * @author Huggo Parcelly - 120210155
 */
public class UpperCase implements Transformacao, Comparable<UpperCase> {
    /**
     * Texto original recebido para transformação
     */
    private String textoOriginal;
    /**
     * Nome da tranformação
     */
    private String operacao = "UpperCase";

    /**
     * Método que realiza a transformação do texto recebido
     *
     * @param textoOriginal Texto original passado para ser transformado
     * @return String com o texto completo em MAIÚSCULO.
     */
    public String transformar(String textoOriginal){
        this.textoOriginal = textoOriginal;
        return this.textoOriginal.toUpperCase();
    }

    /**
     * Método que retorna o nome da transformação
     *
     * @return String com o nome da transformação
     */
    public String getNome() {
        return this.operacao;
    }

    /**
     * Método que compara o nome da classe com as outras, ordenando alfabeticamente
     * @param upperCase Classe de transformação
     * @return inteiro igual a zero quando é igual e diferente de zero quando diferente;
     */
    @Override
    public int compareTo(UpperCase upperCase) {
        return getNome().compareTo(upperCase.getNome());
    }
}
