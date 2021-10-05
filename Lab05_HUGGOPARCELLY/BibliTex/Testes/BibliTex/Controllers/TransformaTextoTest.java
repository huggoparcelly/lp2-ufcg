package BibliTex.Controllers;

import BibliTex.Intefaces.Transformacao;
import BibliTex.Logger.BreakpointLogger;
import BibliTex.Logger.ConsoleLogger;
import BibliTex.Logger.ContagemLogger;
import BibliTex.Logger.TimeConsoleLogger;
import BibliTex.Transforma.UpperCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransformaTextoTest {

    private ConsoleLogger cl;
    private TimeConsoleLogger tcl;
    private ContagemLogger ctl;


    @BeforeEach
    void criaLoggers() {
        this.cl = new ConsoleLogger();
        this.tcl = new TimeConsoleLogger();
        this.ctl = new ContagemLogger();
    }

    @Test
    void cadastraTransformacao() {
        TransformaTexto tt = new TransformaTexto(cl);
        Transformacao meuAlgoritmo = new UpperCase();
        boolean sucess = tt.cadastraTransformacao("minhaTransformacao", meuAlgoritmo);
        assertTrue(sucess);
    }

    @Test
    void testTransformaConsoleLogger() {
        TransformaTexto tt = new TransformaTexto(cl);
        String tipoTransformacao = "UpperCase";
        String textoOriginal = "oi, como vc vai?";
        String textoTransformado = tt.transforma(tipoTransformacao, textoOriginal);
        assertEquals("[transforma] UpperCase\nOI, COMO VC VAI?", textoTransformado);
    }

    @Test
    void testTransformaTimeConsoleLogger() {
        TransformaTexto tt = new TransformaTexto(tcl);
        long timer = (long) (System.currentTimeMillis() / Math.pow(10, 11));
        String tipoTransformacao = "UpperCase";
        String textoOriginal = "oi, como vc vai?";
        String textoTransformado = tt.transforma(tipoTransformacao, textoOriginal);
        assertEquals("[transforma - " + timer + "ms] UpperCase\n" +
                "OI, COMO VC VAI?", textoTransformado);
    }

    @Test
    void testContaTransformacaoBreakpointLogger() {
        BreakpointLogger il = new BreakpointLogger("contaTransformacao");
        TransformaTexto tt = new TransformaTexto(il);
        String tipoTransformacao1 = "UpperCase";
        String tipoTransformacao2 = "CaMeLcAsEfY";
        String textoOriginal = "oi, como vc vai?";
        String transformacao1 = tt.transforma(tipoTransformacao1, textoOriginal);
        String transformacao2 = tt.transforma(tipoTransformacao2, textoOriginal);
        String quantidadeTransformacao = tt.contaTransformacao();
        assertEquals("OI, COMO VC VAI?", transformacao1);
        assertEquals("Oi, CoMo vC VaI?", transformacao2);
        assertEquals("[INVOCADO - contaTransformacao]\n2", quantidadeTransformacao);
    }

    @Test
    void testHitoricoTransformacoesContagemLogger() {
        TransformaTexto tt = new TransformaTexto(ctl);
        String textoTransformado = tt.transforma("UpperCase", "oi, como vc vai?");
        String historico = tt.historico(0);
        tt.transforma("LowerCase", "OI, COMO VOCê VAI?");
        String contagem = ctl.toString();
        assertEquals("oi, como vc vai? -> UpperCase -> " + textoTransformado, historico);
        assertEquals("historico - 1\ntransforma - 2\n", contagem);
    }

    @Test
    void listarTextosOriginaisConcoleLogger() {
        TransformaTexto tt = new TransformaTexto(cl);
        String tipoTransformacao1 = "UpperCase";
        String tipoTransformacao2 = "CaMeLcAsEfY";
        String tipoTransformacao3 = "InterrogaParaPontos";
        String textoOriginal1 = "oi, como vc vai?";
        String textoOriginal2 = "tudo bem com vc?";
        String textoOriginal3 = "Olá, como vc vai?";
        tt.transforma(tipoTransformacao1, textoOriginal1);
        tt.transforma(tipoTransformacao2, textoOriginal2);
        tt.transforma(tipoTransformacao3, textoOriginal3);
        String listaOriginais = tt.listarOriginais();
        assertEquals("[listarOriginais] \n" + textoOriginal1 + "\n" +
                textoOriginal2 + "\n" + textoOriginal3 + "\n", listaOriginais);
    }

    @Test
    void listarTextosOriginaisVerificandoRepeticao() {
        TransformaTexto tt = new TransformaTexto(cl);
        String tipoTransformacao1 = "UpperCase";
        String tipoTransformacao2 = "CaMeLcAsEfY";
        String tipoTransformacao3 = "InterrogaParaPontos";
        String textoOriginal1 = "tudo bem com vc?";
        String textoOriginal2 = "oi, como vc vai?";
        String textoOriginal3 = "oi, como vc vai?"; // não aparece
        tt.transforma(tipoTransformacao1, textoOriginal1);
        tt.transforma(tipoTransformacao2, textoOriginal2);
        tt.transforma(tipoTransformacao3, textoOriginal3);
        String listaOriginais = tt.listarOriginais();
        assertEquals("[listarOriginais] \n" + textoOriginal1 +
                "\n" + textoOriginal2 + "\n", listaOriginais);
    }

    @Test
    void testListarTransformacoesTimeConsoleLogger() {
        TransformaTexto tt = new TransformaTexto(tcl);
        long timer = (long) (System.currentTimeMillis() / Math.pow(10, 11));
        String listaTransformacoes = tt.listarTransformacoes();
        assertEquals("[listarTransformacoes - " + timer + "ms] \n" +
                "CaMeLcAsEfY\n" +
                "Clean\n" +
                "CleanSpaces\n" +
                "InterrogaParaPontos\n" +
                "LowerCase\n" +
                "UpperCase\n", listaTransformacoes);
    }
}