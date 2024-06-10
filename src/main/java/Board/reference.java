package Board;

import java.util.ArrayList;
import Player.Item;
import Player.Player;
import Player.mostro;
import UI.UI;

//variabili globali x gestione metodi
public class reference {
    //set variabili per gestione gioco player,lettura file e ui=il pannello di gioco
    public static Player player;
    public static UI ui = new UI();
    public static Readfile filereader;
    //var temporali x gestione metodi
    public static Item item;
    public static mostro mostro;
    //array di stanze serve x mantenere parametri delle stanze gia completate se si ritorna
    public static ArrayList<Board> lista_stanze = new ArrayList<Board>();
    //stanza momentaneamente presente e id della astanza
    public static Board currentStanza;
    public static int curr_stanza = 1;

    //variabili di controllo x fare check se si è stati gia in una stanza o meno(se si load dei parametri vecchi)
    public static boolean alreadybeen = false;
    public static boolean startGame = false;
    
}
