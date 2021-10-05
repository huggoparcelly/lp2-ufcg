package com.matheusgr.lunr.documento;

import java.util.HashMap;
import java.util.Map;


/**
 * Documento de texto simples. Não precisa de tratamento complexos nem tem
 * metadados próprios.
 */
class DocumentoTexto extends DocumentoAbstract {

	private final String original;
	private final String limpo;
	private Map<String, String> metadados;

	/**
	 * Construtor padrão do documento de texto.
	 * @param id ID do documento.
	 * @param txt Texto do documento.
	 */
	public DocumentoTexto(String id, String txt) {
		super(id, txt);
		this.original = txt;
		this.limpo = this.transformaDocumento();
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
		this.metadados = new HashMap<String, String>();
		this.metadados.put("LINHAS", "" + this.original.chars().filter((value) -> '\n' == value).count());
		this.metadados.put("TAMANHO", "" + this.limpo.length());
		this.metadados.put("METADATADATE", "" + System.currentTimeMillis());
		this.metadados.put("TIPO", "" + "txt");
		return this.metadados;
	}

}
