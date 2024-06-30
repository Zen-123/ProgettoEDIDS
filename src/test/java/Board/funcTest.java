package Board;

import Player.Item;
import Player.mostro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class funcTest {

    private func testFunc;

    @BeforeEach
    //creazione oggetto di tipo func
    void setUp() {
        testFunc = new func();
    }

    @Test
    //test dei costruttori
    void testConstructor() {
        assertNotNull(testFunc);
    }

    @RepeatedTest(10)
    //test metodo generateItem per la creazione di un item e inserirlo nella stanza corrente
    void testGenerateItem() {
        Item item = func.generateItem(5, 5);
        //controlla se item viene generato
        assertNotNull(item);
        //controlla se item viene posizionato nelle coordinate corrette
        assertEquals(5, item.getX());
        assertEquals(5, item.getY());
        //controlla se viene assegnato simbolo corretto ad Item
        assertEquals('I', item.getSymbol());  // 'I' =  simbolo item nella mappa di gioco
        assertTrue(item.getNome().equals("spada") || item.getNome().equals("armatura"));
    }

    @RepeatedTest(10)
    //test metodo generateMonsert
    void testGenerateMonster() {
        mostro monster = func.generateMonster(3, 3);
        //controlla se mostro generato correttamente
        assertNotNull(monster);
        //controlla se mostro viene posizionato nelle coordinate (3,3)
        assertEquals(3, monster.getX());
        assertEquals(3, monster.getY());
        //controlla se viene assegnato simbolo corretto al mostro
        assertEquals('M', monster.getSymbol());
        assertTrue(monster.getNome().equals("vampiro") ||
                monster.getNome().equals("scheletro") ||
                monster.getNome().equals("goblin") ||
                monster.getNome().equals("nano") ||
                monster.getNome().equals("strega"));
    }

    @Test
    //test di metodo generateBoss
    void testGenerateBoss() {
        mostro boss = func.generateBoss(10, 10);
        //controlla se boss viene generato correttamente
        assertNotNull(boss);
        //controlla se boss viene posizionato correttamente
        assertEquals(10, boss.getX());
        assertEquals(10, boss.getY());
        //controlla se al boss sono assegnate correttamente tutte le stats
        assertEquals('B', boss.getSymbol());
        assertEquals("Piovra", boss.getNome());
        assertEquals(40, boss.getDanno_max());
        assertEquals(15, boss.getDanno_min());
        assertEquals(10, boss.getDifesa());
        assertEquals(100, boss.getVita());
    }


}