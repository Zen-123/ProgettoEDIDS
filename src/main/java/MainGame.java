import UI.UI;
import UI.visibilityManager;
import UI.choiceHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGame {

    UI ui = new UI();
    visibilityManager manager = new visibilityManager(ui);
    public static void main(String[] args) {

        new MainGame();
    }

    //Costruttore di classe
    public MainGame(){

        ui.createUI();
        manager.showMenuScreen();

    }

}
