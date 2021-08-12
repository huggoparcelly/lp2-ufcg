package controle;

import java.util.HashMap;

public class Controle {

    private Aluno alunos;

    private final HashMap<String, Aluno> mapaMatriculaAlunos = new HashMap<>();

    public boolean criaObjetoAluno(String matricula, String nome, String curso) {
        if (matricula == null || matricula.isBlank() || nome == null || nome.isBlank() || curso == null || curso.isBlank()) {
            throw new IllegalArgumentException("Entrada inválida!");
        } else if (!existeAluno(matricula)) {
            this.alunos = new Aluno(matricula, nome, curso);
            return adicionaAluno(matricula, alunos) == null;
        }
        return false;
    }

    public String getCurso() {
        return this.alunos.getCurso();
    }

    public Aluno adicionaAluno(String matricula, Aluno aluno) {
        return this.mapaMatriculaAlunos.put(matricula, aluno);
        // retorna o aluno anteriormente associado a essa matricula, ou nulo se não existia tal aluno
    }

    public boolean existeAluno(String matricula) {
        return this.mapaMatriculaAlunos.containsKey(matricula);
    }
//
//    public boolean existeAluno(Aluno aluno) {
//        return this.mapaMatriculaAlunos.containsValue(aluno);
//    }
//
    public Aluno recuperaAluno(String matricula) {
        return this.mapaMatriculaAlunos.get(matricula);
    }
//
//    public Aluno remove(String matricula) {
//        return this.mapaMatriculaAlunos.remove(matricula);
//    }
//
//    public int numeroDeAlunos() {
//        return this.mapaMatriculaAlunos.size();
//    }

    // setar a turma de todos os alunos
//    public void alterarTurma(String turma) {
//        for (Aluno aluno : this.mapaMatriculaAlunos.values()) {
//            aluno.setTurma(turma);
//        }
//    }

}
