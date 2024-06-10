package UI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalToggleButtonUI;


public class UI {
    JFrame window;
    JPanel titleNamePanel, menuButtonPanel,  mainTextPanel, mainTextFieldPanel, mainCharacterSelectionPanel, statPanel, messageTextPanel, commandPanel, mapPanel;
    JLabel titleNameLabel,mainTextArea, mainCharacterSelectionLabel, startGameLabel, statLabel, nameLabel, characterLabel, hpLabel, inventoryWeight, potionLabel, weaponLabel, moneyLabel, roomNumberLabel, commandLabel, southLabel, northLabel, eastLabel, westLabel;
    JButton startButton, loadButton, exitButton, startGameButton;
    JRadioButton warriorButton, archerButton, thiefButton;
    JTextArea messageTextArea;
    static gameBoard gameB;
    ButtonGroup mainCharacterButtonPanel;
    JTextField textField, commandTextField;
    Font titleFont = new Font("Serif", Font.PLAIN, 70);
    Font normalFont = new Font("Serif",Font.PLAIN, 26);
    choiceHandler handler = new choiceHandler(this);
    boolean goOn = true;

    public void createUI(){

        setWindow();
        setTitle();
        setMenuButtonPanel();
        setMainTextPanel();
        setTextField();
        setMainCharacterSelectionPanel();
        setGameScreenPanel();
        window.setVisible(true);



    }
    //Metodo che gestisce la finestra di gioco
    private void setWindow(){
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
    }
    //Metodo che gestisce il titolo del gioco
    private void setTitle(){
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100,50,600,100);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("DUNGEON UNIPD");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);
        titleNamePanel.add(titleNameLabel);
        window.add(titleNamePanel);
    }

    private void setMenuButtonPanel( ) {
        menuButtonPanel = new JPanel();
        menuButtonPanel.setBounds(250, 250, 300, 250);
        menuButtonPanel.setBackground(Color.black);
        menuButtonPanel.setLayout(new GridLayout(3,1));
        setStartButton();
        setLoadButton();
        setExitButton();
        window.add(menuButtonPanel);
    }

    private void setMainCharacterButtonPanel(){
        mainCharacterButtonPanel = new ButtonGroup();
        setWarriorButton();
        setArcherButton();
        setThiefButton();
        mainCharacterSelectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

    }

    //metodo che gestisce il bottone Start
    private void setStartButton(){
        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(normalFont);
        startButton.setFocusPainted(false);
        startButton.addActionListener(handler);
        startButton.setActionCommand("Start");

        startButton.addMouseListener( new MouseAdapter(){
            public void mouseEntered(MouseEvent evt) {
                startButton.setBackground(Color.blue);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                startButton.setBackground(Color.black);
            }
        });
        menuButtonPanel.add(startButton);


    }
    //button start del menu personaggi che ti porta nel pannello di gioco 
    private void setStartGameButton(){
        startGameButton = new JButton("START");
        startGameButton.setBackground(Color.black);
        startGameButton.setForeground(Color.WHITE);
        startGameButton.setFont(normalFont);
        startGameButton.setFocusPainted(false);
        startGameButton.addActionListener(handler);
        startGameButton.setActionCommand("Start game");
        startGameButton.addMouseListener( new MouseAdapter(){
            public void mouseEntered(MouseEvent evt) {
                startGameButton.setBackground(Color.blue);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                startGameButton.setBackground(Color.black);
            }
        });
        mainCharacterSelectionPanel.add(startGameButton);
    }
    //radio button guerriero
    private void setWarriorButton(){
        warriorButton = new JRadioButton("Warrior");
        warriorButton.setName("Warrior");
        warriorButton.setBackground(Color.black);
        warriorButton.setForeground(Color.WHITE);
        warriorButton.setFont(normalFont);
        warriorButton.setFocusPainted(false);
        warriorButton.addActionListener(handler);
        warriorButton.setActionCommand("warrior");

        warriorButton.addMouseListener( new MouseAdapter(){
            public void mouseEntered(MouseEvent evt) {
                warriorButton.setBackground(Color.blue);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                warriorButton.setBackground(Color.black);
            }
        });
        warriorButton.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return Color.darkGray;
            }
        });
        mainCharacterSelectionPanel.add(warriorButton);
        mainCharacterButtonPanel.add(warriorButton);

    }
    //radio button arciere
    private void setArcherButton(){
        archerButton = new JRadioButton("Archer");
        warriorButton.setName("Archer");
        archerButton.setBackground(Color.black);
        archerButton.setForeground(Color.WHITE);
        archerButton.setFont(normalFont);
        archerButton.setFocusPainted(false);
        archerButton.addActionListener(handler);
        archerButton.setActionCommand("archer");

        archerButton.addMouseListener( new MouseAdapter(){
            public void mouseEntered(MouseEvent evt) {
                archerButton.setBackground(Color.blue);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                archerButton.setBackground(Color.black);
            }
        });
        archerButton.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return Color.darkGray;
            }
        });
        mainCharacterSelectionPanel.add(archerButton);

        mainCharacterButtonPanel.add(archerButton);

    }
    //radio button ladro
    private void setThiefButton(){
        thiefButton = new JRadioButton("Thief");
        warriorButton.setName("Thief");
        thiefButton.setBackground(Color.black);
        thiefButton.setForeground(Color.WHITE);
        thiefButton.setFont(normalFont);
        thiefButton.setFocusPainted(false);
        thiefButton.addActionListener(handler);
        thiefButton.setActionCommand("thief");

        thiefButton.addMouseListener( new MouseAdapter(){
            public void mouseEntered(MouseEvent evt) {
                thiefButton.setBackground(Color.blue);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                thiefButton.setBackground(Color.black);
            }
        });
        thiefButton.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return Color.darkGray;
            }
        });
        mainCharacterSelectionPanel.add(thiefButton);
        mainCharacterButtonPanel.add(thiefButton);

    }
    //Metodo che gestice il bottone Exit
    private void setExitButton(){
        exitButton = new JButton("EXIT");
        exitButton.setBackground(Color.black);
        exitButton.setForeground(Color.white);
        exitButton.setFont(normalFont);
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(handler);
        exitButton.setActionCommand("Exit");
        exitButton.addMouseListener( new MouseAdapter(){
            public void mouseEntered(MouseEvent evt) {
                exitButton.setBackground(Color.blue);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButton.setBackground(Color.black);
            }
        });
        menuButtonPanel.add(exitButton);

    }
    private void setLoadButton(){
        loadButton = new JButton("LOAD");
        loadButton.setBackground(Color.black);
        loadButton.setForeground(Color.white);
        loadButton.setFont(normalFont);
        loadButton.setFocusPainted(false);
        loadButton.addActionListener(handler);
        loadButton.setActionCommand("Load");

        loadButton.addMouseListener( new MouseAdapter(){
            public void mouseEntered(MouseEvent evt) {
                loadButton.setBackground(Color.blue);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loadButton.setBackground(Color.black);
            }
        });
        menuButtonPanel.add(loadButton);

    }
    private void setTextField(){
        mainTextFieldPanel = new JPanel();
        mainTextFieldPanel.setName("namepanel");
        mainTextFieldPanel.setBounds(20, 250, 600, 50);
        mainTextFieldPanel.setBackground(Color.black);
        window.add(mainTextFieldPanel);

        Font textFont = new Font("SansSerif", Font.BOLD, 15);
        textField = new JTextField( 30);
        textField.setBackground(Color.darkGray);
        textField.setPreferredSize(new Dimension(100, 30));
        textField.setFont(textFont);
        textField.setForeground(Color.white);
        mainTextFieldPanel.add(textField);

    }
    private void setCommandPanel(){
        commandPanel = new JPanel();
        commandPanel.setBounds(-10, 350,560, 100);
        commandPanel.setBackground(Color.black);
        commandPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        commandLabel = new JLabel("Enter the command: ");
        commandLabel.setBorder(new EmptyBorder(10,20,0,0));
        commandLabel.setBackground(Color.black);
        commandLabel.setForeground(Color.white);
        commandLabel.setFont(normalFont);
        commandPanel.add(commandLabel);
        setCommandTextField();

        window.add(commandPanel);

    }

    // questa è la mappa dove è presente il gioco
    private void setMapPanel(){
        mapPanel = new JPanel();
        mapPanel.setVisible(true);
		// mapPanel.setResizable(false);
        mapPanel.setBounds(0,0,850,350);
        mapPanel.setBackground(Color.black);
        // mapPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        // mapPanel.setLayout(null);

        gameB = new gameBoard();
		mapPanel.add(gameB);
		gameB.requestFocusInWindow();
        
        /* setSouthLabel();
        setNorthLabel();
        setWestLabel();
        setEastLabel(); */
        window.getContentPane().add(mapPanel);
        mapPanel.setLayout(new GridLayout(1, 0, 0, 0));
        // window.add(mapPanel);
    }

    private void setSouthLabel(){
        southLabel = new JLabel("S");
        southLabel.setForeground(Color.white);
        southLabel.setBackground(Color.black);
        southLabel.setFont(normalFont);
        southLabel.setBounds(145,140,mapPanel.getWidth(),mapPanel.getHeight() );
        mapPanel.add(southLabel);
    }

    private void setNorthLabel(){
        northLabel = new JLabel("N");
        northLabel.setForeground(Color.white);
        northLabel.setBackground(Color.black);
        northLabel.setFont(normalFont);
        northLabel.setBounds(145,0,50,50);
        mapPanel.add(northLabel);
    }

    private void setWestLabel(){
        westLabel = new JLabel("W");
        westLabel.setForeground(Color.white);
        westLabel.setBackground(Color.black);
        westLabel.setFont(normalFont);
        westLabel.setBounds(0,130,50,50 );
        mapPanel.add(westLabel);
    }

    private void setEastLabel(){
        eastLabel = new JLabel("E");
        eastLabel.setForeground(Color.white);
        eastLabel.setBackground(Color.black);
        eastLabel.setFont(normalFont);
        eastLabel.setBounds(280,130,50,50 );
        mapPanel.add(eastLabel);
    }


    private void setCommandTextField(){
        Font textFont = new Font("SansSerif", Font.BOLD, 15);
        commandTextField = new JTextField();
        commandTextField = new JTextField( 30);
        commandTextField.setBackground(Color.black);
        commandTextField.setPreferredSize(new Dimension(50, 30));
        commandTextField.setFont(textFont);
        commandTextField.setForeground(Color.white);
        commandPanel.add(commandTextField);

    }
    //Metodo che gestisce il bottone load
    private void setMainTextPanel(){
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(-10, 200, 600, 100);
        mainTextPanel.setBackground(Color.black);
        window.add(mainTextPanel);

        mainTextArea = new JLabel("Give a name to your character: ");
        mainTextArea.setBounds(0, 100, 600, 50);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextPanel.add(mainTextArea);
    }
    
    //questo è il pannello dove gestisco l'output delle azioni / racconto la storia
    private void setMessageTextPanel(){
        messageTextPanel = new JPanel();
        messageTextPanel.setBackground(Color.black);
        messageTextPanel.setBounds(-10, 450, 820, 150);
        messageTextPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));


        messageTextArea = new JTextArea("Aaaaaaaaaaaahhhhhhhhhh ....  .... .... ....\nCredo sia stata una pessima idea addentrarsi in quella grotta\nSembra molto tranquillo questo posto però allo stesso tempo mi sento osservato...\nProgetto di EDIDS GRUPPO AAAS");
        messageTextArea.setBounds(0, 0, 750, 50);
        messageTextArea.setBorder(new EmptyBorder(10,0,0,0));
        messageTextArea.setBackground(Color.black);
        messageTextArea.setForeground(Color.white);
        Font messageText =  new Font("SansSerif", Font.ITALIC, 18);
        messageTextArea.setFont(messageText);
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        messageTextArea.setEditable(false);
        messageTextPanel.add(messageTextArea);

        window.add(messageTextPanel);



    }
    private void setMainCharacterSelectionPanel(){
        mainCharacterSelectionPanel = new JPanel();
        mainCharacterSelectionPanel.setBounds(100,300, 400, 400);
        mainCharacterSelectionPanel.setBackground(Color.black);

        mainCharacterSelectionLabel = new JLabel("Choose your own character: ");
        mainCharacterSelectionLabel.setBorder(new EmptyBorder(10,0,0,0));
        mainCharacterSelectionLabel.setBounds(150, 300, mainCharacterSelectionPanel.getWidth(), 50);
        mainCharacterSelectionLabel.setBackground(Color.black);
        mainCharacterSelectionLabel.setForeground(Color.white);
        mainCharacterSelectionLabel.setFont(normalFont);
        mainCharacterSelectionPanel.add(mainCharacterSelectionLabel);
        setMainCharacterButtonPanel();
        startGameLabel = new JLabel("Press START to begin the game.");
        startGameLabel.setBorder(new EmptyBorder(10,0,0,0));
        startGameLabel.setBounds(150, 450, 400, 50);
        startGameLabel.setBackground(Color.black);
        startGameLabel.setForeground(Color.white);
        startGameLabel.setFont(normalFont);
        mainCharacterSelectionPanel.add(startGameLabel);
        setStartGameButton();
        setCommandPanel();
        window.add(mainCharacterSelectionPanel);

    }
    
    //pannello di gioco player con tutti i parametri di gioco
    private void setGameScreenPanel(){
        statPanel = new JPanel();
        statPanel.setBounds(550, -5, 300, 455 );
        statPanel.setBackground(Color.black);
        statPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        statPanel.setLayout(new GridLayout(0,1));
        statLabel  = new JLabel("Stats: ");
        statLabel.setBorder(new EmptyBorder(10,10,0,0));
        statLabel.setForeground(Color.white);
        statLabel.setFont(normalFont);
        statPanel.add(statLabel);
        /* setNameLabel();
        setCharacterLabel();
        setHpLabel();
        setInventoryWeight();
        setPotionLabel();
        setWeaponLabel();
        setMoneyLabel();
        setRoomNumberLabel(); */

        setMessageTextPanel(); 
        setMapPanel();
        window.add(statPanel);
    }

    private void setNameLabel(){
        nameLabel  = new JLabel("Name: ");
        nameLabel.setForeground(Color.white);
        nameLabel.setEnabled(false);
        nameLabel.setFont(normalFont);
        nameLabel.setBorder(new EmptyBorder(0,10,0,0));
        statPanel.add(nameLabel);
    }

    private void setCharacterLabel(){
        characterLabel  = new JLabel("Character: ");
        characterLabel.setForeground(Color.white);
        characterLabel.setEnabled(false);
        characterLabel.setFont(normalFont);
        characterLabel.setBorder(new EmptyBorder(0,10,0,0));
        statPanel.add(characterLabel);
    }

    private void setHpLabel(){
        hpLabel  = new JLabel("HP: ");
        hpLabel.setForeground(Color.white);
        hpLabel.setFont(normalFont);
        hpLabel.setEnabled(false);
        hpLabel.setBorder(new EmptyBorder(0,10,0,0));
        statPanel.add(hpLabel);
    }
    private void setInventoryWeight(){
        inventoryWeight  = new JLabel("Weight: ");
        inventoryWeight.setForeground(Color.white);
        inventoryWeight.setEnabled(false);
        inventoryWeight.setFont(normalFont);
        inventoryWeight.setBorder(new EmptyBorder(0,10,0,0));
        statPanel.add(inventoryWeight);
    }

    private void setPotionLabel(){
        potionLabel  = new JLabel("Potion: ");
        potionLabel.setForeground(Color.white);
        potionLabel.setEnabled(false);
        potionLabel.setFont(normalFont);
        potionLabel.setBorder(new EmptyBorder(0,10,0,0));
        statPanel.add(potionLabel);
    }
    private void setWeaponLabel(){
        weaponLabel  = new JLabel("Weapon: ");
        weaponLabel.setForeground(Color.white);
        weaponLabel.setEnabled(false);
        weaponLabel.setFont(normalFont);
        weaponLabel.setBorder(new EmptyBorder(0,10,0,0));
        statPanel.add(weaponLabel);
    }

    private void setMoneyLabel(){
        moneyLabel  = new JLabel("Money: ");
        moneyLabel.setForeground(Color.white);
        moneyLabel.setEnabled(false);
        moneyLabel.setFont(normalFont);
        moneyLabel.setBorder(new EmptyBorder(0,10,0,0));
        statPanel.add(moneyLabel);
    }
    private void setRoomNumberLabel(){
        roomNumberLabel  = new JLabel("Room: ");
        roomNumberLabel.setForeground(Color.white);
        roomNumberLabel.setEnabled(false);
        roomNumberLabel.setFont(normalFont);
        roomNumberLabel.setBorder(new EmptyBorder(0,10,0,0));
        statPanel.add(roomNumberLabel);
    }




}
