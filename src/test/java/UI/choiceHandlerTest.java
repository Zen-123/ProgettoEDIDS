package UI;


import Board.reference;
import Player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class choiceHandlerTest {

    private choiceHandler choiceHandler;
    private UI ui;

    @TempDir
    Path tempDir;

    @BeforeEach
    //creazione degli oggetti usati durante il testing per evitare lancio di eccezioni
    void setUp() {
        ui = new UI();
        choiceHandler = new choiceHandler(ui);
        reference.player = new Player();
        //si vanno a creare tutte le label e i panel per interfaccia UI per evitare il lancio di eccezioni
        reference.ui.counterLoadLabel = new JLabel();
        ui.counterLoadLabel = new JLabel();
        ui.loadLabel1 = new JLabel();
        ui.loadLabel2 = new JLabel();
        ui.loadLabel3 = new JLabel();
        ui.loadLabel4 = new JLabel();
        ui.titleNamePanel = new JPanel();
        ui.menuButtonPanel =  new JPanel();
        ui.loadTextFieldPanel = new JPanel();
        ui.commandLoadTextField = new JTextField();
        ui.loadMessagePanel =  new JPanel();
        this.ui.commandPanel = new JPanel();
        this.ui.mapPanel = new JPanel();
        this.ui.mainTextPanel = new JPanel();
        this.ui.mainTextFieldPanel = new JPanel();
        this.ui.mainCharacterSelectionPanel = new JPanel();
        this.ui.statPanel = new JPanel();
        this.ui.messageTextPanel = new JPanel();
        this.ui.mainCharacterButtonPanel = new ButtonGroup();
        this.ui.textField = new JTextField();

        this.ui.winPanel = new JPanel();

    }
    @Test
        //test metodo manageTextInputSav per salvare la partita e i dati del giocatore quando il giocatore usa il comando "save"
    void testManageTextInputSave() throws IOException {
        // si usa per il testing il file test.txt
        File tempFile = new File("FileLoad/test.txt");
        System.setProperty("user.dir", tempDir.toString());

        //chiamata alla funzione da testare
        choiceHandler.manageTextInput("save");

        // verifica se file da caricare sul bucket aws esiste
        assertTrue(tempFile.exists());
        String fileContent = Files.readString(tempFile.toPath());
        //verifica se sono presenti i dati del player
        assertTrue(fileContent.contains("Player: "));
        assertTrue(fileContent.contains("Health: " + reference.player.getVita()));
        assertTrue(fileContent.contains("Money: " + reference.player.getMonete()));
        assertTrue(fileContent.contains("Monster_killed: " + reference.player.getMostriuccisi()));
        assertTrue(fileContent.contains("Category: "));
        assertTrue(fileContent.contains("Weapon:"));
        assertTrue(fileContent.contains("Potions: " + reference.player.getNumpozioni()));
        assertTrue(fileContent.contains("Weight_Inventory: "));
        assertTrue(fileContent.contains("Armour: "));
        assertTrue(fileContent.contains("key: " + reference.player.getKey()));
        assertTrue(fileContent.contains("Max_damage: " ));
        assertTrue(fileContent.contains("Min_damage: "));

    }


    @Test
    //test di metodo fileSave che gestisce il salvataggio effettivo della partita
    void testSetFileSave() throws IOException {
        // si considera lo stesso file .txt usato per il precedente unit test
        String fileName = "test.txt";
        File tempFile = new File("FileLoad/test.txt");

        // chiamata al metodo effettivo
        choiceHandler.setFileSave(fileName);

        // verifica se metodo è stato eseguito correttamente
        assertTrue(tempFile.exists());
        String fileContent = Files.readString(tempFile.toPath());
        assertTrue(fileContent.contains("Player: " + reference.player.getNome()));
        assertTrue(fileContent.contains("Health: " + reference.player.getVita()));
        assertTrue(fileContent.contains("Money: " + reference.player.getMonete()));
        assertTrue(fileContent.contains("Monster_killed: " + reference.player.getMostriuccisi()));
        assertTrue(fileContent.contains("Category: " + reference.player.getCategory()));
        assertTrue(fileContent.contains("Weapon: " + reference.player.getSpadaName()));
        assertTrue(fileContent.contains("Potions: " + reference.player.getNumpozioni()));
        assertTrue(fileContent.contains("Weight_Inventory: " + reference.player.getPeso()));
        assertTrue(fileContent.contains("Armour: " + reference.player.getArmourName()));
        assertTrue(fileContent.contains("key: " + reference.player.getKey()));
        assertTrue(fileContent.contains("Max_damage: " + reference.player.getDannoMaxSpada()));
        assertTrue(fileContent.contains("Min_damage: " + reference.player.getDannoMinSpada()));
    }

    @Test
    //test del metodo setLoad
    void testSetLoad() throws IOException {
        // setting degli oggetti usati per caricare il file
        File tempFile = tempDir.resolve("Filesave 1.txt").toFile();
        Files.writeString(tempFile.toPath(), "Test content");
        System.setProperty("user.dir", tempDir.toString());

        // chiamata alla funzione effettiva
        choiceHandler.setLoad();


        assertEquals("Save n. 2", ui.counterLoadLabel.getText(), "dato che abbiamo chiamato la funzione SetFileSave 2 volte ci aspettiamo di avere 2 save ");

    }
}