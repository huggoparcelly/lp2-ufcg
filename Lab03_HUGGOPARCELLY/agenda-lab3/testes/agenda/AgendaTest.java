package agenda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AgendaTest {

    private Agenda agenda;

    @BeforeEach
    void criaAgenda() {
        this.agenda = new Agenda();
    }

    @Test
    void testCadastraContatoEmPosicaoVazia() {
        boolean sucesso = this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        assertTrue(sucesso);
    }

    @Test
    void testCadastraContatoEmPosicaoExistente() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        boolean sucesso = this.agenda.cadastraContato(1, "Pedro", "Silva", "(84) 98888-1111");
        assertTrue(sucesso);
    }

    @Test
    void testCadastraContatoExistente() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        try {
            this.agenda.cadastraContato(3, "Matheus", "Gaudencio", "(83) 99999-0000");
        } catch (IllegalArgumentException exception) {
            assertEquals("CONTATO JA CADASTRADO", exception.getMessage());
        }
    }

    @Test
    void testCadastraContatoPosicaoLimite() {
        this.agenda.cadastraContato(100, "Matheus", "Gaudencio", "(83) 99999-0000");
        boolean sucesso = this.agenda.cadastraContato(1, "Pedro", "Silva", "(84) 98888-1111");
        assertTrue(sucesso);
    }

    @Test
    void testCadastraContatoPosicaoAcima() {
        try {
            this.agenda.cadastraContato(101, "Matheus", "Gaudencio", "(83) 99999-0000");
        } catch (IllegalArgumentException exception) {
            assertEquals("POSIÇÃO INVÁLIDA", exception.getMessage());
        }
    }

    @Test
    void testCadastraContatoPosicaoAbaixo() {
        try {
            this.agenda.cadastraContato(0, "Matheus", "Gaudencio", "(83) 99999-0000");
        } catch (IllegalArgumentException exception) {
            assertEquals("POSIÇÃO INVÁLIDA", exception.getMessage());
        }
    }

    @Test
    void testCadastraContatoTelefoneVazio() {
        try {
            this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "");
        } catch (IllegalArgumentException exception) {
            assertEquals("CONTATO INVALIDO", exception.getMessage());
        }
    }

    @Test
    void testCadastraContatoTelefoneNulo() {
        try {
            this.agenda.cadastraContato(1, "Matheus", "Gaudencio", null);
        } catch (IllegalArgumentException exception) {
            assertEquals("CONTATO INVALIDO", exception.getMessage());
        }
    }

    @Test
    void cadastraContatoNomeVazio() {
        try {
            this.agenda.cadastraContato(1, "", "Gaudencio", "(83) 99999-0000");
        } catch (IllegalArgumentException exception) {
            assertEquals("CONTATO INVALIDO", exception.getMessage());
        }
    }

    @Test
    void cadastraContatoNomeNulo() {
        try {
            this.agenda.cadastraContato(1, null, "Gaudencio", "(83) 99999-0000");
        } catch (IllegalArgumentException exception) {
            assertEquals("CONTATO INVALIDO", exception.getMessage());
        }
    }

    @Test
    void verificaContatoComContatoDiferente() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        boolean sucesso = this.agenda.verificaContato("Pedro", "Silva", "(84) 98888-1111");
        assertFalse(sucesso);
    }

    @Test
    void verificaContatoComContatoIgual() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        boolean sucesso = this.agenda.verificaContato("Matheus", "Gaudencio", "(83) 99999-0000");
        assertTrue(sucesso);
    }

    @Test
    void listarContatos() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        this.agenda.cadastraContato(2, "Pedro", "Silva", "(84) 98888-1111");
        assertEquals("1 - Matheus Gaudencio" + "\n" + "2 - Pedro Silva" + "\n", agenda.listaContato());
    }

    @Test
    void cadastraFavoritoEmPosicaoVazia() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        boolean sucesso = this.agenda.cadastraFavorito(1, 1);
        assertTrue(sucesso);
    }

    @Test
    void cadastraFavoritoEmPosicaoSuperior() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        try {
            this.agenda.cadastraFavorito(1, 11);
        } catch (IllegalArgumentException exception) {
            assertEquals("POSIÇÃO INVÁLIDA", exception.getMessage());
        }
    }

    @Test
    void cadastraFavoritoEmPosicaoInferior() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        try {
            this.agenda.cadastraFavorito(1, 0);
        } catch (IllegalArgumentException exception) {
            assertEquals("POSIÇÃO INVÁLIDA", exception.getMessage());
        }
    }

    @Test
    void cadastraMesmoFavoritoEmPosicaoDiferente() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        this.agenda.cadastraFavorito(1, 1);
        try {
            this.agenda.cadastraFavorito(1, 2);
        } catch (IllegalArgumentException exception) {
            assertEquals("CONTATO JÁ CADASTRADO", exception.getMessage());
        }
    }

    @Test
    void cadastraDoisFavoritosEmPosicoesDiferentes() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        this.agenda.cadastraContato(2, "Pedro", "Silva", "(84) 98888-1111");
        this.agenda.cadastraFavorito(1, 1);
        boolean sucesso = this.agenda.cadastraFavorito(2, 2);
        assertTrue(sucesso);
    }

    @Test
    void cadastraFavoritosNaMesmaPosicao() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        this.agenda.cadastraContato(2, "Pedro", "Silva", "(84) 98888-1111");
        this.agenda.cadastraFavorito(1, 1);
        boolean sucesso = this.agenda.cadastraFavorito(2, 1);
        assertTrue(sucesso);
    }

    @Test
    void listarFavoritos() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        this.agenda.cadastraFavorito(1, 1);
        assertEquals("1 - Matheus Gaudencio" + "\n", this.agenda.listarFavoritos());
    }


    @Test
    void cadastraTagEmUmContato() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        boolean sucesso = this.agenda.cadastraTag("1", "UFCG", 1);
        assertTrue(sucesso);
    }

    @Test
    void cadastraTagEmDoisContatos() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        this.agenda.cadastraContato(2, "Pedro", "Silva", "(84) 98888-1111");
        boolean sucesso = this.agenda.cadastraTag("1 2", "UFCG", 1);
        assertTrue(sucesso);
    }

    @Test
    void cadastraTagEmContatoInexistente() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        try {
            this.agenda.cadastraTag("2", "UFCG", 1);
        } catch (IllegalArgumentException exception) {
            assertEquals("CONTATO INEXISTENTE", exception.getMessage());
        }

    }

    @Test
    void cadastraTagJaCadastrada() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        this.agenda.cadastraTag("1", "UFCG", 1);
        try {
            this.agenda.cadastraTag("1", "UFCG", 2);
        } catch (IllegalArgumentException exception) {
            assertEquals("TAG JÁ CADASTRADA", exception.getMessage());
        }
    }

    @Test
    void cadastraTagPosicaoInferior() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        try {
            this.agenda.cadastraTag("1", "UFCG", 0);
        } catch (IllegalArgumentException exception) {
            assertEquals("POSIÇÃO DA TAG INVÁLIDA", exception.getMessage());
        }
    }

    @Test
    void cadastraTagPosicaoSuperior() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        try {
            this.agenda.cadastraTag("1", "UFCG", 6);
        } catch (IllegalArgumentException exception) {
            assertEquals("POSIÇÃO DA TAG INVÁLIDA", exception.getMessage());
        }
    }


    @Test
    void exibicaoContato() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        String exibe = this.agenda.exibicaoContato(1);
        assertEquals("Matheus Gaudencio" + "\n" + "(83) 99999-0000" + "\n", exibe);
    }

    @Test
    void exibicaoContatoPosicaoSemContato() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        try {
            this.agenda.exibicaoContato(100);
        } catch (IllegalArgumentException exception) {
            assertEquals("CONTATO INEXISTENTE", exception.getMessage());
        }
    }

    @Test
    void exibicaoContatoPosicaoAbaixo() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        try {
            this.agenda.exibicaoContato(0);
        } catch (IllegalArgumentException exception) {
            assertEquals("POSIÇÃO INVÁLIDA", exception.getMessage());
        }
    }

    @Test
    void exibicaoContatoPosicaoAcima() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        try {
            this.agenda.exibicaoContato(101);
        } catch (IllegalArgumentException exception) {
            assertEquals("POSIÇÃO INVÁLIDA", exception.getMessage());
        }
    }

    @Test
    void exibicaoContatoFavoritado() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        this.agenda.cadastraFavorito(1,1);
        String exibe = this.agenda.exibicaoContato(1);
        assertEquals("❤ " + "Matheus Gaudencio" + "\n" + "(83) 99999-0000" + "\n", exibe);
    }

    @Test
    void exibicaoContatoComTags() {
        this.agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        this.agenda.cadastraTag("1", "professor-ufcg", 1);
    }

}