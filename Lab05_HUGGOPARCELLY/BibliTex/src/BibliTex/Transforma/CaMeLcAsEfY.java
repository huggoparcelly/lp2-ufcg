package BibliTex.Transforma;

import BibliTex.Intefaces.Transformacao;

/**
 * Representação da transformação CaMeLcAsEfY no sistema
 * A classe é implementada no transformacao, para seus métodos serem utilizados no tipo genérico
 * além de implementada no Comparable, para realizar a comparação no nome da classe com as outras
 * deixando ordenado alfabeticamente.
 * @author Huggo Parcelly - 120210155
 */
public class CaMeLcAsEfY implements Transformacao, Comparable<CaMeLcAsEfY> {
    /**
     * Texto original recebido para transformação
     */
    private String textoOriginal;
    /**
     * Nome da tranformação
     */
    private String operacao = "CaMeLcAsEfY";

    /**
     * Método que realiza a transformação do texto recebido
     * @param textoOriginal Texto original passado para ser transformado
     * @return String com o texto passado no formato que as letras na posição par sejam maiuscula
     * e na posição impar minusculas.
     */
    public String transformar(String textoOriginal){
        this.textoOriginal = textoOriginal;
        String textoTransformado = "";
        for (int i = 0; i < textoOriginal.length(); i+=1) {
            if (i % 2 == 0) {
                textoTransformado += textoOriginal.toUpperCase().charAt(i);
            }
            if (i % 2 != 0) {
                textoTransformado += textoOriginal.toLowerCase().charAt(i);
            }
        }
        return textoTransformado;
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
     * @param caMeLcAsEfY Classe de transformação
     * @return inteiro igual a zero quando é igual e diferente de zero quando diferente;
     */
    @Override
    public int compareTo(CaMeLcAsEfY caMeLcAsEfY) {
        return getNome().compareTo(caMeLcAsEfY.getNome());
    }
}
