package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class choiceHandler implements ActionListener {

    UI userInterfaceHandler;
    /*
    Problema che potrebbe capitare in futuro:
    Importare sempre la UI che si sta usando definendola nel costruttore, altrimenti ne viene creata una nuova che
    non contiene niente e da errore
    */
    public choiceHandler(UI ui){
        userInterfaceHandler = ui;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String choice = e.getActionCommand();
        visibilityManager vManager = new visibilityManager(userInterfaceHandler);
        switch (choice){
            case "Start":
                vManager.showStartScreen();
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }

}


