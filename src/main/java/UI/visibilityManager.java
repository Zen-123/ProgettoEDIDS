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
    }
    public void showStartScreen(){

        ui.titleNamePanel.setVisible(false);
        ui.menuButtonPanel.setVisible(false);

        ui.mainTextPanel.setVisible(true);
        ui.mainTextFieldPanel.setVisible(true);
    }


}
