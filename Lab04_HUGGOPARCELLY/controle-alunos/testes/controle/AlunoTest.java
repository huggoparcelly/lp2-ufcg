package controle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {

    private Aluno alunoBase;

    @BeforeEach
    void cadastraAlunoCasoBase() {
        this.alunoBase = new Aluno("250", "Gabriel Reyes", "Computação");
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
    void testToString() {
        Aluno aluno = new Aluno("250", "Gabriel Reyes", "Computação");
        assertEquals("Gabriel Reyes", aluno.toString());
    }

    @Test
    void testEqualsObject() {
        Aluno aluno1 = new Aluno("250", "Gabriel Reyes", "Computação");
        Aluno aluno2 = new Aluno("250", "Gabriel Reyes", "Computação");
        assertEquals(aluno1, aluno2);
    }

    @Test
    void testEqualsObjectMatriculasDiferentes() {
        Aluno aluno1 = new Aluno("251", "Gabriel Reyes", "Computação");
        Aluno aluno2 = new Aluno("250", "Gabriel Reyes", "Computação");
        assertNotEquals(aluno1, aluno2);
    }

    @Test
    void testHashCode() {
        Aluno aluno1 = new Aluno("250", "Gabriel Reyes", "Computação");
        Aluno aluno2 = new Aluno("250", "Gabriel Reyes", "Computação");
        assertEquals(aluno1.hashCode(), aluno2.hashCode());
    }
}