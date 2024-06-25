import Board.Board;
import Board.Readfile;
import Board.reference;
import Player.Player;
import Player.mostro;
import UI.UI;
import UI.visibilityManager;
import UI.choiceHandler;

/**
 * Classe MainGame che permette di far partire il software
 * Utilizza altre classi presenti nel pacchetto UI per generare la user interface
 */

public class MainGame {
    private UI ui = new UI();
    private visibilityManager manager = new visibilityManager(reference.ui);
    private choiceHandler handler = new choiceHandler(reference.ui);

    /**
     * Funzione main per fare partire il gioco.
     * Costruisce un oggetto MainGame
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new MainGame();
        initialGame();

    }

    /**
     * Costruttore della classe MainGame
     * permette di settare la user interface e il menu di gioco, inoltre scarica tutti i salvataggi presenti su aws
     */
    public MainGame(){
        reference.ui.createUI();
        manager.showMenuScreen();
        handler.setLoad();
    }

    //assegnazione var globali
    private static void initialGame(){
        reference.player = new Player();
        reference.filereader = new Readfile();
        reference.mostrorun = new mostro();
        reference.currentStanza = new Board(reference.curr_stanza);
    }
}
