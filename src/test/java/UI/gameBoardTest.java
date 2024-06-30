package UI;
import Player.mostro;
import Board.Board;
import  Board.Cell;
import Board.reference;
import Player.Player;
import org.junit.Before;
import org.junit.Test;
import Board.Readfile;
import javax.swing.*;

import java.awt.event.KeyEvent;

import static Board.reference.player;
import static Board.reference.ui;
import static org.junit.Assert.*;

public class gameBoardTest {

    private gameBoard board;

    @Before
    //setting di tutti gli oggetti usati per il testing
    public void setUp() {
        reference.ui.commandTextField = new JTextField();
        board = new gameBoard();
        reference.filereader = new Readfile();
        reference.player = new Player();

        //creazione di tutte le label e panel per evitare lancio di eccezioni
        ui.counterLoadLabel = new JLabel();
        ui.counterLoadLabel = new JLabel();
        ui.loadLabel1 = new JLabel();
        ui.loadLabel2 = new JLabel();
        ui.loadLabel3 = new JLabel();
        ui.loadLabel4 = new JLabel();
        ui.titleNamePanel = new JPanel();
        ui.menuButtonPanel =  new JPanel();
        ui.loadTextFieldPanel = new JPanel();
        reference.ui.commandTextField =  new JTextField();
        ui.commandTextField = new JTextField();
        ui.loadMessagePanel =  new JPanel();
        ui.messageTextArea = new JTextArea();
        ui.commandTextField = new JTextField();


    }

    @Test
    //test del metodo CheckWhatYouBumped considerando il caso di un muro
    public void testCheckWhatYouBumped_Wall() {
        reference.currentStanza = new Board(1);
        //si va a settare nella cella (0,0) il muro con simbolo #
        reference.currentStanza.setSsymbol(0, 0, '#');

        //il metodo CheckWhatYouBumped restituisce false quando si incontra un muro, ci aspettiamo un valore booleano false
        assertFalse(reference.functions.checkwhatyoubumped(0, 0));
    }

    @Test
    //test del metodo CheckWhatYouBumped nel caso si incontri uno spazio libero
    public void testCheckWhatYouBumped_FreeSpace() {

        reference.currentStanza = new Board(1);
        reference.currentStanza.setSsymbol(0, 0, '.');
        //il metodo CheckWhatYouBumped restituisce true quando si trova spazio libero, segnalando che il giocatore può muoversi in quella casella
        assertTrue(reference.functions.checkwhatyoubumped(0, 0));
    }

    @Test
    //test del metodo CheckWhatYouBumpe nel caso si incontri una moneta
    public void testCheckWhatYouBumped_Coin() {
        reference.currentStanza = new Board(1);
        reference.currentStanza.setSsymbol(0, 0, 'C');
        reference.player = new Player();
        int initialCoins = reference.player.getMonete();
        //il metodo CheckWhatYouBumped restituisce true quando si trova  una moneta, segnalando che il giocatore può raccogliere la moneta e avanzare nella casella
        assertTrue(reference.functions.checkwhatyoubumped(0, 0));
        //una volta raccolta la moneta ci aspettiamo che il giocatore aumenti il proprio numero di monete
        assertTrue(reference.player.getMonete() >= initialCoins);
    }

    @Test
    //test del metodo changeRoom che permette al giocatore di cambiare stanza una volta superata una porta
    public void testChangeRoom() {
        reference.currentStanza = new Board(1);
        reference.currentStanza.setDrive_to_N(2);
        reference.player = new Player();
        //chiamata del metodo che si vuole testare
        reference.functions.changeRoomAndWriteToFile(2);
        //ci aspettiamo che la stanza corrente in cui si trova il giocatore sia quella settata dal metodo changeRoom
        assertEquals(2, reference.currentStanza.getid());
    }

    @Test
    //test del metodo monsterEncounter
    public void testMonsterEncounter() {
        reference.player = new Player();
        reference.player.setVita(100);
        mostro monster = new mostro();
        monster.setVita(50);
        monster.setDanno_max(10);
        monster.setDanno_min(5);

        //chiamate al metodo da testare, con parametri personalizzati
        reference.functions.monsterEncounter(20, monster, false);

        //ci aspettiamo che dopo che il giocatore ha incontrato il mostro, subisca dei danni generati in modo casuale
        assertTrue(reference.player.getVita() <= 100);
        //ci aspettiamo che dopo che il giocatore attacca il mostro, il mostro dovrebbe aver subito dei danni
        assertTrue(monster.getVita() <= 50);
    }
}