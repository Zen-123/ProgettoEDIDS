import Board.Board;
import Board.Readfile;
import Board.func;
import Board.reference;
import Player.Player;
import Player.mostro;
import UI.UI;
import UI.choiceHandler;
import UI.visibilityManager;



/**
 * Classe MainGame che permette di far partire il software
 * Utilizza altre classi presenti nel pacchetto UI per generare la user interface
 */

public class MainGame {
    UI ui = new UI();
    visibilityManager manager = new visibilityManager(reference.ui);
    choiceHandler handler = new choiceHandler(reference.ui);
//test update 2

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

    }

    //inizializzazione var globali
    public static void initialGame(){
        reference.player = new Player();
        reference.filereader = new Readfile();
        reference.mostrorun = new mostro();
        reference.currentStanza = new Board(reference.curr_stanza);
        reference.functions = new func();
        reference.filereader.ResetDirectory();
    }
}
