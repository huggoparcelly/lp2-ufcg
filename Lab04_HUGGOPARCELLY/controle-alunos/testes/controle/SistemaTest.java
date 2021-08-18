package controle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SistemaTest {

    private final Sistema sistema = new Sistema();

    @BeforeEach
    void criaAlunoEGrupoBase() {
        this.sistema.cadastrarGrupo("Lista", "10");
        this.sistema.cadastrarAluno("250", "Gabriel Reyes", "Computação");
    }

    @Test
    void cadastraAlunoNovo() {
        boolean novoAluno = this.sistema.cadastrarAluno("251", "Matheus Gaudencio", "Computação");
        assertTrue(novoAluno);
    }

    @Test
    void cadastraAlunoMatriculaJaExistente() {
        boolean novoAluno = this.sistema.cadastrarAluno("250", "Gabriel Reyes", "Computação");
        assertFalse(novoAluno);
    }

    @Test
    void testeRecuperaAluno() {
        Aluno aluno = this.sistema.recuperaAluno("250");
        assertEquals("Gabriel Reyes", aluno.toString());
    }

    @Test
    void testeRecuperaAlunoInexistente() {
        Aluno aluno = this.sistema.recuperaAluno("251");
        assertNull(aluno);
    }

    @Test
    void testeCadastraGrupoComTamanho() {
        boolean novoGrupo = this.sistema.cadastrarGrupo("Exercicios","10");
        assertTrue(novoGrupo);
    }

    @Test
    void testeCadastraGrupoComTamanhoVazio() {
        boolean novoGrupo = this.sistema.cadastrarGrupo("Exercicios","");
        assertTrue(novoGrupo);
    }

    @Test
    void testeCadastraGrupoJaExistente() {
        boolean grupo = this.sistema.cadastrarGrupo("Lista", "10");
        assertFalse(grupo);
    }

    @Test
    void testeAdicionarAlunoNoGrupo() {
        String cadastro = this.sistema.adicionarAlunoNoGrupo("250", "Lista");
        assertEquals("ALUNO ALOCADO!", cadastro);
    }

    @Test
    void testeAdicionarAlunoNoGrupoNaoCadastrado() {
        String cadastro = this.sistema.adicionarAlunoNoGrupo("250", "Exercicios");
        assertEquals("GRUPO NÃO CADASTRADO", cadastro);
    }

    @Test
    void testeAdicionarAlunoNaoCadastradoNoGrupo() {
        String cadastro = this.sistema.adicionarAlunoNoGrupo("200", "Lista");
        assertEquals("ALUNO NÃO CADASTRADO", cadastro);
    }

    @Test
    void testeVerificaPertinenciaNoGrupo() {
        this.sistema.adicionarAlunoNoGrupo("250", "Lista");
        String pertence = this.sistema.verificarAlunoNoGrupo("250", "Lista");
        assertEquals("PERTENCE AO GRUPO!", pertence);
    }

    @Test
    void testeVerificaNaoPertinenciaNoGrupo() {
        String pertence = this.sistema.verificarAlunoNoGrupo("250", "Lista");
        assertEquals("ALUNO NÃO PERTENCE AO GRUPO!", pertence);
    }

    @Test
    void testeVerificaPertinenciaNoGrupoNaoCadastrado() {
        String pertence = this.sistema.verificarAlunoNoGrupo("250", "Exercicios");
        assertEquals("GRUPO NÃO CADASTRADO!", pertence);
    }

    @Test
    void testeRegistrarAlunoParticipante() {
        boolean registro = this.sistema.registrarAlunoParticipante("250");
        assertTrue(registro);
    }

    @Test
    void testeRegistrarParticipacaoAlunoNaoCadastrado() {
        try {
            this.sistema.registrarAlunoParticipante("251");
        } catch (IllegalArgumentException iae) {
            assertEquals("ALUNO NÃO CADASTRADO!", iae.getMessage());
        }
    }

    @Test
    void testeAlunosParticipantes() {
        this.sistema.registrarAlunoParticipante("250");
        String participantes = this.sistema.alunosParticipantes();
        assertEquals("1. 250 - Gabriel Reyes - Computação", participantes);
    }

    @Test
    void testeSemAlunosParticipantes() {
        String semRegistros = this.sistema.alunosParticipantes();
        assertEquals("SEM REGISTROS!", semRegistros);
    }

    @Test
    void testeGruposDoAluno() {
        this.sistema.adicionarAlunoNoGrupo("250", "Lista");
        this.sistema.cadastrarGrupo("Exercicios","");
        this.sistema.adicionarAlunoNoGrupo("250", "Exercicios");
        String grupos = this.sistema.gruposDoAluno("250");
        assertEquals("- Exercicios" + "\n" + "- Lista" + "\n", grupos);
    }

    @Test
    void testeGruposDoAlunoSemGrupo() {
        String grupos = this.sistema.gruposDoAluno("251");
        assertEquals("", grupos);
    }

}