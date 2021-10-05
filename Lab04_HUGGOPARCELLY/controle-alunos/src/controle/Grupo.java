package controle;

import java.util.HashSet;
import java.util.Objects;

/**
 * Repretação de um grupo no sistema
 * Cada grupo é identificado unicamente pelo nome do grupo
 * Os grupos são formados por um conjunto de alunos
 * Os grupos são associados aos alunos pela matrícula do aluno e nome do grupo
 * Os grupos apresentam restrição de tamanho, mas é opcional
 *
 * @author Huggo Parcelly - 120210155
 */
public class Grupo {

    /**
     * O nome do grupo
     */
    private final String nomeGrupo;

    /**
     * Método HashSet que armazena e manipula os alunos dentro do grupo
     * Os alunos ficam associados aos grupos por suas matrículas
     */
    private final HashSet<Aluno> alunos;

    /**
     * Indica o tamanho do grupo
     */
    private final String tamanho;

    /**
     * Contrutor para criar a representação do grupo, com nome e tamanho.
     *
     * @param nomeGrupo Nome do grupo
     * @param tamanho tamanho do grupo
     */
    public Grupo(String nomeGrupo, String tamanho) {
        if(nomeGrupo == null) {
            throw new NullPointerException("NOME DO GRUPO NULO!");
        }
        if(nomeGrupo.isBlank()) {
            throw new IllegalArgumentException("NOME DO GRUPO VAZIO!");
        }
        this.nomeGrupo = nomeGrupo;
        this.tamanho = tamanho;
        this.alunos = new HashSet<>();
    }


    /**
     * Método que adiciona os alunos ao conjunto de alunos
     *
     * @param aluno Objeto aluno
     * @return booleano quando o aluno é adicionado ao grupo
     */
    public boolean entraAluno(Aluno aluno) {
        if(tamanho.isBlank()) {
            this.alunos.add(aluno);
        } else if(Integer.parseInt(tamanho) > this.alunos.size()) {
            this.alunos.add(aluno);
        } else {
            return false;
        }
        return true;
    }

    /**
     *
     * @param aluno Objeto aluno
     * @return um booleano se o aluno pertece ou não ao grupo
     */
    public boolean pertenceAoGrupo(Aluno aluno) {
        return this.alunos.contains(aluno);
    }

    /**
     * Método que retorna uma string com o nome do grupo
     *
     * @return String com o nome do grupo
     */
    public String toString() {
        return this.nomeGrupo;
    }

    /**
     * Método de Override que vai comparar dois objetos grupo.
     * Verifica se não é nulo.
     * Verifica se são da mesma classe.
     *
     * @param o Objeto para comparação
     * @return booleano se o objeto é igual ou não
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grupo)) return false;
        Grupo grupo = (Grupo) o;
        return nomeGrupo.equals(grupo.nomeGrupo);
    }

    /**
     * Método de Override do hashCode do objeto.
     * Localização do objeto na memória.
     *
     * @return inteiro com a localização do objeto
     */
    @Override
    public int hashCode() {
        return Objects.hash(nomeGrupo);
    }
}
