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
            assertEquals("Entrada vazia!", iea.getMessage());
        }
    }

    @Test
    void cadastraAlunoMatriculaNula() {
        try {
            new Aluno(null, "Gabriel Reyes", "Computação");
            fail("Espera-se um erro");
        } catch (NullPointerException npe) {
            assertEquals("Entrada nula!", npe.getMessage());
        }
    }

    @Test
    void cadastraAlunoNomeVazio() {
        try {
            new Aluno("250", "", "Computação");
            fail("Espera-se um erro");
        } catch (IllegalArgumentException iea) {
            assertEquals("Entrada vazia!", iea.getMessage());
        }
    }

    @Test
    void cadastraAlunoNomeNulo() {
        try {
            new Aluno("250", null, "Computação");
            fail("Espera-se um erro");
        } catch (NullPointerException npe) {
            assertEquals("Entrada nula!", npe.getMessage());
        }
    }

    @Test
    void cadastraAlunoCursoVazio() {
        try {
            new Aluno("250", "Gabriel Reyes", "");
            fail("Espera-se um erro");
        } catch (IllegalArgumentException iea) {
            assertEquals("Entrada vazia!", iea.getMessage());
        }
    }

    @Test
    void cadastraAlunoCursoNulo() {
        try {
            new Aluno("250", "Gabriel Reyes", null);
            fail("Espera-se um erro");
        } catch (NullPointerException npe) {
            assertEquals("Entrada nula!", npe.getMessage());
        }
    }

    @Test
    void testToString() {
        assertEquals("Gabriel Reyes", alunoBase.toString());
    }

    @Test
    void testEqualsObject() {
        Aluno novoAluno = new Aluno("250", "Gabriel Reyes", "Computação");
        assertEquals(alunoBase, novoAluno);
    }

    @Test
    void testEqualsObjectMatriculasDiferentes() {
        Aluno novoAluno = new Aluno("251", "Gabriel Reyes", "Computação");
        assertNotEquals(alunoBase, novoAluno);
    }

    @Test
    void testHashCode() {
        Aluno novoAluno = new Aluno("250", "Gabriel Reyes", "Computação");
        assertEquals(alunoBase.hashCode(), novoAluno.hashCode());
    }
}