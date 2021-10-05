package BibliTex.Transforma;

import BibliTex.Transforma.CaMeLcAsEfY;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaMeLcAsEfYTest {

    @Test
    void testTransformarCaMeLcAsEfY() {
        String textoOriginal = "oi, como vc vai?";
        String textoTransformado = new CaMeLcAsEfY().transformar(textoOriginal);
        assertEquals("Oi, CoMo vC VaI?", textoTransformado);
    }

    @Test
    void testGetNome() {
        String getNome = new CaMeLcAsEfY().getNome();
        assertEquals("CaMeLcAsEfY", getNome);
    }
}