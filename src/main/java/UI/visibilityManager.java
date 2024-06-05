package UI;

/**
 * Classe che gestisce la visibilità o meno di certi componenti nella user interface
 * permette di cambiare da una schermata ad un altra
 */
public class visibilityManager {

    UI ui;

    /**
     * Costruttore della classe visibilityManager
     * @param userInterface  oggetto della classe UI usato nel MainGame per la gestione delle schermate
     */
    public visibilityManager(UI userInterface) {
        ui = userInterface;
    }

    /**
     * Metodo per gestire la schermata di menu
     * mostrati solo titleNamePanel,menuButtonPanel
     */
    public void showMenuScreen(){
        //componenti visibili
        ui.titleNamePanel.setVisible(true);
        ui.menuButtonPanel.setVisible(true);

        //componenti di altre pagine non visibili
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
    /**
     * Metodo per gestire la schermata di selezione del personaggio
     * mostrati titleNamePanel,mainTextPanel,mainTextFieldPanel,mainCharacterSelectionPanel
     */
    public void showStartScreen(){
        //componenti visibili
        ui.titleNamePanel.setVisible(true);
        ui.mainTextPanel.setVisible(true);
        ui.mainTextFieldPanel.setVisible(true);
        ui.mainCharacterSelectionPanel.setVisible(true);


        //componenti di altre pagine non visibili
        ui.menuButtonPanel.setVisible(false);
        ui.commandPanel.setVisible(false);
        ui.commandLoadTextField.setVisible(false);
        ui.loadTextFieldPanel.setVisible(false);
        ui.mapPanel.setVisible(false);
        ui.statPanel.setVisible(false);
        ui.messageTextPanel.setVisible(false);



    }
    /**
     * Metodo per gestire la schermata di gioco
     * mostrati statPanel,messageTextPanel,commandPanel,mapPanel
     */
    public void showGameScreen(){
        //componenti visibili
        ui.statPanel.setVisible(true);
        ui.messageTextPanel.setVisible(true);
        ui.commandPanel.setVisible(true);
        ui.mapPanel.setVisible(true);

        //componenti di altre pagine non visibili
        ui.loadTextFieldPanel.setVisible(false);
        ui.commandLoadTextField.setVisible(false);
        ui.titleNamePanel.setVisible(false);
        ui.menuButtonPanel.setVisible(false);
        ui.mainTextPanel.setVisible(false);
        ui.mainTextFieldPanel.setVisible(false);
        ui.mainCharacterSelectionPanel.setVisible(false);
    }
    /**
     * Metodo per gestire la schermata di caricamento
     * mostrati loadMessagePanel,commandLoadTextField,loadTextFieldPanel
     */
    public void showLoadScreen(){
        //componenti visibili
        ui.loadMessagePanel.setVisible(true);
        ui.commandLoadTextField.setVisible(true);
        ui.loadTextFieldPanel.setVisible(true);


        //componenti di altre pagine non visibili
        ui.menuButtonPanel.setVisible(false);
        ui.titleNamePanel.setVisible(false);
        ui.commandPanel.setVisible(false);
        ui.mapPanel.setVisible(false);
        ui.mainTextPanel.setVisible(false);
        ui.mainTextFieldPanel.setVisible(false);
        ui.mainCharacterSelectionPanel.setVisible(false);
        ui.statPanel.setVisible(false);
        ui.messageTextPanel.setVisible(false);
    }


}
