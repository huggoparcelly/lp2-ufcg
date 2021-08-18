package controle;

import java.util.ArrayList;
import java.util.HashMap;

public class Sistema {

    private final HashMap<String, Aluno> alunos;
    private final HashMap<String, Grupo> grupos;
    private final ArrayList<Aluno> registros;

    public Sistema() {
         this.alunos = new HashMap<>();
         this.grupos = new HashMap<>();
        this.registros = new ArrayList<>();
    }

    public boolean cadastrarAluno(String matricula, String nome, String curso) {
        if(naoExisteAluno(matricula)) {
            Aluno novoAluno = new Aluno(matricula, nome, curso);
            this.alunos.put(matricula, novoAluno);
            return true;
        }
        return false;
    }

    private boolean naoExisteAluno(String matricula) {
        return !this.alunos.containsKey(matricula);
    }

    public Aluno recuperaAluno(String matricula) {
        return this.alunos.get(matricula);
    }

    public String getCurso(String matricula) {
        Aluno aluno = this.alunos.get(matricula);
        return aluno.getCurso();
    }

    public boolean cadastrarGrupo(String nomeGrupo, String tamanho) {
        if(naoExisteGrupo(nomeGrupo)) {
            Grupo novoGrupo = new Grupo(nomeGrupo, tamanho);
            this.grupos.put(nomeGrupo, novoGrupo);
            return true;
        }
        return false;
    }

    private boolean naoExisteGrupo(String nomeGrupo) {
        return !this.grupos.containsKey(nomeGrupo);
    }

    public String adicionarAlunoNoGrupo(String matricula, String nomeGrupo) {
        if(naoExisteAluno(matricula)) {
             return  "ALUNO NÃO CADASTRADO";
        } else if(naoExisteGrupo(nomeGrupo)) {
            return "GRUPO NÃO CADASTRADO";
        }
        else {
            Grupo grupo = this.grupos.get(nomeGrupo);
            Aluno aluno = this.alunos.get(matricula);
            if(grupo.entraAluno(aluno)) {
                return "ALUNO ALOCADO!";
            }
           return "GRUPO CHEIO!";
        }
    }

    public String verificarAlunoNoGrupo(String matricula, String nomeGrupo ) {
        Grupo grupo = this.grupos.get(nomeGrupo);
        if(naoExisteGrupo(nomeGrupo)) {
            return "GRUPO NÃO CADASTRADO!";
        } else {
            Aluno aluno = this.alunos.get(matricula);
            if(grupo.pertenceAoGrupo(aluno)){
                return "PERTENCE AO GRUPO!";
            } else {
                return "ALUNO NÃO PERTENCE AO GRUPO!";
            }
        }
    }

    public boolean registrarAlunoParticipante(String matricula) {
        if(naoExisteAluno(matricula)) {
            throw new IllegalArgumentException("ALUNO NÃO CADASTRADO!");
        } else {
            Aluno aluno = this.alunos.get(matricula);
            this.registros.add(aluno);
            return true;
        }
    }

    public String alunosParticipantes() {
        String participantes = "";
        if(registros.size() != 0) {
            for(int i = 0; i < registros.size(); i++) {
                Aluno alunoRegistrado = registros.get(i);
                String matricula = alunoRegistrado.getMatricula();
                String curso = alunoRegistrado.getCurso();
                participantes += (i + 1) + ". " + matricula + " - " + alunoRegistrado + " - " + curso;
            }
        } else {
            participantes += "SEM REGISTROS!";
        }
        return participantes;
    }


    public String gruposDoAluno(String matricula) {
        Aluno aluno = this.alunos.get(matricula);
        String resposta = "";
        for (Grupo grupo : this.grupos.values()) {
            if(grupo.pertenceAoGrupo(aluno)) {
                resposta += "- " + grupo + System.lineSeparator();
            }
        }
        return resposta;
    }

}
