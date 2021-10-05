package BibliTex.Transforma;

import BibliTex.Transforma.InterrogaParaPontos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterrogaParaPontosTest {

    @Test
    void transformar() {
        String textoOriginal = "oi, como vc vai?";
        String textoTransformado = new InterrogaParaPontos().transformar(textoOriginal);
        assertEquals("oi, como vc vai.", textoTransformado);
    }

    @Test
    void testGetNome() {
        String getNome = new InterrogaParaPontos().getNome();
        assertEquals("InterrogaParaPontos", getNome);
    }
}