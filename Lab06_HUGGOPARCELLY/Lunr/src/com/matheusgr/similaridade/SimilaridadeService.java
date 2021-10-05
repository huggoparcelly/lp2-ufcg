package com.matheusgr.similaridade;

import com.matheusgr.lunr.documento.DocumentoService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Componente para tratamento da lógica de negócio relativa a similaridade.
 */
public class SimilaridadeService {

	private final Map<String, String[]> documentos;
	double valorSimilaridade;
	private final DocumentoService documentoService;

	/**
	 * Inicialização da lógica de serviço.
	 * 
	 * @param documentoService DocumentoService a ser utilizado pelo
	 *                         SimilaridadeService.
	 */
	public SimilaridadeService(DocumentoService documentoService) {
		this.documentoService = documentoService;
		this.documentos = new HashMap<>();
	}

	/**
	 * Calcula e retorna a similaridade.
	 * 
	 * Para o cálculo da similaridade:
	 * <ul>
	 * <li>Pega o documento 1</li>
	 * <li>Pega o documento 2</li>
	 * <li>Pega os termos do documento 1 e coloca em um conjunto (Termos1)</li>
	 * <li>Pega os termos do documento 2 e coloca em um conjunto (Termos2)</li>
	 * <li>Calcula a interseção entre Termos1 e Termos2 (Inters)</li>
	 * <li>Calcula a união entre Termos1 e Termos2 (Uniao)</li>
	 * <li>A similaridade é o tamanho de Inters sobre o tamanho do conjunto
	 * Uniao</li>
	 * </ul>
	 * 
	 * @param docId1 Documento 1.
	 * @param docId2 Documento 2.
	 * @return Valor de similaridade (entre 0 e 1, inclusives) representando a
	 *         semelhança entre os documentos.
	 */
	public double similaridade(String docId1, String docId2) {
		double tamanhoIntersecao = 0;
		String[] documento1 = this.documentoService.recuperaDocumento(docId1).get().getTexto();
		String[] documento2 = this.documentoService.recuperaDocumento(docId2).get().getTexto();

		this.documentos.put(docId1, documento1);
		this.documentos.put(docId2, documento2);
		for(String doc1: this.documentos.get(docId1)) {
			for(String doc2: this.documentos.get(docId2)) {
				if(Objects.equals(doc1, doc2)) {
					tamanhoIntersecao += 1;
				}
			}
		}
		double tamanhoUniao = documento1.length + documento2.length;
		valorSimilaridade = tamanhoIntersecao / tamanhoUniao;
		BigDecimal big = new BigDecimal(valorSimilaridade)
				.setScale(2, RoundingMode.HALF_EVEN);
		return big.doubleValue();
	}

}
