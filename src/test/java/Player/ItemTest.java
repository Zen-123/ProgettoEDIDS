package Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class ItemTest {

    private Item defaultItem;
    private Item customItem;

    @BeforeEach
    //setting degli oggetti usati in fase di test per evitare lancio di eccezioni
    void setUp() {
        //creazione oggetto Item di default
        defaultItem = new Item();
        //creazione oggetto Item personalizzato
        customItem = new Item("Spada", 10, 5, 3, true, 1, false,20);
    }

    @Test
    //test costruttore di default di un oggetto Item
    void testDefaultConstructor() {
        assertEquals("", defaultItem.getNome());
        assertEquals(0, defaultItem.getAttacco_max());
        assertEquals(0, defaultItem.getAttacco_min());
        assertEquals(0, defaultItem.getDifesa());
        assertFalse(defaultItem.isIsSword());
        assertEquals(0, defaultItem.getId_stanza());
        assertFalse(defaultItem.isHasTake());
        assertFalse(defaultItem.CanAttack());
    }

    @Test
    //test costruttore parametrizzato per la creazione di un oggetto personalizzato
    void testParameterizedConstructor() {
        assertEquals("Spada", customItem.getNome());
        assertEquals(10, customItem.getAttacco_max());
        assertEquals(5, customItem.getAttacco_min());
        assertEquals(3, customItem.getDifesa());
        assertTrue(customItem.isIsSword());
        assertEquals(1, customItem.getId_stanza());
        assertFalse(customItem.isHasTake());
        assertFalse(customItem.CanAttack());
    }

    @Test
    //test metodo setCanAttack per Item di tipo spada
    void testSetCanAttack() {
        assertFalse(customItem.CanAttack());
        customItem.setCanAttack(true);
        assertTrue(customItem.CanAttack());
    }

    @RepeatedTest(100)
    //test getDanno ripetuto perchè getDanno restituisce un valore casuale che deve trovarsi tra valore massimo Attacco_max e valore minimo Attacco_min
    void testGetDanno() {
        int danno = customItem.getDanno();
        assertTrue(danno >= customItem.getAttacco_min() && danno <= customItem.getAttacco_max());
    }

    @Test
    //test di metodo setId_stanza personalizzato
    void testSetId_stanza() {
        customItem.setId_stanza(2);
        assertEquals(2, customItem.getId_stanza());
    }

    @Test
    //test metodo setHasTake
    void testSetHasTake() {
        assertFalse(customItem.isHasTake());
        customItem.setHasTake(true);
        assertTrue(customItem.isHasTake());
    }

    @Test
    //test metodi set e get di Item
    void testInheritanceFromEntity() {
        customItem.setX(5);
        customItem.setY(10);
        customItem.setSymbol('I');

        assertEquals(5, customItem.getX());
        assertEquals(10, customItem.getY());
        assertEquals('I', customItem.getSymbol());
    }
}