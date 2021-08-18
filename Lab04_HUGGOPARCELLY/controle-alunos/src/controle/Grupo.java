package controle;

import java.util.HashSet;
import java.util.Objects;

public class Grupo {
    private final String nomeGrupo;
    private final HashSet<Aluno> alunos;
    private final String tamanho;

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

    public boolean pertenceAoGrupo(Aluno aluno) {
        return this.alunos.contains(aluno);
    }

    public String toString() {
        return this.nomeGrupo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grupo)) return false;
        Grupo grupo = (Grupo) o;
        return nomeGrupo.equals(grupo.nomeGrupo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeGrupo);
    }
}
