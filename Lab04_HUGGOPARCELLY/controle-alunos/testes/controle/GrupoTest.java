package controle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class GrupoTest {

    private Grupo grupoBase;
    private Aluno aluno;
    private Aluno aluno2;
    private Aluno aluno3;
    private Aluno aluno4;

    @BeforeEach
    void criaGrupoBase() {
        this.grupoBase = new Grupo("Listas", "");
        this.aluno = new Aluno("250", "Gabriel Reyes", "Computação");
        this.aluno2 = new Aluno("251", "Ana Reyes", "Computação");
        this.aluno3 = new Aluno("252", "Maria Reyes", "Computação");
        this.aluno4 = new Aluno("201","Torbjorn Lindholm", "Torbjorn Lindholm");
    }

    @Test
    void cadastrarGrupoSemRestricao() {
        new Grupo("Programação OO", "");
    }

    @Test
    void cadastrarGrupoComRestricaoTamanho() {
        new Grupo("Programação OO", "10");
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
    void entraNoGrupoComTamanhoVazio() {
        boolean adicionaAluno1 = grupoBase.entraAluno(this.aluno);
        assertTrue(adicionaAluno1);
        boolean adicionaAluno2 = grupoBase.entraAluno(this.aluno2);
        assertTrue(adicionaAluno2);
        boolean adicionaAluno3 = grupoBase.entraAluno(this.aluno3);
        assertTrue(adicionaAluno3);
    }

    @Test
    void testeAlunoPertenceAoGrupo() {
        grupoBase.entraAluno(this.aluno);
        boolean pertence = grupoBase.pertenceAoGrupo(aluno);
        assertTrue(pertence);
    }

    @Test
    void testeAlunoNaoPertenceAoGrupo() {
        boolean pertence = grupoBase.pertenceAoGrupo(this.aluno);
        assertFalse(pertence);
    }

    @Test
    void testEqualsObject() {
        Grupo novoGrupo = new Grupo("Listas", "10");
        assertEquals(grupoBase, novoGrupo);
    }

    @Test
    void testEqualsObjectGruposDiferentes() {
        Grupo novoGrupo = new Grupo("Programação OO", "");
        assertNotEquals(grupoBase, novoGrupo);
    }

    @Test
    void testHashCode() {
        Grupo novoGrupo = new Grupo("Listas", "10");
        assertEquals(grupoBase.hashCode(), novoGrupo.hashCode());
    }
}