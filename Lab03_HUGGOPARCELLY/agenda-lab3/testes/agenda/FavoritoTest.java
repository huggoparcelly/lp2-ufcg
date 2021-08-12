package agenda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FavoritoTest {

    private Favorito favorito;

    @BeforeEach
    void tagBase() {
        this.favorito = new Favorito("Matheus Gaudencio");
    }

    @Test
    void testContatoFavorito() {
        assertEquals("Matheus Gaudencio", favorito.toString());
    }

    @Test
    void testContatoFavoritoVazio() {
        try {
            new Favorito("");
        } catch (IllegalArgumentException exception) {
            assertEquals("CONTATO INVALIDO", exception.getMessage());
        }
    }

    @Test
    void testContatoFavoritoNula() {
        try {
            new Favorito(null);
        } catch (IllegalArgumentException exception) {
            assertEquals("CONTATO INVALIDO", exception.getMessage());
        }
    }

    @Test
    void testToString() {
        Favorito f1 = new Favorito("Livia Sampaio");
        assertEquals("Livia Sampaio", f1.toString());
    }

    @Test
    void testEquals() {
        Favorito f1 = new Favorito("Livia Sampaio");
        Favorito f2 = new Favorito("Livia Sampaio");
        assertEquals(f1, f2);
    }

    @Test
    void testHashCode() {
        Favorito f1 = new Favorito("Livia Sampaio");
        Favorito f2 = new Favorito("Livia Sampaio");
        assertEquals(f1.hashCode(), f2.hashCode());
    }
}