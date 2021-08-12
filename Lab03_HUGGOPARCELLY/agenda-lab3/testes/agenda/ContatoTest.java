package agenda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ContatoTest {

    private Contato contatoBase;

    @BeforeEach
    void preparaContatos() {
        this.contatoBase = new Contato("Matheus", "Gaudencio", "(83) 99999-0000");
    }

    @Test
    void testNomeVazio() {
        try {
            new Contato("", "Gaudencio", "(83) 99999-0000");
            fail("Era esperado um erro");
        } catch (IllegalArgumentException exception) {
            assertEquals("CONTATO INVALIDO", exception.getMessage());
        }
    }

    @Test
    void testNomeNulo() {
        try {
            new Contato(null, "Gaudencio", "(83) 99999-0000");
            fail("Era esperado um erro");
        } catch (IllegalArgumentException exception) {
            assertEquals("CONTATO INVALIDO", exception.getMessage());
        }
    }

    @Test
    void testTelefoneVazio() {
        try {
            new Contato("Matheus", "Gaudencio", "");
            fail("Era esperado um erro");
        } catch (IllegalArgumentException exception) {
            assertEquals("CONTATO INVALIDO", exception.getMessage());
        }
    }

    @Test
    void testTelefoneNulo() {
        try {
            new Contato("Matheus", "Gaudencio", null);
            fail("Era esperado um erro");
        } catch (IllegalArgumentException exception) {
            assertEquals("CONTATO INVALIDO", exception.getMessage());
        }
    }


    @Test
    void cadastraTag() {
        boolean sucesso = contatoBase.cadastraTag("UFCG", 1);
        assertTrue(sucesso);
    }

    @Test
    void nomeTag() {
        contatoBase.cadastraTag("UFCG", 1);
        assertEquals("UFCG", contatoBase.nomeTag());
    }


    @Test
    void testToString() {
        Contato c1 = new Contato("Matheus", "Gaudencio", "(83) 99999-0000");
        assertEquals("Matheus Gaudencio", c1.toString());
    }

    @Test
    void testEqualsObject() {
        Contato c1 = new Contato("Matheus", "Gaudencio", "(83) 99999-0000");
        Contato c2 = new Contato("Matheus", "Gaudencio", "(83) 99999-0000");
        assertEquals(c1, c2);
    }

    @Test
    void testEqualsObjectNomesDiferentes() {
        Contato c1 = new Contato("Matheus", "Gaudencio", "(83) 99999-0000");
        Contato c2 = new Contato("Pedro", "Paulo", "(83) 88888-0000");
        assertNotEquals(c1, c2);
    }

    @Test
    void testHashCode() {
        Contato c1 = new Contato("Matheus", "Gaudencio", "(83) 99999-0000");
        Contato c2 = new Contato("Matheus", "Gaudencio", "(83) 99999-0000");
        assertEquals(c1.hashCode(), c2.hashCode());
    }


}