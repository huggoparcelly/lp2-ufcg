package com.matheusgr.lunr;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class SimilaridadeServiceTest extends BaseTest {

    @Test
    void similaridade() {
        var documentoOpt1 = this.documentoController.recuperarDocumento(TEXTO1_ID);
        var documentoOpt2 = this.documentoController.recuperarDocumento(TEXTO2_ID);
        assertTrue(documentoOpt1.isPresent());
        assertTrue(documentoOpt2.isPresent());
        var doc1 = documentoOpt1.get();
        var doc2 = documentoOpt2.get();
        double similaridade = this.similaridadeController.similaridade(doc1.getId(), doc2.getId());
        assertEquals(0.33, similaridade);
    }
}