import Board.Board;
import Board.Readfile;
import Board.reference;
import Player.Player;
import UI.visibilityManager;

public class MainGame {

    visibilityManager manager = new visibilityManager(reference.ui);

    public MainGame(){

        reference.ui.createUI();
        manager.showMenuScreen();

    }
    public static void main(String[] args) {
        //inizio gioco
        initialGame();
        new MainGame();
    }
    //assegnazione var globali
    private static void initialGame(){
        reference.player = new Player();
        reference.filereader = new Readfile();
        reference.currentStanza = new Board(reference.curr_stanza);
    }
}
