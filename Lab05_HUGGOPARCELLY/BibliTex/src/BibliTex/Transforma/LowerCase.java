package BibliTex.Transforma;

import BibliTex.Intefaces.Transformacao;

/**
 * Representação da transformação LowerCase no sistema
 * A classe é implementada no transformacao, para seus métodos serem utilizados no tipo genérico
 * além de implementada no Comparable, para realizar a comparação no nome da classe com as outras
 * deixando ordenado alfabeticamente.
 * @author Huggo Parcelly - 120210155
 */
public class LowerCase implements Transformacao, Comparable<LowerCase> {

    /**
     * Texto original recebido para transformação
     */
    private String textoOriginal;
    /**
     * Nome da tranformação
     */
    private String operacao = "LowerCase";

    /**
     * Método que realiza a transformação do texto recebido
     *
     * @param textoOriginal Texto original passado para ser transformado
     * @return String com o texto completo em minúsculo
     */
    public String transformar(String textoOriginal){
        this.textoOriginal = textoOriginal;
        return this.textoOriginal.toLowerCase();
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
     * @param lowerCase Classe de transformação
     * @return inteiro igual a zero quando é igual e diferente de zero quando diferente;
     */
    @Override
    public int compareTo(LowerCase lowerCase) {
        return getNome().compareTo(lowerCase.getNome());
    }
}
