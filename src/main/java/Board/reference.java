package Board;

import java.util.ArrayList;
import Player.Item;
import Player.Player;
import Player.mostro;
import UI.UI;

/**
 * Classe che contiene variabili globali per la gestione dei metodi e dello stato del gioco.
 */
public class reference {

    /** Il giocatore attuale. */
    public static Player player;

    /** L'interfaccia utente del gioco. */
    public static UI ui = new UI();

    /** Oggetto per la lettura dei file. */
    public static Readfile filereader;

    /** Item presente in gioco */
    public static Item item;

    /** Mostro presente in gioco. */
    public static mostro mostro;

    /** Mostro che esegue azione di run   */
    public static mostro mostrorun;

    /** Lista delle stanze già visitate, per mantenere i parametri delle stanze completate. */
    public static ArrayList<Board> lista_stanze = new ArrayList<Board>();

    /** La stanza attuale in cui si trova il giocatore. */
    public static Board currentStanza;

    /** L'ID della stanza attuale. */
    public static int curr_stanza = 1;

    /** Oggetto che gestisce tutti i metodi implementati per lo sviluppo e le interazioni di gioco. */
    public static func functions;

    /** Flag che indica se il giocatore è già stato in una stanza. */
    public static boolean alreadybeen = false;

    /** Flag che indica se il gioco è iniziato. */
    public static boolean startGame = false;
}