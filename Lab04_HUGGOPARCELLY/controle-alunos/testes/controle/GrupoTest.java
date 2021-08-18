package controle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class GrupoTest {

    private Grupo grupoBase;
//    criar aluno base

    @BeforeEach
    void criaGrupoBase() {
        this.grupoBase = new Grupo("Lista", "2");
    }

    @Test
    void cadastraNomeGrupoVazio() {
        try {
            new Grupo("", "10");
            fail("Espera-se um erro");
        } catch (IllegalArgumentException iea) {
            assertEquals("NOME DO GRUPO VAZIO!", iea.getMessage());
        }
    }

    @Test
    void cadastraNomeGrupoNulo() {
        try {
            new Grupo(null, "10");
            fail("Espera-se um erro");
        } catch (NullPointerException npe) {
            assertEquals("NOME DO GRUPO NULO!", npe.getMessage());
        }
    }


    @Test
    void entraAlunoNoGrupoBase() {
        Aluno aluno = new Aluno("250", "Gabriel Reyes", "Computação");
        Aluno aluno2 = new Aluno("251", "Ana Reyes", "Computação");
        Aluno aluno3 = new Aluno("252", "Maria Reyes", "Computação");
        grupoBase.entraAluno(aluno);
        grupoBase.entraAluno(aluno2);
        boolean adicionaNoGrupo = grupoBase.entraAluno(aluno3);
        assertFalse(adicionaNoGrupo);
    }

    @Test
    void entraNoGrupoComTamanhoVazio() {
        Grupo novoGrupo = new Grupo("Exercicios", "");
        Aluno aluno = new Aluno("250", "Gabriel Reyes", "Computação");
        Aluno aluno2 = new Aluno("251", "Ana Reyes", "Computação");
        Aluno aluno3 = new Aluno("252", "Maria Reyes", "Computação");
        novoGrupo.entraAluno(aluno);
        novoGrupo.entraAluno(aluno2);
        boolean adicionaNoGrupo = novoGrupo.entraAluno(aluno3);
        assertTrue(adicionaNoGrupo);
    }

    @Test
    void testeAlunoPertenceAoGrupo() {
        Aluno aluno = new Aluno("250", "Gabriel Reyes", "Computação");
        grupoBase.entraAluno(aluno);
        boolean pertence = grupoBase.pertenceAoGrupo(aluno);
        assertTrue(pertence);
    }

    @Test
    void testeAlunoNaoPertenceAoGrupo() {
        Aluno aluno = new Aluno("250", "Gabriel Reyes", "Computação");
        boolean pertence = grupoBase.pertenceAoGrupo(aluno);
        assertFalse(pertence);
    }

    @Test
    void testEqualsObject() {
        Grupo novoGrupo = new Grupo("Lista", "2");
        assertEquals(grupoBase, novoGrupo);
    }

    @Test
    void testEqualsObjectGruposDiferentes() {
        Grupo novoGrupo = new Grupo("Exercicios", "");
        assertNotEquals(grupoBase, novoGrupo);
    }

    @Test
    void testHashCode() {
        Grupo novoGrupo = new Grupo("Lista", "2");
        assertEquals(grupoBase.hashCode(), novoGrupo.hashCode());
    }
}