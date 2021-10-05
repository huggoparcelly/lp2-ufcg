package BibliTex.Transforma;

import BibliTex.Transforma.UpperCase;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UpperCaseTest {

    @Test
    void testTransformarUpperCase() {
        String textoOriginal = "oi, como vc vai?";
        String textoTransformado = new UpperCase().transformar(textoOriginal);
        assertEquals("OI, COMO VC VAI?", textoTransformado);
    }

    @Test
    void testGetNome() {
        String getNome = new UpperCase().getNome();
        assertEquals("UpperCase", getNome);
    }
}