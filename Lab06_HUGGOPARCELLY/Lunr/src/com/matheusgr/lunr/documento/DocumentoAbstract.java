package com.matheusgr.lunr.documento;

import biblitex.TransformaTexto;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public abstract class DocumentoAbstract implements Documento {
    private final String id;
    private String[] split;
    private String limpo;
    private final String original;
    private TransformaTexto tt;

    /**
     * Contrutor padrão do documento, que recebe o Id e o documento original
     *
     * @param id ID do documento a ser criado.
     * @param original Documento a ser criado.
     */
    public DocumentoAbstract(String id, String original) {
        this.id = id;
        this.original = original;
    }

    /**
     * Método transforma documento, irá tratar o documento original
     * utilizando a biblioteca Biblitex
     *
     * A Biblitex retorna o texto transformado, para um novo tratamento
     *
     * @return Retorna o texto limpo
     */
    public String transformaDocumento() {
        this.tt = new TransformaTexto();
        String txt = original;

        if(this.original.contains("<head>")) {
            txt = tt.transforma(TransformaTexto.Algoritmos.html, original);
        }
        if(this.original.contains("@author")) {
            txt = tt.transforma(TransformaTexto.Algoritmos.java, original);
        }

        this.limpo = tt.transforma(TransformaTexto.Algoritmos.clean, txt).strip();
        return this.limpo;
    }

    /**
     * Retorna a quantidade de texto útil, sendo definido como a quantidade de
     * caracteres úteis (sem caracteres estranhos e sem espaços) sobre o total de
     * caracteres original (incluindo espaços).
     *
     * @return Percentual de texto útil (entre 0 e 1, inclusives).
     */
    public double metricaTextoUtil() {
        long extractedSize = tt.transforma(TransformaTexto.Algoritmos.cleanSpaces, this.limpo)
                .length();
        return (1.0 * extractedSize) / this.original.length();
    }

    /**
     * Retorna a identificação do documento. A documentação é definida pelo próprio
     * documento e é uma String sem formatação específica. Todo documento gerado
     * pelo Lunr começa com o símbolo "_". O identificador não pode ser vazio ou
     * nulo.
     *
     * @return Identificação do documento.
     * @throws NullPointerException Caso o ID seja nulo.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Retorna os termos do texto em formato de String. Não podem existir termos
     * vazios (só de espaços, tabulações ou string vazia).
     *
     * @return Array de termos extraídos do documento.
     */
    public String[] getTexto() {
        if (this.split == null) {
            this.split = tt.transforma(TransformaTexto.Algoritmos.cleanLines, this.limpo)
                    .split(" ");
            Arrays.sort(this.split);
        }
        return this.split;
    }

    /**
     * Retorna metadados referente ao documento. Detalhes como seu tipo, autor, ou
     * características próprias do documento.
     * <p>
     * Idealmente, todo metadado terá como características:
     * <p>
     * LINHAS, TAMANHO (número de caracteres), METADATADATE (hora atual do sistema
     * na criação dos metadados, em ms), TIPO.
     *
     * @return Mapa com os metadados descrito em forma textual.
     */
    public abstract Map<String, String> getMetadados();

    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DocumentoAbstract other = (DocumentoAbstract) obj;
        return Objects.equals(id, other.id);
    }

    public String toString() {
        return "===" + this.id + System.lineSeparator() + this.limpo;
    }

}
