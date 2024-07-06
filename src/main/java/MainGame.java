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
 * Classe MainGame che permette di avviare il software.
 * Utilizza altre classi presenti nel pacchetto UI per generare l'interfaccia utente.
 */

public class MainGame {
    visibilityManager manager = new visibilityManager(reference.ui);
    choiceHandler handler = new choiceHandler(reference.ui);

    /**
     * Metodo principale che avvia il gioco.
     * @param args Argomenti della linea di comando (non utilizzati).
     */
    public static void main(String[] args) {
        //inizio gioco
        initialGame();
        new MainGame();
    }

    /**
     * Costruttore della classe MainGame.
     * Configura l'interfaccia utente e il menu di gioco.
     */
    public MainGame(){
        reference.ui.createUI();
        manager.showMenuScreen();

    }

    /**
     * Inizializza le variabili globali del gioco.
     * Crea nuove istanze per il giocatore, il lettore di file, il mostro,
     * la stanza corrente e le funzioni di utilità.
     * Inoltre, reimposta la directory di lavoro.
     */
    public static void initialGame(){
        reference.player = new Player();
        reference.filereader = new Readfile();
        reference.mostrorun = new mostro();
        reference.currentStanza = new Board(reference.curr_stanza);
        reference.functions = new func();
        reference.filereader.ResetDirectory();
    }
}
