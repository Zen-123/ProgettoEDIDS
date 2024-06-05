package UI;

public class visibilityManager {

    UI ui;

    public visibilityManager(UI userInterface) {
        ui = userInterface;
    }

    public void showMenuScreen(){
        ui.titleNamePanel.setVisible(true);
        ui.menuButtonPanel.setVisible(true);

        ui.loadTextFieldPanel.setVisible(false);
        ui.commandLoadTextField.setVisible(false);
        ui.loadMessagePanel.setVisible(false);
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

        ui.commandLoadTextField.setVisible(false);
        ui.loadTextFieldPanel.setVisible(false);

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
        ui.loadTextFieldPanel.setVisible(false);

        ui.commandLoadTextField.setVisible(false);

        ui.titleNamePanel.setVisible(false);
        ui.menuButtonPanel.setVisible(false);
        ui.mainTextPanel.setVisible(false);
        ui.mainTextFieldPanel.setVisible(false);
        ui.mainCharacterSelectionPanel.setVisible(false);
    }

    public void showLoadScreen(){
        ui.loadMessagePanel.setVisible(true);
        ui.titleNamePanel.setVisible(false);
        ui.menuButtonPanel.setVisible(false);
        ui.commandLoadTextField.setVisible(true);
        ui.loadTextFieldPanel.setVisible(true);

        ui.commandPanel.setVisible(false);
        ui.mapPanel.setVisible(false);
        ui.mainTextPanel.setVisible(false);
        ui.mainTextFieldPanel.setVisible(false);
        ui.mainCharacterSelectionPanel.setVisible(false);
        ui.statPanel.setVisible(false);
        ui.messageTextPanel.setVisible(false);
    }


}
