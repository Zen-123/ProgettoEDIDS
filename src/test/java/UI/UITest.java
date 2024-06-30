package UI;

import static org.junit.Assert.*;

import Board.Board;
import Board.reference;
import Board.Readfile;
import Player.Player;
import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import javax.swing.*;

public class UITest {

    private UI ui;

    @Before
    //setting di tutti i componenti usati per il testing
    public void setUp() {
        ui = new UI();
        reference.ui.commandTextField = new JTextField();
        reference.player = new Player();
        reference.filereader = new Readfile();
        reference.currentStanza = new Board(1);
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

        ui.createUI();
    }

    @Test
    //testing della creazione della window effettiva
    public void testWindowCreation() {
        assertNotNull(ui.window);
        assertEquals(800, ui.window.getSize().width);
        assertEquals(600, ui.window.getSize().height);
        assertEquals(Color.black, ui.window.getContentPane().getBackground());
    }

    @Test
    //testing creazione del titolo di gioco
    public void testTitleCreation() {
        assertNotNull(ui.titleNamePanel);
        assertEquals("DUNGEON UNIPD", ((JLabel)ui.titleNamePanel.getComponent(0)).getText());
    }

    @Test
    //testing della creazione dei 3 bottoni del menu di gioco
    public void testMenuButtonCreation() {
        assertNotNull(ui.menuButtonPanel);
        assertEquals(3, ui.menuButtonPanel.getComponentCount());
        assertTrue(ui.menuButtonPanel.getComponent(0) instanceof JButton);
        assertTrue(ui.menuButtonPanel.getComponent(1) instanceof JButton);
        assertTrue(ui.menuButtonPanel.getComponent(2) instanceof JButton);
    }

    @Test
    //testing creazione dei 3 menu di selezione del personaggio
    public void testCharacterSelectionCreation() {
        assertNotNull(ui.mainCharacterSelectionPanel);
        assertTrue(ui.mainCharacterSelectionPanel.getComponent(1) instanceof JRadioButton);
        assertTrue(ui.mainCharacterSelectionPanel.getComponent(2) instanceof JRadioButton);
        assertTrue(ui.mainCharacterSelectionPanel.getComponent(3) instanceof JRadioButton);
    }

    @Test
    //testing creazione finestra di gioco
    public void testGameScreenCreation() {
        assertNotNull(ui.statPanel);
        assertNotNull(ui.messageTextPanel);
        assertNotNull(ui.mapPanel);
    }

    @Test
    //testing creazione finestra di load file
    public void testLoadScreenCreation() {
        assertNotNull(ui.loadMessagePanel);
        assertEquals(4, ui.loadMessagePanel.getComponentCount());
    }

    @Test
    //testing fineste di alert durante il salvataggio del gioco
    public void testAlertMenu() {
        assertEquals(-1, ui.setAlertMenu(1));

    }
}