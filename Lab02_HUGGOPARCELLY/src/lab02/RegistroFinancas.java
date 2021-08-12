package lab02;

/**
 *
 * Representação do registro financeiro de um aluno.
 * É possível registrar o dinheiro disponível, ganhos adicionais e seus gastos.
 * Os registros dos ganhos e desespesas são em centavos.
 * Um ganho pode ser corrigido, de acordo com a posição em que ele foi cadastrado.
 * As despesas não podem ser alteradas.
 * Ao fim será mostrado uma string com o resumo da sua conta.
 *
 */
public class RegistroFinancas {
    /**
     * Ganhos recebidos. No formato inteiro.
     * Soma dos ganhos recebidos. No formato inteiro.
     * Ganhos registrados. No formato de um array, que terá o tamanho do total de ganhos.
     * Soma das despesas. No formato inteiro.
     * Total de dinheiro disponível. No formato inteiro.
     *
     */
    private int ganhosRecebidos;
    private int totalRecebidos;
    private int[] ganhos;
    private int somaDespesas;
    private int disponivel;

    /**
     * Constroí o registro de finanças do aluno.
     * Atribui a variável ganhosRecibidos os ganhos iniciais registrados pelo aluno.
     * Cria o Array dos ganhos com o tamanho registrado pelo aluno no totalDeGanhos.
     * @param ganhosIniciais ganhos iniciais registrados.
     * @param totalDeGanhos quantidade de ganhos registrados.
     */

    public RegistroFinancas(int ganhosIniciais, int totalDeGanhos) {
        ganhosRecebidos = ganhosIniciais;
        ganhos = new int[totalDeGanhos];
    }

    /**
     * Método que atribui os valores recebidos em cada posição do array de ganhos.
     * @param valorCentavos valor dos ganhos em centavos.
     * @param posicaoGanho posição do ganho no array.
     */
    public void adicionaGanhos(int valorCentavos, int posicaoGanho) {
        ganhos[posicaoGanho - 1] = valorCentavos;
    }

    /**
     * Retorna o total de ganhos recebidos
     * Realiza uma interação dentro do Array de ganhos.
     * Acumula os ganhos recebidos na variável totalRecebidos.
     * @return o total acumulado de ganhos recebidos.
     */
    private int calculaGanhos() {
        totalRecebidos = 0;
        for(int ganho: ganhos) {
            totalRecebidos += ganho;
        }
       return totalRecebidos += ganhosRecebidos;
    }

    /**
     * Método que recebe como parâmetro o valor das despesas.
     * Calcula a soma das despesas.
     * Calcula o saldo disponível do aluno.
     * @param valorCentavos o valor das despesas em centavos.
     */
    public void pagaDespesa(int valorCentavos) {
        calculaGanhos();
        somaDespesas += valorCentavos;
        disponivel = totalRecebidos - somaDespesas;
    }

    /**
     * Retorna em cada linha uma String com o valor e posição de cada ganho.
     * A representação é no formato "posição - valor do ganho".
     * @return a representação dos ganhos com quebra de linhas.
     */
    public String exibeGanhos() {
        String extratoGanhos = "1 - " + ganhos[0];
        for (int i = 1; i < ganhos.length; i++) {
            extratoGanhos += "\n" + Integer.toString(i + 1) + " - " + Integer.toString(ganhos[i]);
        }
        return extratoGanhos;
    }

    /**
     *
     * Retorna uma String com o resumo da conta.
     * A representação é no formato "Total recebidos: valor" "Despesas totais: valor" "Total disponível: valor".
     * @return a representação do resumo da conta
     */
    public String toString() {
        return "Total recebidos: " + Integer.toString(totalRecebidos) +
                " Despesas totais: " + Integer.toString(somaDespesas) +
                " Total disponível: " + Integer.toString(disponivel);
    }

}
