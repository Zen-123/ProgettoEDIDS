import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import Board.Board;
import Board.Readfile;
import Board.reference;
import Player.Player;
import Player.mostro;
import UI.UI;

public class MainGameTest {

    private MainGame mainGame;

    @Before
    public void setUp() {
        // Resetta le variabili statiche prima di ogni test
        reference.player = null;
        reference.filereader = null;
        reference.mostrorun = null;
        reference.currentStanza = null;
        reference.ui = new UI();
    }

    @Test
    public void testMainGameConstructor() {
        reference.player = new Player();
        mainGame = new MainGame();

        assertNotNull(reference.ui);
        //verifica esistenza di questi oggetti
        assertNotNull(mainGame.manager);
        assertNotNull(mainGame.handler);
    }

    @Test
    //test per verificare se classi principali di gioco sono state inizializzate
    public void testInitialGame() {
        MainGame.initialGame();


        //verifica che oggetti principali di reference esistono
        assertNotNull(reference.player);
        assertTrue(reference.player instanceof Player);

        assertNotNull(reference.filereader);
        assertTrue(reference.filereader instanceof Readfile);

        assertNotNull(reference.mostrorun);
        assertTrue(reference.mostrorun instanceof mostro);

        assertNotNull(reference.currentStanza);
        assertTrue(reference.currentStanza instanceof Board);
    }

    @Test
    //test di classe main per avvio di gioco
    public void testMain() {
        MainGame.main(new String[]{});

        //verifica che tutti gli oggetti di reference esistano
        assertNotNull(reference.player);
        assertNotNull(reference.filereader);
        assertNotNull(reference.mostrorun);
        assertNotNull(reference.currentStanza);
    }
}