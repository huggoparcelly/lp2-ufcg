package controle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControleTest {

    private final Controle controle = new Controle();
    private Aluno alunoBase;

    @BeforeEach
    void criaAlunoBase() {
        this.alunoBase = new Aluno("250", "Gabriel Reyes", "Computação");
    }

    @Test
    void cadastraAlunoCasoBase() {
        boolean sucesso = this.controle.criaObjetoAluno("250", "Gabriel Reyes", "Computação");
        assertTrue(sucesso);
    }

    @Test
    void cadastraAlunoMatriculaJaExistente() {
        this.controle.criaObjetoAluno("250", "Gabriel Reyes", "Computação");
        boolean sucesso = this.controle.criaObjetoAluno("250", "Gabriel Reyes", "Computação");
        assertFalse(sucesso);
    }

    @Test
    void cadastraAlunoMatriculaVazia() {
        try {
            new Aluno("", "Gabriel Reyes", "Computação");
            fail("Espera-se um erro");
        } catch (IllegalArgumentException iea) {
            assertEquals("Entrada inválida!", iea.getMessage());
        }
    }

    @Test
    void cadastraAlunoMatriculaNula() {
        try {
            new Aluno(null, "Gabriel Reyes", "Computação");
            fail("Espera-se um erro");
        } catch (IllegalArgumentException iea) {
            assertEquals("Entrada inválida!", iea.getMessage());
        }
    }

    @Test
    void cadastraAlunoNomeVazio() {
        try {
            new Aluno("250", "", "Computação");
            fail("Espera-se um erro");
        } catch (IllegalArgumentException iea) {
            assertEquals("Entrada inválida!", iea.getMessage());
        }
    }

    @Test
    void cadastraAlunoNomeNulo() {
        try {
            new Aluno("250", null, "Computação");
            fail("Espera-se um erro");
        } catch (IllegalArgumentException iea) {
            assertEquals("Entrada inválida!", iea.getMessage());
        }
    }

    @Test
    void cadastraAlunoCursoVazio() {
        try {
            new Aluno("250", "Gabriel Reyes", "");
            fail("Espera-se um erro");
        } catch (IllegalArgumentException iea) {
            assertEquals("Entrada inválida!", iea.getMessage());
        }
    }

    @Test
    void cadastraAlunoCursoNulo() {
        try {
            new Aluno("250", "Gabriel Reyes", null);
            fail("Espera-se um erro");
        } catch (IllegalArgumentException iea) {
            assertEquals("Entrada inválida!", iea.getMessage());
        }
    }

    @Test
    void testeAdicionaMatriculaExistente() {
        this.controle.criaObjetoAluno("250", "Gabriel Reyes", "Computação");
        Aluno espero = this.controle.adicionaAluno("250", alunoBase);
        assertEquals(alunoBase, espero);
    }

    @Test
    void testeAdicionaAlunoNovo() {
        this.controle.criaObjetoAluno("250", "Gabriel Reyes", "Computação");
        Aluno espero = this.controle.adicionaAluno("251", alunoBase);
        assertNull(espero);
    }

    @Test
    void testeRecuperaAluno() {
        this.controle.criaObjetoAluno("250", "Gabriel Reyes", "Computação");
        Aluno espero = this.controle.recuperaAluno("250");
        assertEquals("Gabriel Reyes", espero.toString());
    }

    @Test
    void testeRecuperaAlunoInexistente() {
        this.controle.criaObjetoAluno("250", "Gabriel Reyes", "Computação");
        Aluno espero = this.controle.recuperaAluno("251");
        assertNull(espero);
    }

}