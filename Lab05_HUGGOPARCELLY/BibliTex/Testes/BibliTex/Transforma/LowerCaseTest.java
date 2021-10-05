package BibliTex.Transforma;

import BibliTex.Transforma.LowerCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LowerCaseTest {

    @Test
    void transformar() {
        String textoOriginal = "OI, COMO VC VAI?";
        String textoTransformado = new LowerCase().transformar(textoOriginal);
        assertEquals("oi, como vc vai?", textoTransformado);
    }

    @Test
    void testGetNome() {
        String getNome = new LowerCase().getNome();
        assertEquals("LowerCase", getNome);
    }
}