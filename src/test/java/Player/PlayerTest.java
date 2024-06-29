package Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;

    @BeforeEach
    //creazione oggetto di tipo Player
    void setUp() {
        player = new Player();
        player.setStanza_presente(1);
    }

    @Test
    //test costruttore di Player
    void testConstructor() {
        assertEquals(" ", player.getNome());
        assertEquals(" ", player.getCategory());
        assertEquals(100, player.getVita());
        assertTrue(player.isHasSword());
        assertFalse(player.isHasArmour());
        assertEquals(0, player.getPeso());
        assertEquals(0, player.getMonete());
        assertEquals(0, player.getMostriuccisi());
        assertEquals(1, player.getStanzapresente());
        assertEquals(0, player.getKey());
        assertEquals(0, player.getGoldkey());
        assertEquals(0, player.getNumpozioni());
    }

    @Test
    //test addSpada
    void testAddSpada() {
        //creazione di oggetto di tipo Item personalizzato, in questo caso una spada
        Item spada = new Item("Spada", 10, 5, 0, true, 1, false);
        //test metodo addSpada
        player.addSpada(spada);
        //verifica se peso di inventario giocatore aggiornato dopo aver raccolto la spada
        assertEquals(20, player.getPeso());
        assertTrue(spada.CanAttack());
        //controlla se giocatore possiede tra gli oggetti la spada
        assertEquals(spada, player.getSpada());
    }

    @Test
    //test addArmour
    void testAddArmour() {
        //creazione di oggetto di tipo Item personalizzato, in questo caso una armatura
        Item armatura = new Item("Armatura", 0, 0, 5, false, 1, false);
        //test metodo addArmour
        player.addArmour(armatura);
        //verifica che armatura aggiunta ad inventario giocatore, aggiornando peso di inventario
        assertEquals(30, player.getPeso());
        assertFalse(armatura.CanAttack());
        //verifica che giocatore possiede armatura
        assertEquals(armatura, player.getArmour());
    }

    @Test
    //test metodo usePozioni
    void testUsePozioni() {
        //metodo che aggiunge una pozione a inventario
        player.addPozioni();
        //viene settata la vita del player per testare metodo usePozioni
        player.setVita(60);
        assertEquals(5, player.getPeso(), "dopo aver aggiunto una pozione, il peso di inventario dovrebbe essere aumentato di 5");
        //metodo da testare
        player.usePozioni();

        assertEquals(90, player.getVita(), "la vita del giocatore dovrebbe aumentare di 30 dopo aver bevuto la pozione con metodo usePozioni");
        assertEquals(0, player.getNumpozioni(), "una volta usata, il numero di pozioni nell inventario dovrebbe decrementare");
    }

    @Test
    //test per verificare se giocatore subisce danno dai mostri
    void testTakeDamage() {
        //metodo da testare
        player.takeDamage(-20);
        //vita di player prima del danno: 100
        assertEquals(80, player.getVita(), "il giocatore dovrebbe avere una vita decrementata in base al danno subito, in questo caso 20");
    }

    @Test
    //test metodo isAlive
    void testIsAlive() {
        assertTrue(player.isAlive());
        player.setVita(0);
        assertFalse(player.isAlive(), "Se il giocatore ha 0 di vita allora è morto, ci aspettiamo che la funzione restituisca false");
    }

    @Test
    //test di setMonete e getMonete
    void testSetAndGetMonete() {
        player.setMonete(50);
        assertEquals(50, player.getMonete());
        player.setMoneteLoad(100);
        assertEquals(100, player.getMonete());
    }

    @Test
    //test di setkey e getkey
    void testSetAndGetKey() {
        player.setKey(2);
        assertEquals(2, player.getKey());
        assertEquals(10, player.getPeso(), "una volta aggiunte 2 chiavi all inventario il peso dovrebbe aumentare di 5 per chiave");
    }

    @Test
    //test setGoldKey e getGoldKey
    void testSetAndGetGoldKey() {
        player.setGoldKey();
        assertEquals(1, player.getGoldkey());
        assertEquals(10, player.getPeso(), "una volta aggiunta la golden key il peso dell'inventario dovrebbe aumentare di 10");
    }

    @Test
    //test di setCategory e getCategory
    void testSetAndGetCategory() {
        player.setCategory("Guerriero");
        assertEquals("Guerriero", player.getCategory());
    }

    @Test
    //test di setMostriUccisi e getMostriUccisi
    void testSetAndGetMostriUccisi() {
        player.setMostri_uccisi(5);
        assertEquals(5, player.getMostriuccisi());
        //setMostri_uccisi con parametro 0 incrementa di 1 il numero di mostri uccisi, ci aspettiamo di avere 6 mostri uccisi dopo aver chiamato la funzione
        player.setMostri_uccisi(0);
        assertEquals(6, player.getMostriuccisi());
    }
}