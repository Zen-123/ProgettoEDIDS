package UI;

public class visibilityManager {

    UI ui;

    public visibilityManager(UI userInterface) {
        ui = userInterface;
    }

    public void showMenuScreen(){
        ui.titleNamePanel.setVisible(true);
        ui.menuButtonPanel.setVisible(true);

        ui.mainTextPanel.setVisible(false);
        ui.mainTextFieldPanel.setVisible(false);
        ui.mainCharacterSelectionPanel.setVisible(false);
    }
    public void showStartScreen(){
        ui.menuButtonPanel.setVisible(false);

        ui.titleNamePanel.setVisible(true);
        ui.mainTextPanel.setVisible(true);
        ui.mainTextFieldPanel.setVisible(true);
        ui.mainCharacterSelectionPanel.setVisible(true);

    }


}
