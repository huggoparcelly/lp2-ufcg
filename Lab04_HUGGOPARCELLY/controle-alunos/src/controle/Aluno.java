package controle;

import java.util.Objects;

public class Aluno {

    private final String matricula;
    private final String nome;
    private final String curso;

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

    public String getMatricula() {return this.matricula;}

    public String getCurso() {
        return this.curso;
    }

    public String toString() {
        return this.nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null) return false;
        if (getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return matricula.equals(aluno.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}
