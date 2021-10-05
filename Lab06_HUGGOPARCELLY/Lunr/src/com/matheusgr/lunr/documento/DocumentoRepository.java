package com.matheusgr.lunr.documento;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Repositório de documentos. O repositório pode ter opreações simples de busca,
 * mas a lógica de ranking, limitação e ordenação deve ficar em outra entidade.
 * 
 * O ID de um documento é único.
 */
class DocumentoRepository {

	private final Map<String, Documento> documentos;
	private final ValidadorDocumentos validador;

	/**
	 * Construção padrão do repositório de documentos.
	 */
	DocumentoRepository() {
		this.documentos = new HashMap<>();
		this.validador = new ValidadorDocumentos();
	}

	/**
	 * Adiciona o documento. O documento é validado para garantir a consistência do
	 * documento (sem termos e id vazios).
	 * 
	 * @param d Documento a ser adicionado.
	 */
	void adiciona(Documento d) {
		this.validador.validacao(d.getId(), d.getTexto());
		this.documentos.put(d.getId(), d);
	}

	/**
	 * Recupera um documento do repositório.
	 * 
	 * @param id ID do documento.
	 * @return Documento, caso exista.
	 */
	Optional<Documento> recupera(String id) {
		this.validador.validacao(id);
		return Optional.ofNullable(this.documentos.get(id));
	}

	/**
	 * Retorna o total de documentos cadastrados.
	 * 
	 * @return O total de documentos cadastrados.
	 */
	int totalDocumentos() {
		return this.documentos.size();
	}

	/**
	 * Realiza uma busca pelos termos.
	 * 
	 * @param termo Termo a ser buscado.
	 * @return Conjunto de documentos com o termo.
	 */
	public Set<Documento> busca(String termo) {
		return this.documentos.values().stream()
					.filter((x) -> Arrays.binarySearch(x.getTexto(), termo) > 0)
					.collect(Collectors.toSet());
	}

	/**
	 * Realiza uma busca pelos metadados.
	 *
	 * @param metadados Metados a serem buscados
	 * @return Conjuntos de documentos que possuem os metadados.
	 */
	public Set<Documento> buscaAvancada(Map<String, String> metadados) {
//		 Feito com pesquisas na internet
//		 font:
//		 https://www.ti-enxame.com/pt/java/como-verificar-se-existe-um-valor-no-hashmap/1040533919/
		Set<Documento> documento = new HashSet<>();
		ArrayList<String> listaMetados = new ArrayList<>(metadados.values());
		for(Documento d: this.documentos.values()) {
			boolean metadadoCorreto = true;
			for (String metadado: listaMetados){
				if(d.getMetadados().containsValue(metadado)){
					continue;
				} else{
					metadadoCorreto = false;
				}
			}
			if(metadadoCorreto) {
				documento.add(d);
			}
		}

		return documento;
	}

}
