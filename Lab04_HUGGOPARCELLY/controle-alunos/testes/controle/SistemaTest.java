package controle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SistemaTest {

    private final Sistema sistema = new Sistema();

    @BeforeEach
    void criaAlunoEGrupoBase() {
        this.sistema.cadastrarAluno("250", "Gabriel Reyes", "Computação");
        this.sistema.cadastrarAluno("200","Lili Camposh", "Computação");
        this.sistema.cadastrarAluno("202","Angela Ziegler", "Medicina");
        this.sistema.cadastrarAluno("201","Torbjorn Lindholm", "Torbjorn Lindholm");
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
    void testeCadastraGrupoComTamanhoVazio() {
        boolean novoGrupo = this.sistema.cadastrarGrupo("Programação OO","");
        assertTrue(novoGrupo);
    }

    @Test
    void testeCadastraGrupoComTamanho() {
        boolean novoGrupo = this.sistema.cadastrarGrupo("Listas","10");
        assertTrue(novoGrupo);
    }

    @Test
    void testeCadastraGrupoJaExistente() {
        this.sistema.cadastrarGrupo("Listas", "10");
        boolean grupo = this.sistema.cadastrarGrupo("Listas", "");
        assertFalse(grupo);
    }

    @Test
    void testeAdicionarAlunoNoGrupo() {
        this.sistema.cadastrarGrupo("Programação OO","");
        String cadastroAluno1 = this.sistema.adicionarAlunoNoGrupo("200", "Programação OO");
        String cadastroAluno2 = this.sistema.adicionarAlunoNoGrupo("202", "Programação OO");
        assertEquals("ALUNO ALOCADO!", cadastroAluno1);
        assertEquals("ALUNO ALOCADO!", cadastroAluno2);
    }

    @Test
    void testeAdicionarAlunoExistenteNoGrupo() {
        this.sistema.cadastrarGrupo("Programação OO","");
        String cadastroAluno1 = this.sistema.adicionarAlunoNoGrupo("200", "Programação OO");
        String cadastroAluno2 = this.sistema.adicionarAlunoNoGrupo("202", "Programação OO");
        String cadastroAluno3 = this.sistema.adicionarAlunoNoGrupo("200", "Programação OO");
        assertEquals("ALUNO ALOCADO!", cadastroAluno1);
        assertEquals("ALUNO ALOCADO!", cadastroAluno2);
        assertEquals("ALUNO ALOCADO!", cadastroAluno3);
    }

    @Test
    void testeAdicionarAlunoNaoCadastradoNoGrupo() {
        this.sistema.cadastrarGrupo("Programação OO","");
        String cadastro = this.sistema.adicionarAlunoNoGrupo("100", "Programação OO");
        assertEquals("ALUNO NÃO CADASTRADO", cadastro);
    }

    @Test
    void testeAdicionarAlunoNoGrupoNaoCadastrado() {
        String cadastro = this.sistema.adicionarAlunoNoGrupo("200", "Anatomia");
        assertEquals("GRUPO NÃO CADASTRADO", cadastro);
    }

    @Test
    void entraAlunoNoGrupoBaseTamanho1() {
        this.sistema.cadastrarGrupo("Listas","1");
        this.sistema.adicionarAlunoNoGrupo("250", "Listas");
        String cadastro = this.sistema.adicionarAlunoNoGrupo("202", "Listas");
        assertEquals("GRUPO CHEIO!", cadastro);

    }

    @Test
    void testeVerificaPertinenciaNoGrupo() {
        this.sistema.cadastrarGrupo("Listas","");
        this.sistema.adicionarAlunoNoGrupo("250", "Listas");
        String pertenceAluno1 = this.sistema.verificarAlunoNoGrupo("250", "Listas");
        assertEquals("PERTENCE AO GRUPO!", pertenceAluno1);
        String pertenceAluno2 = this.sistema.verificarAlunoNoGrupo("202", "Listas");
        assertEquals("ALUNO NÃO PERTENCE AO GRUPO!", pertenceAluno2);
    }

    @Test
    void testeVerificaPertinenciaNoGrupoNaoCadastrado() {
        String pertence = this.sistema.verificarAlunoNoGrupo("200", "Anatomia");
        assertEquals("GRUPO NÃO CADASTRADO!", pertence);
    }

    @Test
    void testeVerificaPertinenciaAlunoNaoCadastrado() {
        this.sistema.cadastrarGrupo("Programação OO","");
        String cadastro = this.sistema.verificarAlunoNoGrupo("100", "Programação OO");
        assertEquals("ALUNO NÃO CADASTRADO", cadastro);
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
    void testeGruposDoAlunoSemGrupo() {
        String grupos = this.sistema.gruposDoAluno("251");
        assertEquals("", grupos);
    }

    @Test
    void testeGruposDeAlunos() {
        this.sistema.cadastrarGrupo("Programação OO","");
        this.sistema.cadastrarGrupo("Listas","");
        this.sistema.adicionarAlunoNoGrupo("250", "Listas");
        this.sistema.adicionarAlunoNoGrupo("250", "Programação OO");
        String grupos = this.sistema.gruposDoAluno("250");
        assertEquals("- Programação OO" + "\n" + "- Listas" + "\n", grupos);
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





}