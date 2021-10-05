package BibliTex.Transforma;

import BibliTex.Transforma.Clean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CleanTest {

    @Test
    void transformar() {
        String textoOriginal = "oi, como vc vai?";
        String textoTransformado = new Clean().transformar(textoOriginal);
        assertEquals("oi como vc vai", textoTransformado);
    }

    @Test
    void testGetNome() {
        String getNome = new Clean().getNome();
        assertEquals("Clean", getNome);
    }
}