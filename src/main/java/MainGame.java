import UI.UI;
import UI.visibilityManager;
import UI.choiceHandler;

/**
 * Classe MainGame che permette di far partire il software
 * Utilizza altre classi presenti nel pacchetto UI per generare la user interface
 */

public class MainGame {
    private UI ui = new UI();
    private visibilityManager manager = new visibilityManager(ui);
    private choiceHandler handler = new choiceHandler(ui);

    /**
     * Funzione main per fare partire il gioco.
     * Costruisce un oggetto MainGame
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new MainGame();
    }

    /**
     * Costruttore della classe MainGame
     * permette di settare la user interface e il menu di gioco, inoltre scarica tutti i salvataggi presenti su aws
     */
    public MainGame(){
        ui.createUI();
        manager.showMenuScreen();
        handler.setLoad();
    }


}

