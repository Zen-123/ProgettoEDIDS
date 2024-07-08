package Board;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import Player.Item;
import Player.Player;
import Player.mostro;
import UI.UI;
import UI.gameBoard;
import UI.visibilityManager;

class funcTest {

    private func testFunc;

    @BeforeEach
    //creazione oggetto di tipo func
    void setUp() {
        testFunc = new func();
        reference.player = new Player();
        reference.ui = new UI();
        reference.ui.commandTextField = new JTextField();
        reference.ui.messageTextArea = new JTextArea();
        reference.ui.gameB = new gameBoard();
        reference.curr_stanza = 12;
        reference.filereader = new Readfile();
        //stanza 12 serve per tutti i test
        reference.currentStanza = new Board(12);
        //stanza 13 serve per il change room text
        reference.functions = new func();     
        reference.mostrorun = reference.currentStanza.lista_mostri.get(0);
        reference.item = reference.currentStanza.lista_item.get(0);
        visibilityManager vManager = new visibilityManager(reference.ui);
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
        assertEquals(30, boss.getDanno_max());
        assertEquals(15, boss.getDanno_min());
        assertEquals(10, boss.getDifesa());
        assertEquals(50, boss.getVita());
    }
    @Test
    void testChangeRoom(){
       //gli dico al player che ho attraversato porta W quindi spawna di fianco ad E
       reference.player.setspawnTo('E');
       reference.alreadybeen = false;
       reference.startGame = true;
       reference.functions.changeRoomAndWriteToFile(13);
       
       assertEquals(1, reference.player.getY());
       assertEquals(5, reference.player.getX());
       
       assertEquals('/', reference.player.spawnTo());
    }
    @Test
    void testPlayerIsAttacking(){
        reference.mostro = new mostro();
        reference.player.addSpada(new Item("spadagiocatore", 8, 8, 0, true, 12, true,20));
        reference.player.addArmour(new Item("armaturagiocatore", 0, 0, 1, false, 12, true,30));
        reference.player.setVita(100);
        int beforebattle = reference.mostro.getVita();
        reference.functions.playerIsAttacking();
        int afterbattle = reference.mostro.getVita();
        assertTrue(afterbattle <= beforebattle);
    }
    @Test
    void testCheckWhatYouBumped(){
        reference.player.setPeso(0);
        reference.player.setGoldKey();
        reference.player.setKeyLoad(2);
        assertTrue(reference.functions.checkwhatyoubumped(0, 0));
        assertTrue(reference.functions.checkwhatyoubumped(1, 0));
        assertTrue(reference.functions.checkwhatyoubumped(3, 0));
        assertFalse(reference.functions.checkwhatyoubumped(4, 0));
        assertTrue(reference.functions.checkwhatyoubumped(4, 1));
        assertFalse(reference.functions.checkwhatyoubumped(0, 1));
        assertFalse(reference.functions.checkwhatyoubumped(1, 1));
        assertFalse(reference.functions.checkwhatyoubumped(2, 1));
        assertFalse(reference.functions.checkwhatyoubumped(3, 1));

        assertFalse(reference.functions.checkwhatyoubumped(3, 2));
        assertFalse(reference.functions.checkwhatyoubumped(2, 3));

    }
    @RepeatedTest(10)
    void testUsingPotion(){
        reference.player.setVita(70);
        reference.player.setPeso(0);
        reference.player.setNum_pozioni(0);
        reference.player.setCanAttack(true);
        reference.player.getSpada().setCanAttack(false);
        reference.functions.playerUsingPotion();
        assertEquals(70, reference.player.getVita());
        reference.player.addPozioni();
        assertEquals(5, reference.player.getPeso());
        assertEquals(1, reference.player.getNumpozioni());
        reference.functions.playerUsingPotion();
        assertEquals(0, reference.player.getNumpozioni());

    }
    @Test
    void testMonsterEncounter(){
        mostro monster = new mostro("vampiro",18,18,1,30,12);
        reference.player.addSpada(new Item("spadagiocatore", 8, 8, 0, true, 12, true,20));
        reference.player.addArmour(new Item("armaturagiocatore", 0, 0, 1, false, 12, true,30));
        reference.player.setVita(100);
        reference.functions.monsterEncounter(2, monster,0, true);
        //dato che il danno è causale ci possono essere più valori
        assertEquals(83, 82, reference.player.getVita());

    }
    @Test
    void testPlayerTakeItem(){
        //posizione iniziale giocatore 3,3 item 3,2 mostro 2,3
        // reference.player = new Player();
        reference.currentStanza = new Board(12);
        assertEquals(1, reference.currentStanza.lista_item.size());
        assertEquals(1, reference.currentStanza.lista_mostri.size());
        reference.player.setCoordinate(3, 3);
        reference.currentStanza.lista_item.get(0).setCoordinate(3, 2);
        reference.currentStanza.lista_mostri.get(0).setCoordinate(2, 3);
        reference.currentStanza.lista_mostri.remove(0);
        reference.player.addSpada(new Item("spada", 8, 4, 0, true, 12, true,20));
        reference.player.addArmour(new Item("armatura", 0, 0, 0, false, 12, true,30));
        reference.player.setHasArmour(true);
        assertEquals("spada", reference.player.getSpada().getNome());
        assertEquals("armatura", reference.player.getArmour().getNome());
        //il giocatore prendendo Item dovrebbe spostarsi di una riga in basso o in alto
        if(reference.currentStanza.lista_item.get(0).getNome().compareTo("spada") == 0){
            reference.functions.takeItem();
            assertEquals("spada", reference.player.getSpada().getNome());
            assertEquals(3, reference.player.getX(), "coordinata x attesa");
            assertEquals(2, reference.player.getY(), "coordinata y attesa");
        }else if (reference.currentStanza.lista_item.get(0).getNome().compareTo("armatura") == 0) {
            reference.functions.takeItem();
            assertEquals("armatura", reference.player.getArmour().getNome());
            assertEquals(3, reference.player.getX(), "posizione aggiornata di coordinata x attesa");
            assertEquals(2, reference.player.getY(), "posizione aggiornata di coordinata y attesa");
        }
    }
    @RepeatedTest(50)
    void testUpdateMonsterPos(){
        reference.currentStanza = new Board(13);
        if(reference.currentStanza.cellestanza.get(3).get(3).getSymbol() == 'A')
            reference.currentStanza.cellestanza.get(3).set(3,Cell.FREE);

        int x_before = reference.currentStanza.lista_mostri.get(0).getX();
        int y_before = reference.currentStanza.lista_mostri.get(0).getY();
        reference.functions.updateMonsterPosition();
        //mi aspetto che mostro ogni volta cambia posizione
        int x_after = reference.currentStanza.lista_mostri.get(0).getX();
        int y_after = reference.currentStanza.lista_mostri.get(0).getY();
        assertTrue((x_before != x_after) || (y_before != y_after));
    }
}