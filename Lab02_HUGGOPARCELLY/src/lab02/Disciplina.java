package lab02;

import java.util.Arrays;

/**
 * Representação da situação de um aluno em uma disciplina.
 * É possível registras as 4 notas de um aluno.
 * Caso alguma das notas não seja cadastradas ela é considerada como zero.
 * Se alguma das notas for registradas mais de uma vez, é considerado o último registro.
 * É possível cadastras horas de estudos paraa determinada disciplina, que são cumulativas.
 * É feito o cálculo da média do aluno.
 * O aluno é considerado aprovado quando a média for igual ou superior a 7,0.
 *
 */
public class Disciplina {
    /**
     * Nome da disciplina. No formato de String.
     * Horas de estudo. No formato de inteiro.
     * Notas registradas. Serão guardadas dentro de um array de tamanho 4.
     * Status do aluno. No formato de booleano.
     * Média do aluno. No formato de double.
     * Soma das notas. No formato de double.
     */
    private String disciplina;
    private int horasEstudo;
    private double[] notas = new double[4];
    private boolean status;
    private double media;
    private double soma;

    /**
     * Constroí a disciplina com o seu nome.
     * @param nomeDisciplina o nome da disciplina no formato de string.
     */
    public Disciplina(String nomeDisciplina) {
        disciplina = nomeDisciplina;
    }

    /**
     * Método que atribui a quantidade de horas estudadas na disciplina.
     * A quantidade é atribuída à váriável horasEstudo.
     * @param horas o tempo de estudo da disciplina, em horas, registrado pelo usuário.
     */
    public void cadastraHoras(int horas) {
        horasEstudo = horas;
    }

    /**
     * Método que irá guardar as notas dentro de um array de tamanho 4.
     * As notas podem ser substituidas nas referidas posições.
     * @param nota refere-se a posição da nota (1, 2, 3 ou 4).
     * @param valorNota refere-se a pontuação da nota.
     */
    public void cadastraNota(int nota, double valorNota) {
        notas[nota - 1] = valorNota;
    }

    /**
     * Método que irá retornar a situação do aluno.
     * Através de uma interação é feito o acumulo das notas.
     * É calculado a média.
     * É feita uma comparação para retornar um status de aprovado ou não.
     * @return status, no formato booleano.
     */
    public boolean aprovado() {
        soma = 0;
        for( double nota: notas) {
            soma += nota;
        }
        media = soma / notas.length;

        if (media >= 7.0) {
            status = true;
        } else {
            status = false;
        }
        return status;
    }

    /**
     * Retorna uma String com o nome das disciplina, as horas de estudo, a média do aluno e as suas notas.
     * A representação é no formato "nome da disciplina" "horas estudo" "média" "[notas]".
     * @return a representação de uma String com a disciplina, horas, média e as notas.
     */
    public String toString() {
        return disciplina + " " +
                Integer.toString(horasEstudo) + " " +
                Double.toString(media) + " " +
                Arrays.toString(notas);
    }

}
