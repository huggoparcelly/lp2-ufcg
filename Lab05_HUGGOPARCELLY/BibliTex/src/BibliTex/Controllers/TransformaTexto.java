package BibliTex.Controllers;

import BibliTex.Intefaces.Logger;
import BibliTex.Intefaces.Transformacao;
import BibliTex.Transforma.*;

import java.util.*;

/**
 * Classe responsável por gerenciar as transformações de texto e os loggers
 * Realizando o cadastrado de novas transformações,
 * realizando as transformações, contando quantas transformações ocorreram,
 * mostrando o histórico de transformação, através da posição,
 * listando os textos originais e os tipos de transformações
 *
 * @author Huggo Parcelly - 120210155
 */
public class TransformaTexto {

    /**
     * Método ArrayList utilizado para armazenar e manipular os textos originais
     * nõa armazena textos repetidos e é sensitive case.
     */
    private final ArrayList<String> textosOriginais;
    /**
     * Método ArrayList utilizado para armazenar e manipular os textos transformados
     */
    private final ArrayList<String> textosTransformados;
    /**
     * Método ArrayList utilizado para armazenar e manipular os tipos de transformações
     */
    private final ArrayList<String> historicos;
    /**
     * Método Map utilizado para armazenar e manipular as transformações já existentes no sistema
     * Além das novas cadastradas.
     */
    private final Map<String, Transformacao> transformacoes;
    /**
     * Interface logger utiliza um tipo genérico para exibir os registros
     * no formado indicado pelo tipo de logger.
     */
    private final Logger loggers;
    /**
     * Variável do tipo inteiro para realizar a contagem das transformações.
     */
    private int contador;

    /**
     * Construtor do sitema
     * Irá inicializar os Maps, Listas, Logger e contador
     * Além de adicionar as transformações do sistema no map.
     * @param tipoLogger Tipo de logger passado para registro das operações
     */
    public TransformaTexto(Logger tipoLogger) {
        this.transformacoes = new TreeMap<>();
        this.textosTransformados = new ArrayList<>();
        this.textosOriginais = new ArrayList<>();
        this.historicos = new ArrayList<>();
        this.loggers = tipoLogger;
        this.contador = 0;
        this.transformacoes.put("UpperCase", new UpperCase());
        this.transformacoes.put("LowerCase", new LowerCase());
        this.transformacoes.put("CaMeLcAsEfY", new CaMeLcAsEfY());
        this.transformacoes.put("Clean", new Clean());
        this.transformacoes.put("CleanSpaces", new CleanSpaces());
        this.transformacoes.put("InterrogaParaPontos", new InterrogaParaPontos());

    }

    /**
     * Método para realizar o cadastro de novas transformações, utilizando um nome específico
     * e o nome da transformação.
     *
     * @param nomeTransformacao Nome atribuido à transformação, não necessáriamente o mesmo nome do sistema.
     * @param tipoTransformacao Tipo de transformação utilizada, que poderá ser uma nova implementada
     * @return booleano true para o cadastro com sucesso.
     */
    public boolean cadastraTransformacao(String nomeTransformacao, Transformacao tipoTransformacao){
        this.transformacoes.put(nomeTransformacao, tipoTransformacao);
        return true;
    }

    /**
     * Método que realiza a verificação da existencia do texto, dentro da lista de textos Originais
     * Caso o texto já exista, ele não é adicionado novamente na lista.
     *
     * @param textoOriginal Texto original passado para transformar.
     */
    private void verificaOriginais(String textoOriginal) {
       if(!textosOriginais.contains(textoOriginal)) {
           this.textosOriginais.add(textoOriginal);
       }
    }

    /**
     * Método que realiza a transformação do texto original,
     * Verifica a existencia do texto original e adiciona o texto na lista dos originais
     * Adiciona o texto transformado na lista dos transformados,
     * Adiciona o tipo de transformação à lista de histórico e
     * realiza a contagem das transformações.
     *
     * @param tipoTransformacao Tipo de transformação que deseja realizar.
     * @param textoOriginal Texto original para ser transformado.
     * @return String no formato do logger passado + o texto transformado.
     */
    public String transforma(String tipoTransformacao, String textoOriginal){

        String textoTransformado = this.transformacoes.get(tipoTransformacao).transformar(textoOriginal);
        verificaOriginais(textoOriginal);
        this.textosTransformados.add(textoTransformado);
        this.historicos.add(tipoTransformacao);
        contador += 1;
        return this.loggers.getLogger("transforma", tipoTransformacao) + textoTransformado;
    }

    /**
     * Método que retorna a contagens das transformações
     * @return String no formato do logger passado + a contagem das transformações.
     */
    public String contaTransformacao() {
        return this.loggers.getLogger("contaTransformacao", "") + contador;
    }

    /**
     * Método que retorna o histórico de transformações, através de uma posição passada
     *
     * @param posicao Posição da transformação na lista de historico.
     * @return String no formato do logger passado + o historico de transformações no formato
     * texto Original -> tipo de transformação -> texto transformado
     */
    public String historico(int posicao) {
        String listaTransformacoes = "";
        listaTransformacoes += textosOriginais.get(posicao) + " -> " + historicos.get(posicao)
                + " -> " + textosTransformados.get(posicao);
        return this.loggers.getLogger("historico", Integer.toString(posicao)) + listaTransformacoes;
    }

    /**
     * Método que retorna a lista dos textos originais
     *
     * @return String no formato do logger passado + a lista de textos originais
     */
    public String listarOriginais() {
        String listaOriginais = "";
        for (String textosOriginais : textosOriginais) {
            listaOriginais += textosOriginais + System.lineSeparator();
        }

        return this.loggers.getLogger("listarOriginais", "") + listaOriginais;
    }

    /**
     * Método que retorna a lista das transformações existentes no sistema em ordem alfabética.
     *
     * @return String no formato do logger passado + a lista de tipos de transformações existentes no sistema
     * em ordem alfabética
     */
    public String listarTransformacoes() {
        String listaTransformacoes = "";
        for(Transformacao t: this.transformacoes.values()) {
            listaTransformacoes += t.getNome() + System.lineSeparator();
        }
        return this.loggers.getLogger("listarTransformacoes", "") + listaTransformacoes;
    }
}


