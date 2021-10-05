package com.matheusgr.lunr.busca;

import com.matheusgr.lunr.documento.Documento;
import com.matheusgr.lunr.documento.DocumentoService;

import java.util.HashMap;
import java.util.Map;

/**
 * Busca avançada, realiza busca através dos metadados do documento
 *
 * Passado os valores dos metadados, são retornados todos os documentos que
 * contenham todos os metadados buscados
 *
 * Os documentos que não possuem os metadados não devem ser retornados
 */
public class BuscaAvancada implements Busca {

    private final Map<String, String> metadados;

    /**
     * Construtor padrão para receber e validar os metados passados
     *
     * Deve conter pelo menos um metado.
     *
     * @param metadados Metados a serem pesquisados
     */
    public BuscaAvancada(Map<String, String> metadados) {
        new ValidadorBusca().validaMetadados(metadados);
        this.metadados = metadados;
    }

    /**
     * Realiza a busca a partir da consulta ao DocumentoService.
     *
     * O DocumentoService realiza apenas operações simples de busca
     *
     * @param ds DocumentoService a ser utilizado para busca.
     * @return Mapa com os documentos encontrados
     */
    public Map<Documento, Integer> busca(DocumentoService ds) {
        Map<Documento, Integer> respostaDocumento = new HashMap<>();

        for (Documento d : ds.buscaAvancada(metadados)) {
            respostaDocumento.put(d, respostaDocumento.getOrDefault(d, 0) + 1);
        }

        return respostaDocumento;
    }

    /**
     * Descreve uma consulta para explicar a consulta que foi feita.
     *
     *
     * @return Descrição da busca, onde cada linha representa um parâmetro de busca
     * 	      e as colunas representam um detelhamento de cada parâmetro.
     */
    @Override
    public String[][] descreveConsulta() {
        String[][] resultado = new String[this.metadados.size()][];
        for (int i = 0; i < resultado.length; i++) {
            resultado[i] = new String[] {"TERMO " + (i + 1), String.valueOf(this.metadados.values())};
        }
        return resultado;
    }
}
