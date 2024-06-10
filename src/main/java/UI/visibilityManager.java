package UI;

import Board.reference;
import Player.Item;

public class visibilityManager {

    UI ui;

    public visibilityManager(UI userInterface) {
        ui = userInterface;
    }

    public void showMenuScreen(){
        ui.titleNamePanel.setVisible(true);
        ui.menuButtonPanel.setVisible(true);

        ui.commandPanel.setVisible(false);
        ui.mapPanel.setVisible(false);
        ui.mainTextPanel.setVisible(false);
        ui.mainTextFieldPanel.setVisible(false);
        ui.mainCharacterSelectionPanel.setVisible(false);
        ui.statPanel.setVisible(false);
        ui.messageTextPanel.setVisible(false);

    }
    public void showStartScreen(){
        ui.menuButtonPanel.setVisible(false);
        ui.commandPanel.setVisible(false);

        ui.mapPanel.setVisible(false);
        ui.titleNamePanel.setVisible(true);
        ui.mainTextPanel.setVisible(true);
        ui.mainTextFieldPanel.setVisible(true);
        ui.mainCharacterSelectionPanel.setVisible(true);
        ui.statPanel.setVisible(false);
        ui.messageTextPanel.setVisible(false);



    }

    public void showGameScreen(){
        ui.statPanel.setVisible(true);
        ui.messageTextPanel.setVisible(true);
        ui.commandPanel.setVisible(true);
        ui.mapPanel.setVisible(true);

        ui.titleNamePanel.setVisible(false);
        ui.menuButtonPanel.setVisible(false);
        ui.mainTextPanel.setVisible(false);
        ui.mainTextFieldPanel.setVisible(false);
        ui.mainCharacterSelectionPanel.setVisible(false);

        //riemptio giocatore con tutti i dati prelevati dal menu di selezione personaggio
        switch(ui.mainCharacterButtonPanel.getSelection().getActionCommand()) {
            case "warrior":
                reference.player.setMonete(0);
                reference.player.addNum_pozioni(0);
                reference.player.addSpada( new Item("spada",13,5,0,true,reference.curr_stanza,true));
                reference.player.addArmour(new Item("armatura",0,0,5,false,reference.curr_stanza,true));
                reference.player.setHasArmour(true);
                break;
            case "archer":
                reference.player.setMonete(10);
                reference.player.addNum_pozioni(2);
                reference.player.addSpada( new Item("spada",10,3,0,true,reference.curr_stanza,true));
                reference.player.addArmour(new Item("armatura",0,0,1,false,reference.curr_stanza,true));
                reference.player.setHasArmour(true);
                break;
            case "thief":
                reference.player.setMonete(20);
                reference.player.addNum_pozioni(3);
                reference.player.setKey();
                reference.player.addSpada(new Item("spada",8,4,0,true,reference.curr_stanza,true));
                reference.player.addArmour(new Item("armatura",0,0,0,false,reference.curr_stanza,true));
                reference.player.setHasArmour(true);
                break;
            default:
                break;
        }
        reference.player.setCategory(ui.mainCharacterButtonPanel.getSelection().getActionCommand());
        reference.player.setNome(ui.textField.getText());
    }
}
