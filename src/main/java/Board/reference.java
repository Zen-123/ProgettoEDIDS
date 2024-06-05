package Board;

import java.util.ArrayList;

import Player.Item;
import Player.Player;

//variabili globali x gestione metodi
public class reference {
    public static Player player;

    public static Item item;

    public static ArrayList<Board> lista_stanze = new ArrayList<Board>();
    public static Board currentStanza;
    public static int curr_stanza = 1;
    public static boolean alreadybeen = false;
    public static Readfile filereader;
}
