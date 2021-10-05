package BibliTex.Transforma;

import BibliTex.Transforma.CleanSpaces;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CleanSpacesTest {

    @Test
    void transformar() {
        String textoOriginal = "oi, como vc vai?";
        String textoTransformado = new CleanSpaces().transformar(textoOriginal);
        assertEquals("oi,comovcvai?", textoTransformado);
    }

    @Test
    void testGetNome() {
        String getNome = new CleanSpaces().getNome();
        assertEquals("CleanSpaces", getNome);
    }
}