package lab02;

/**
 * Representa a quantidade de horas de internet que um aluno tem dedicado a uma disciplina remota.
 * Para uma disciplina de X horas, o aluno deve dedicar-se o dobro de tempo online
 * É possível registrar a quantidade de horas de uma disciplina e a quantidade de horas gastas pelo aluno.
 * Ao atingir o tempo esperado, será indicado que o aluno atingiu a quantidade de tempo para a disciplina.
 * Contudo, mesmo com o tempo esperado atingido, é possível registrar mais horas dedicadas na disciplina.
 *
 * @author Huggo Parcelly
 *
 */
public class RegistroTempoOnline {
    /**
     * Nome da disciplina. No formato de string.
     * Tempo online esperado para a disciplina. Em horas e no formato de inteiro.
     * Tempo online do aluno. Em horas e formato inteiro.
     * Identificador se atingiu ou não o tempo esperado. No formato booleano.
     */
    private String disciplina;
    private int tempoEsperado;
    private int tempoOnline;
    private boolean atingiu = false;

    /**
     * Constroí um registro do tempo online com o nome da disciplina.
     * Terá um tempo esperado de 120h, por padrão.
     * @param nomeDisciplina o nome da disciplina no formato de string.
     */
    public RegistroTempoOnline(String nomeDisciplina) {
        disciplina = nomeDisciplina;
        tempoEsperado = 120;
    }

    /**
     * Constroí um registro do tempo online com o nome da disciplina e com um tempo esperado.
     * @param nomeDisciplina o nome da disciplina no formato de string.
     * @param tempoOnlineEsperado o tempo online esperado para a disciplina, em horas, no formato de inteiro.
     */
    public RegistroTempoOnline(String nomeDisciplina, int tempoOnlineEsperado) {
        disciplina = nomeDisciplina;
        tempoEsperado = tempoOnlineEsperado;
    }

    /**
     * Método que acumula o tempo online registrado, na variável tempoOnline.
     * @param tempo o valor do tempo em horas registrado pelo aluno.
     */
    public void adicionaTempoOnline(int tempo) {
        tempoOnline += tempo;
    }

    /**
     * Método que verifica se o aluno atingiu ou não o tempo esperado para a disciplina registrada.
     * @return o identificador, no formato booleano.
     */
    public boolean atingiuMetaTempoOnline() {
        if (tempoOnline >= tempoEsperado) {
            atingiu = true;
        }
        return atingiu;
    }

    /**
     * Retorna uma String com o nome da disciplina, o tempo online já usado e o tempo esperado.
     * A representação é no formato "nome da disciplina" "o tempo online já usado" / "tempo online esperado".
     * @return a representação de uma String do status de tempo dedicado a disciplina
     */
    public String toString() {
        return disciplina + " " +
                Integer.toString(tempoOnline) +
                "/" +
                Integer.toString(tempoEsperado);
    }
}

