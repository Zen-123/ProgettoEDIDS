package UI;

import Board.reference;
import static org.junit.jupiter.api.Assertions.*;

import Player.Player;
import org.junit.jupiter.api.*;

import javax.swing.*;
import java.awt.*;

public class visibilityManagerTest {

    private UI ui;
    private visibilityManager visibilityManager;

    @BeforeEach
    //setting di tutti i componenti usati per evitare lancio di eccezioni
    public void setUp() {
        ui = new UI();

        ui.titleNamePanel = new JPanel();
        ui.menuButtonPanel = new JPanel();
        ui.loadTextFieldPanel = new JPanel();
        ui.commandLoadTextField = new JTextField();
        ui.loadMessagePanel = new JPanel();
        ui.commandPanel = new JPanel();
        ui.mapPanel = new JPanel();
        ui.mainTextPanel = new JPanel();
        ui.mainTextFieldPanel = new JPanel();
        ui.mainCharacterSelectionPanel = new JPanel();
        ui.statPanel = new JPanel();
        ui.messageTextPanel = new JPanel();
        ui.winPanel = new JPanel();
        ui.textField = new JTextField();
        ui.mainCharacterButtonPanel = new ButtonGroup();

        visibilityManager = new visibilityManager(ui);
    }

    @Test
    //testing per verificare se viene mostrato correttamente la finestra di menu
    public void testShowMenuScreen() {
        visibilityManager.showMenuScreen();

        assertTrue(ui.titleNamePanel.isVisible());
        assertTrue(ui.menuButtonPanel.isVisible());

        assertFalse(ui.loadTextFieldPanel.isVisible());
        assertFalse(ui.commandLoadTextField.isVisible());
        assertFalse(ui.loadMessagePanel.isVisible());
        assertFalse(ui.commandPanel.isVisible());
        assertFalse(ui.mapPanel.isVisible());
        assertFalse(ui.mainTextPanel.isVisible());
        assertFalse(ui.mainTextFieldPanel.isVisible());
        assertFalse(ui.mainCharacterSelectionPanel.isVisible());
        assertFalse(ui.statPanel.isVisible());
        assertFalse(ui.messageTextPanel.isVisible());
        assertFalse(ui.winPanel.isVisible());
    }

    @Test
    //testing per verificare se viene mostrato correttamente la finestra di selezione del personaggio
    public void testShowStartScreen() {
        visibilityManager.showStartScreen();

        assertTrue(ui.titleNamePanel.isVisible());
        assertTrue(ui.mainTextPanel.isVisible());
        assertTrue(ui.mainTextFieldPanel.isVisible());
        assertTrue(ui.textField.isVisible());
        assertTrue(ui.mainCharacterSelectionPanel.isVisible());

        assertFalse(ui.menuButtonPanel.isVisible());
        assertFalse(ui.commandPanel.isVisible());
        assertFalse(ui.commandLoadTextField.isVisible());
        assertFalse(ui.loadTextFieldPanel.isVisible());
        assertFalse(ui.mapPanel.isVisible());
        assertFalse(ui.statPanel.isVisible());
        assertFalse(ui.messageTextPanel.isVisible());
        assertFalse(ui.winPanel.isVisible());
    }

    @Test
    //testing per verificare se viene mostrato correttamente la finestra di gioco
    public void testShowGameScreen() {
        //test dati già inseriti
        visibilityManager.checkLoad = true;
        reference.player = new Player();
        reference.player.setNome("test");
        reference.player.setCategory("warrior");
        visibilityManager.showGameScreen();
        assertTrue(ui.statPanel.isVisible());
        assertTrue(ui.messageTextPanel.isVisible());
        assertTrue(ui.commandPanel.isVisible());
        assertTrue(ui.mapPanel.isVisible());

        assertFalse(ui.loadTextFieldPanel.isVisible());
        assertFalse(ui.commandLoadTextField.isVisible());
        assertFalse(ui.titleNamePanel.isVisible());
        assertFalse(ui.menuButtonPanel.isVisible());
        assertFalse(ui.mainTextPanel.isVisible());
        assertFalse(ui.mainTextFieldPanel.isVisible());
        assertFalse(ui.mainCharacterSelectionPanel.isVisible());
        assertFalse(ui.winPanel.isVisible());
    }

    @Test
    //testing per verificare se viene mostrato correttamente la finestra di load dei salvataggi
    public void testShowLoadScreen() {
        visibilityManager.showLoadScreen();

        assertTrue(ui.loadMessagePanel.isVisible());
        assertTrue(ui.commandLoadTextField.isVisible());
        assertTrue(ui.loadTextFieldPanel.isVisible());

        assertFalse(ui.menuButtonPanel.isVisible());
        assertFalse(ui.titleNamePanel.isVisible());
        assertFalse(ui.commandPanel.isVisible());
        assertFalse(ui.mapPanel.isVisible());
        assertFalse(ui.mainTextPanel.isVisible());
        assertFalse(ui.mainTextFieldPanel.isVisible());
        assertFalse(ui.mainCharacterSelectionPanel.isVisible());
        assertFalse(ui.statPanel.isVisible());
        assertFalse(ui.messageTextPanel.isVisible());
        assertFalse(ui.winPanel.isVisible());
    }


}
