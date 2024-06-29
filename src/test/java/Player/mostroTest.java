package Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class MostroTest {

    private mostro defaultMostro;
    private mostro customMostro;

    @BeforeEach
    void setUp() {
        //creazione mostro di default
        defaultMostro = new mostro();
        //creazione mostro personalizzato
        customMostro = new mostro("Scheletro", 10, 5, 3, 100, 1);
    }

    @Test
    //test di verifica costruttore di default per la creazione di mostro default
    void testDefaultConstructor() {
        assertNull(defaultMostro.getNome());
        assertEquals(0, defaultMostro.getDanno_max());
        assertEquals(0, defaultMostro.getDanno_min());
        assertEquals(0, defaultMostro.getDifesa());
        assertEquals(0, defaultMostro.getVita());
        assertEquals(0, defaultMostro.getIdstanza());
    }

    @Test
    //test di verifica costruttore parametrizzato per la creazione di mostro personalizzato
    void testParameterizedConstructor() {
        assertEquals("Scheletro", customMostro.getNome());
        assertEquals(10, customMostro.getDanno_max());
        assertEquals(5, customMostro.getDanno_min());
        assertEquals(3, customMostro.getDifesa());
        assertEquals(100, customMostro.getVita());
        assertEquals(1, customMostro.getIdstanza());
    }

    @Test
    //test metodo setNome
    void testSetNome() {
        defaultMostro.setNome("Goblin");
        assertEquals("Goblin", defaultMostro.getNome());
    }

    @RepeatedTest(100)
    //test metodo getDanno che viene ripetuto 10 volte perchè danno del mostro è un valore generato casualmente che deve trovarsi tra intervallo di danno_max e danno_min
    void testGetDanno() {
        int danno = customMostro.getDanno();
        assertTrue(danno >= customMostro.getDanno_min() && danno <= customMostro.getDanno_max());
    }

    @Test
    //test setDifesa
    void testSetDifesa() {
        defaultMostro.setDifesa(5);
        assertEquals(5, defaultMostro.getDifesa());
    }

    @Test
    //test setVita
    void testSetVita() {
        defaultMostro.setVita(50);
        assertEquals(50, defaultMostro.getVita());
    }

    @Test
    //test takeDamage
    void testTakeDamage() {
        customMostro.takeDamage(-20);
        assertEquals(80, customMostro.getVita(), "la vita del mostro dovrebbe decrementare di 20, partendo da 100");

    }

    @Test
    //test setIdStanza
    void testSetIdstanza() {
        defaultMostro.setIdstanza(2);
        assertEquals(2, defaultMostro.getIdstanza());
    }

    @Test
    //test setDannoMax mostro
    void testSetDanno_max() {
        defaultMostro.setDanno_max(15);
        assertEquals(15, defaultMostro.getDanno_max());
    }

    @Test
    //test setDannoMin mostro
    void testSetDanno_min() {
        defaultMostro.setDanno_min(3);
        assertEquals(3, defaultMostro.getDanno_min());
    }

    @Test
    //test set e get di Item
    void testInheritanceFromEntity() {
        customMostro.setX(5);
        customMostro.setY(10);
        customMostro.setSymbol('M');

        assertEquals(5, customMostro.getX());
        assertEquals(10, customMostro.getY());
        assertEquals('M', customMostro.getSymbol());
    }
}