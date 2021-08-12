package agenda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagTest {

    private Tag tag;

    @BeforeEach
    void tagBase() {
        this.tag = new Tag("UFCG");
    }

    @Test
    void testNomeTag() {
        assertEquals("UFCG", tag.toString());
    }

    @Test
    void testNomeTagVazio() {
        try {
            new Tag("");
        } catch (IllegalArgumentException exception) {
            assertEquals("TAG INVALIDA", exception.getMessage());
        }
    }

    @Test
    void testNomeTagNula() {
        try {
            new Tag(null);
        } catch (IllegalArgumentException exception) {
            assertEquals("TAG INVALIDA", exception.getMessage());
        }
    }

    @Test
    void testToString() {
        Tag t1 = new Tag("UFCG");
        assertEquals("UFCG", t1.toString());
    }

    @Test
    void testEquals() {
        Tag t1 = new Tag("LP2");
        Tag t2 = new Tag("LP2");
        assertEquals(t1, t2);
    }

    @Test
    void testHashCode() {
        Tag t1 = new Tag("LP2");
        Tag t2 = new Tag("LP2");
        assertEquals(t1.hashCode(), t2.hashCode());
    }
}