package controle;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe responsável por gerenciar os alunos cadastrados, os grupos e as operações sobre eles
 * Controla o cadastro de alunos, exibição de alunos, criação de grupos,
 * alocação de alunos no grupo, verifica a pertinencia do aluno em grupos,
 * registra do aluno que respondeu, exibe o aluno que respondeu e mostra os grupos de um aluno
 *
 * @author Huggo Parcelly - 120210155
 */
public class Sistema {

    /**
     * Método HasMap que irá armazenar e manipular os alunos cadastrados no sistema
     * Faz a utilização de chave-valor para associar alunos e grupos.
     */
    private final HashMap<String, Aluno> alunos;

    /**
     * Método HasMap que irá armazenar e manipular os grupos cadastrados no sistema
     * Faz a utilização de chave-valor para associar alunos e grupos.
     */
    private final HashMap<String, Grupo> grupos;

    /**
     * Método ArrayList utilizado para armazenar e manipular os alunos que responderam
     * pela ordem de participação, e com possibilidade de participar mais de uma vez
     */
    private final ArrayList<Aluno> registros;

    /**
     * Construtor do sistema
     * Irá inicializar os métodos HasMap e o Arraylist.
     *
     */
    public Sistema() {
         this.alunos = new HashMap<>();
         this.grupos = new HashMap<>();
         this.registros = new ArrayList<>();
    }

    /**
     * Método que cadastra uma aluno pela sua matricula, nome e curso
     *
     * @param matricula Matrícula do aluno
     * @param nome Nome do aluno
     * @param curso Curso do aluno
     * @return booleano quando o cadastro é realizado
     */
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

    /**
     * Método que exibe o aluno, fazendo a busca pela key matrícula
     *
     * @param matricula Matrícula do aluno buscado
     * @return String com o aluno ou null caso o aluno não seja encontrado
     */
    public Aluno recuperaAluno(String matricula) {
        return this.alunos.get(matricula);
    }

    /**
     * Método que vai exibir o curso do aluno
     * Utilizando como key a matrícula
     *
     * @param matricula Matrícula do aluno
     * @return String com o nome do curso
     */
    public String getCurso(String matricula) {
        Aluno aluno = this.alunos.get(matricula);
        return aluno.getCurso();
    }

    /**
     * Método que realiza do cadastro dos grupos
     * O tamanho do grupo é opicional.
     *
     * @param nomeGrupo Nome do grupo cadastrado
     * @param tamanho Tamanho do grupo (opicional)
     * @return booleano quando o cadastro é realizado
     */
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

    /**
     * Método que irá adicionar um aluno a um grupo
     * Associando a matrícula ao nome do grupo
     *
     * @param matricula Matrícula do aluno
     * @param nomeGrupo Nome do grupo
     * @return String "ALUNO ALOCADO" caso de sucesso,
     * "ALUNO NÃO CADASTRADO" quando não existe o aluno
     * "GRUPO NÃO CADASTRADO" quando não existe o grupo
     * "GRUPO CHEIO!" quando o grupo tem um tamanho e já está cheio.
     */
    public String adicionarAlunoNoGrupo(String matricula, String nomeGrupo) {
        if(naoExisteAluno(matricula)) {
             return  "ALUNO NÃO CADASTRADO";
        } else if(naoExisteGrupo(nomeGrupo)) {
            return "GRUPO NÃO CADASTRADO";
        }
        else {
            Grupo grupo = this.grupos.get(nomeGrupo);
            Aluno aluno = this.alunos.get(matricula);
            if(grupo.pertenceAoGrupo(aluno) || grupo.entraAluno(aluno)) {
                return "ALUNO ALOCADO!";
            }
           return "GRUPO CHEIO!";
        }
    }

    /**
     * Método que vai verificar a pertinencia do aluno em um grupo
     * Associando a matricula e o nome do grupo na busca
     *
     * @param matricula Matrícula do aluno
     * @param nomeGrupo Nome do grupo
     * @return "PERTENCE AO GRUPO!" caso de sucesso,
     * "ALUNO NÃO PERTENCE AO GRUPO!", caso o aluno não pertença ao grupo
     * "ALUNO NÃO CADASTRADO" quando não existe o aluno
     * "GRUPO NÃO CADASTRADO" quando não existe o grupo
     *
     */
    public String verificarAlunoNoGrupo(String matricula, String nomeGrupo ) {
        if(naoExisteAluno(matricula)) {
            return  "ALUNO NÃO CADASTRADO";
        }
        if(naoExisteGrupo(nomeGrupo)) {
            return "GRUPO NÃO CADASTRADO!";
        } else {
            Grupo grupo = this.grupos.get(nomeGrupo);
            Aluno aluno = this.alunos.get(matricula);
            if(grupo.pertenceAoGrupo(aluno)){
                return "PERTENCE AO GRUPO!";
            } else {
                return "ALUNO NÃO PERTENCE AO GRUPO!";
            }
        }
    }

    /**
     * Método que registra o aluno que participou
     * Utilizando a matrícula como key no registro
     *
     * @param matricula Matrícula do aluno
     * @return Um erro caso o aluno não exista
     * um booleano true caso o registro seja feito com sucesso.
     */
    public boolean registrarAlunoParticipante(String matricula) {
        if(naoExisteAluno(matricula)) {
            throw new IllegalArgumentException("ALUNO NÃO CADASTRADO!");
        } else {
            Aluno aluno = this.alunos.get(matricula);
            this.registros.add(aluno);
            return true;
        }
    }

    /**
     * Método que exibe os alunos participantes, sua matricula e seu curso
     * No formato posição de participação.  matricula - aluno - curso
     *
     * @return String com os participantes ou "SEM REGISTROS" caso não exista nenhum aluno participante
     */
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

    /**
     * Método que que exibe os grupos em que o aluno está cadastrado
     * Utilizando a matrícula para buscar os grupos associados.
     *
     * @param matricula Matrícula do aluno
     * @return String vazia quando não tem grupos cadastrados ou nomes dos grupos
     * no formato - Nome do grupo
     */
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
