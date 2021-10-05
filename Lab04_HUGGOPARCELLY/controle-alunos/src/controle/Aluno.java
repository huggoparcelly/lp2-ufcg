package controle;

import java.util.Objects;

/**
 * Representação de um aluno no sistema
 * Cada aluno tem uma matrícula nome e curso
 * Cada aluno é identificado unicamente pela sua matrícula
 *
 * @author Huggo Parcelly - 120210155
 */
public class Aluno {

    /**
     * Matrícula do aluno
     */
    private final String matricula;

    /**
     * Nome do aluno
     */
    private final String nome;

    /**
     * Curso do aluno
     */
    private final String curso;

    /**
     * Contrutor para criar a representação do aluno, com matrícula, nome e curso
     *
     * @param matricula Matrícula do aluno
     * @param nome Nome do aluno
     * @param curso Curso do aluno
     */
    public Aluno(String matricula, String nome, String curso) {
        if (matricula == null || nome == null || curso == null) {
            throw new NullPointerException("Entrada nula!");
        } else if ( matricula.isBlank() ||  nome.isBlank() ||  curso.isBlank()) {
            throw new IllegalArgumentException("Entrada vazia!");
        }
        else {
            this.matricula = matricula;
            this.nome = nome;
            this.curso = curso;
        }
    }

    /**
     * Método que retorna uma string o a matrícula do aluno
     *
     * @return String com a mátricula
     */
    public String getMatricula() {return this.matricula;}

    /**
     * Método que retorna uma string com o nome do curso do aluno
     *
     * @return String com o nome do curso
     */
    public String getCurso() {
        return this.curso;
    }

    /**
     * Método que retorna uma string com o nome do aluno
     *
     * @return String com o nome do aluno
     */
    public String toString() {
        return this.nome;
    }

    /**
     * Método de Override que vai comparar dois objetos aluno.
     * Verifica se não é nulo.
     * Verifica se são da mesma classe.
     *
     * @param o Objeto para comparação
     * @return booleano se o objeto é igual ou não
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null) return false;
        if (getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return matricula.equals(aluno.matricula);
    }

    /**
     * Método de Override do hashCode do objeto.
     * Localização do objeto na memória.
     *
     * @return inteiro com a localização do objeto
     */
    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}
