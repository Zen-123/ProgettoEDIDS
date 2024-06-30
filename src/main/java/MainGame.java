import Board.Board;
import Board.Readfile;
import Board.reference;
import Player.Player;
import Player.mostro;
import UI.visibilityManager;
import UI.choiceHandler;
import UI.UI;
import Board.func;

/**
 * Classe MainGame che permette di far partire il software
 * Utilizza altre classi presenti nel pacchetto UI per generare la user interface
 */

public class MainGame {
    UI ui = new UI();
    visibilityManager manager = new visibilityManager(reference.ui);
    choiceHandler handler = new choiceHandler(reference.ui);


    public static void main(String[] args) {
        //inizio gioco
        initialGame();
        new MainGame();
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
        reference.functions = new func();
        reference.filereader.ResetDirectory();
    }
}
