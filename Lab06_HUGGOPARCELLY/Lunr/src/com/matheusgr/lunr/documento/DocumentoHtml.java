package com.matheusgr.lunr.documento;

import java.util.HashMap;
import java.util.Map;

/**
 * DocumentoHTML representa e extrai dados de um HTML.
 * 
 * Os termos extraídos são obtidos dos textos dos nós do HTML. São ignorados
 * nome de tags ou de propriedades. Por não ser um texto bem formulado, limpa-se
 * da melhor forma possível o HTML.
 * 
 * Os metadados são obtidos de características do documento, mas de detalhes
 * descritos na tag HEAD.
 */
class DocumentoHtml extends DocumentoAbstract {

	private static final String HEAD_METADADO = "HEAD";

	private final String id;
	private final String original;
	private final String limpo;
	private Map<String, String> metadados;

	/**
	 * Construtor padrão. Realiza o processamento de extração do HTML.
	 * 
	 * @param id ID do documento a ser criado.
	 * @param original HTML do documento a ser criado.
	 */
	public DocumentoHtml(String id, String original) {
		super(id, original);
		this.id = id;
		this.original = original;
		this.limpo = this.transformaDocumento();
	}

	@Override
	public String toString() {
		return "===" + this.id + System.lineSeparator() + this.getMetadados().get(HEAD_METADADO)
				+ System.lineSeparator() + "===" + this.limpo;
	}

	/**
	 * Retorna metadados referente ao documento. Detalhes como seu tipo, autor, ou
	 * características próprias do documento.
	 *
	 * Idealmente, todo metadado terá como características:
	 *
	 * LINHAS, TAMANHO (número de caracteres), METADATADATE (hora atual do sistema
	 * na criação dos metadados, em ms), TIPO.
	 *
	 * @return Mapa com os metadados descrito em forma textual.
	 */
	@Override
	public Map<String, String> getMetadados() {
		if (this.metadados != null) {
			return this.metadados;
		}
		this.metadados = extractHead(this.original);
		this.metadados.put("LINHAS", "" + this.original.chars().filter((value) -> '\n' == value).count());
		this.metadados.put("TAMANHO", "" + this.limpo.length());
		this.metadados.put("METADATADATE", "" + System.currentTimeMillis());
		this.metadados.put("TIPO", "" + "html");
		return this.metadados;
	}

	/*
	 * Os metadados do HTML incluem, especificamente:
	 * - Quantidade de tags, estimada a partir da quantidade de símbolos <
	 * - Todo o corpo da tag <head>
	 */
	private Map<String, String> extractHead(String original2) {
		Map<String, String> metadados2 = new HashMap<>();
		metadados2.put("BRUTE_TAGS", "" + this.original.chars().filter((value) -> '<' == value).count());
		String meta = "";
		int headStart = original2.toLowerCase().indexOf("<head>");
		if (headStart != -1) {
			int headEnd = original2.toLowerCase().indexOf("</head>");
			if (headEnd != -1) {
				meta = original2.substring(headStart, headEnd);
			}
		}
		metadados2.put(HEAD_METADADO, meta);
		return metadados2;
	}
}
