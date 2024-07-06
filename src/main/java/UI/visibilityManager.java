package UI;

import java.awt.Color;

import Board.reference;
import Player.Item;

/**
 * Classe che gestisce la visibilità dei componenti dell'interfaccia utente.
 * Permette di passare da una schermata all'altra del gioco.
 */
public class visibilityManager {

    /** Riferimento all'interfaccia utente principale. */
    UI ui;
    /** Flag per indicare se si sta caricando un gioco salvato. */
    public boolean checkLoad = false;
    /**
     * Costruttore della classe visibilityManager.
     *
     * @param userInterface Oggetto della classe UI usato nel MainGame per la gestione delle schermate.
     */
    public visibilityManager(UI userInterface) {
        ui = userInterface;

    }

    /**
     * Mostra la schermata del menu principale.
     * Rende visibili solo titleNamePanel e menuButtonPanel.
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
        ui.winPanel.setVisible(false);

    }
    /**
     * Mostra la schermata di selezione del personaggio.
     * Rende visibili titleNamePanel, mainTextPanel, mainTextFieldPanel e mainCharacterSelectionPanel.
     */
    public void showStartScreen(){
        //componenti visibili
        ui.titleNamePanel.setVisible(true);
        ui.mainTextPanel.setVisible(true);
        ui.mainTextFieldPanel.setVisible(true);
        ui.textField.setVisible(true);
        ui.textField.setText("");
        ui.textField.setSelectedTextColor(Color.darkGray);
        ui.mainCharacterSelectionPanel.setVisible(true);


        //componenti di altre pagine non visibili
        ui.menuButtonPanel.setVisible(false);
        ui.commandPanel.setVisible(false);
        ui.commandLoadTextField.setVisible(false);
        ui.loadTextFieldPanel.setVisible(false);
        ui.mapPanel.setVisible(false);
        ui.statPanel.setVisible(false);
        ui.messageTextPanel.setVisible(false);
        ui.winPanel.setVisible(false);



    }
    /**
     * Mostra la schermata di gioco principale.
     * Rende visibili statPanel, messageTextPanel, commandPanel e mapPanel.
     * Inizializza anche i dati del giocatore se si sta iniziando una nuova partita.
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
        ui.winPanel.setVisible(false);


        /*riemptio giocatore con tutti i dati prelevati dal menu di selezione personaggio
        checkLoad variabile booleana che serve per segnalare se si sta creando un nuovo personaggio oppure se si sta caricando un personaggio già esistente
        checkLoad = false se si crea un nuovo personaggio
        checkLoad = true se si usa un personaggio creato in precedenza
        */
            if(checkLoad == false) {
                try {
                    switch (ui.mainCharacterButtonPanel.getSelection().getActionCommand()) {
                        case "archer":
                            reference.player.setCategory("archer");
                            reference.player.setMonete(10);
                            reference.player.addNum_pozioni(2);
                            reference.player.addSpada(new Item("spada", 10, 3, 0, true, reference.curr_stanza, true,20));
                            reference.player.addArmour(new Item("armatura", 0, 0, 1, false, reference.curr_stanza, true,30));
                            reference.player.setHasArmour(true);
                            break;
                        case "thief":
                            reference.player.setCategory("thief");
                            reference.player.setMonete(20);
                            reference.player.addNum_pozioni(3);
                            reference.player.setKey();
                            reference.player.addSpada(new Item("spada", 8, 4, 0, true, reference.curr_stanza, true,20));
                            reference.player.addArmour(new Item("armatura", 0, 0, 0, false, reference.curr_stanza, true,30));
                            reference.player.setHasArmour(true);
                            break;
                        default:
                            reference.player.setCategory("warrior");
                            reference.player.setMonete(0);
                            reference.player.addNum_pozioni(0);
                            reference.player.addSpada(new Item("spada", 13, 5, 0, true, reference.curr_stanza, true,20));
                            reference.player.addArmour(new Item("armatura", 0, 0, 5, false, reference.curr_stanza, true,30));
                            reference.player.setHasArmour(true);
                            break;
                    }
                    reference.player.setCategory(ui.mainCharacterButtonPanel.getSelection().getActionCommand());
                    reference.player.setNome(ui.textField.getText());
                } catch (Exception e) {
                    System.out.println("Guarda che non hai selezionato una categoria: default warrior");
                    reference.player.setCategory("warrior");
                    reference.player.setMonete(0);
                    reference.player.setPeso(0);
                    reference.player.addNum_pozioni(0);
                    reference.player.addSpada(new Item("spada", 13, 5, 0, true, reference.curr_stanza, true,20));
                    reference.player.addArmour(new Item("armatura", 0, 0, 5, false, reference.curr_stanza, true,30));
                    reference.player.setHasArmour(true);
                }
            }

    }

    /**
     * Mostra la schermata di caricamento del gioco.
     * Rende visibili loadMessagePanel, commandLoadTextField e loadTextFieldPanel.
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
        ui.winPanel.setVisible(false);
    }
    /**
     * Mostra la schermata di fine gioco (vittoria o sconfitta).
     * Visualizza le statistiche finali del giocatore e il risultato del gioco.
     */
    public void showWinPanel(){
        ui.winPanel.setVisible(true);
        reference.ui.namePlayerLabel.setText("Player: " + reference.player.getNome());
        reference.ui.monsterLabel.setText("Monsters killed: " + reference.player.getMostriuccisi());
        reference.ui.moneyLabel.setText("Money: " + reference.player.getMonete());
        if(reference.player.getVita() <= 0){
            ui.winLabel.setText("YOU DIED!");
        }else{
            ui.winLabel.setText("YOU WIN!");
        }
        ui.messageTextArea.setText("");
        //componenti di altre pagine non visibili
        ui.loadMessagePanel.setVisible(false);
        ui.commandLoadTextField.setVisible(false);
        ui.loadTextFieldPanel.setVisible(false);
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