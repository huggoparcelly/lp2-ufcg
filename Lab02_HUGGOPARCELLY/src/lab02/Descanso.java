package lab02;
/**
 * Representação da rotina de descanso de um aluno.
 * O aluno deve descansar 26 horas ou mais para se considerar descansado, sem incluir horas de sono.
 * O mesmo é considerado cansado caso não sejam registrados horas de desanso ou números de semanas.
 *
 * @author Huggo Parcelly
 *
 */
public class Descanso {
    /**
     * Horas de descanso. No formato de número inteiro.
     * Numero de semanas. No formato de número inteiro.
     *
     */
    private int horasDescanso;
    private int semanas;

    /**
     * Método que atribui a quantidade de semanas registradas à variável semanas.
     * @param valor o valor do numero de semanas registrado.
     */
    public void defineNumeroSemanas(int valor) {
        semanas = valor;
    }

    /**
     * Método que atribui a quantidade de horas de descanso registradas à variável horasDescanso.
     * @param valor o valor das horas descansadas.
     */
    public void defineHorasDescanso(int valor) {
        horasDescanso = valor;
    }

    /**
     * Método que verifica o status do aluno.
     * Através de uma comparação vai verificar se o aluno está cansado ou descansado.
     * Inicia a variável status como cansado.
     * @return a representação do status do aluno, após a comparação.
     */
    public String getStatusGeral() {
        String status = "cansado";

        if (horasDescanso == 0 || semanas == 0) {
            status = "cansado";
        } else if ((horasDescanso / semanas) >= 26) {
            status = "descansado";
        }
        return status;
    }
}
