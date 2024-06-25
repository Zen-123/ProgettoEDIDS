package UI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * Classe per la gestione della user interface utilizzando la libreria java swing
 */
public class UI {
    private JFrame window;
    public JPanel titleNamePanel, menuButtonPanel,  mainTextPanel, mainTextFieldPanel, mainCharacterSelectionPanel, statPanel, messageTextPanel, commandPanel, mapPanel, loadMessagePanel, loadTextFieldPanel, winPanel;
    private JLabel titleNameLabel,mainTextArea, mainCharacterSelectionLabel, startGameLabel, statLabel, nameLabel, characterLabel, hpLabel, inventoryWeight, potionLabel, weaponLabel, moneyLabel, roomNumberLabel, commandLabel, southLabel, northLabel, eastLabel, westLabel, monsterLabel, winLabel;
    public JLabel loadLabel1, loadLabel2, loadLabel3, loadLabel4, counterLoadLabel;
    private JButton startButton, loadButton, exitButton, startGameButton, commandButton, loadMessageButton, winButton;
    private JRadioButton warriorButton, archerButton, thiefButton;
    private JTextArea messageTextArea;
    private ButtonGroup mainCharacterButtonPanel;
    public JTextField textField, commandTextField, commandLoadTextField;
    private Font titleFont = new Font("Serif", Font.PLAIN, 70);
    private  Font normalFont = new Font("Serif",Font.PLAIN, 26);
    ActionListener handler = new choiceHandler(this);
    private String fileName;
    private File fileLoad;

    /**
     * Metodo che permette la crezione della UI
     */
    public void createUI(){

        setWindow();
        setTitle();
        setMenuButtonPanel();
        setMainTextPanel();
        setMainCharacterSelectionPanel();
        setGameScreenPanel();
        setLoadMessagePanel();
        setWinPanel();
        window.setVisible(true);



    }


    /*
        Metodo che gestisce la finestra di gioco effettiva
        creazione e gestione di window
     */
    private void setWindow(){
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
    }


    /*
        Metodo che gestisce il titleNamePanel
        creazione e gestione di titleNamePanel, titleNameLabel
        pagina: menuScreen
     */
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

    private void setWinLabel(){
        Font winFont = new Font("Serif", Font.PLAIN, 50);
        winLabel = new JLabel("YOU WIN! ");
        winLabel.setFont(winFont);
        winLabel.setBorder(new EmptyBorder(10,80,0,0));
        winLabel.setForeground(Color.white);
        winPanel.add(winLabel);
    }

    private void setHpLabelWinPage(){
        hpLabel = new JLabel("Player: ");
        hpLabel.setFont(normalFont);
        hpLabel.setBorder(new EmptyBorder(0,10,0,0));
        hpLabel.setForeground(Color.white);
        winPanel.add(hpLabel);
    }

    private void setMoneyLabelWinPage(){
        moneyLabel = new JLabel("Experience/money: ");
        moneyLabel.setFont(normalFont);
        moneyLabel.setBorder(new EmptyBorder(0,10,0,0));
        moneyLabel.setForeground(Color.white);
        winPanel.add(moneyLabel);
    }

    private void setMonsterLabel(){
        monsterLabel = new JLabel("Monsters killed: ");
        monsterLabel.setFont(normalFont);
        monsterLabel.setBorder(new EmptyBorder(0,10,0,0));
        monsterLabel.setForeground(Color.white);
        winPanel.add(monsterLabel);
    }

    private void setWinButton(){

        winButton = new JButton("RETURN TO MAIN MENU");
        winButton.setBackground(Color.black);
        winButton.setForeground(Color.WHITE);
        winButton.setFont(normalFont);
        winButton.setFocusPainted(false);
        winButton.addActionListener(handler);
        winButton.setActionCommand("returnToMainMenu");



        //creazione effetto di hover sul bottone
        winButton.addMouseListener( new MouseAdapter(){
            public void mouseEntered(MouseEvent evt) {
                winButton.setBackground(Color.blue);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                winButton.setBackground(Color.black);
            }
        });
        winPanel.add(winButton);
    }

    private void setWinPanel(){
        winPanel = new JPanel();
        winPanel.setBounds(200, 50,400, 400 );
        winPanel.setBackground(Color.black);
        winPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        winPanel.setLayout(new GridLayout(5,1));
        setWinLabel();
        setHpLabelWinPage();
        setMoneyLabelWinPage();
        setMonsterLabel();
        setWinButton();
        window.add(winPanel);
    }


    /*
        Metodo che gestisce il menuButtonPanel che va a contenere i bottoni Start, Load, Save
        vengono creati i seguenti componenti: menuButtonPanel, startButton, loadButton, exitButton
        pagina: menuScreen
     */
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


    /*
        Metodo per la gestione del ButtonGroup
        sono creati i componenti:mainCharacterButtonPanel, warriorButton, archerButton, thiefButton
        pagina: StartScreen
     */
    private void setMainCharacterButtonPanel(){
        mainCharacterButtonPanel = new ButtonGroup();
        setWarriorButton();
        setArcherButton();
        setThiefButton();
        mainCharacterSelectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

    }

    /*
        metodo che gestisce il bottone startButton
        pagina: menuScreen
     */
    private void setStartButton(){
        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(normalFont);
        startButton.setFocusPainted(false);
        startButton.addActionListener(handler);
        startButton.setActionCommand("Start");

        //creazione effetto di hover sul bottone
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

    /*
        Metodo che gestice il bottone startGameButton
        pagina: StartScreen
     */
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

    /*
        Metodo che gestice il bottone warriorButton
        pagina: StartScreen
     */
    private void setWarriorButton(){
        warriorButton = new JRadioButton("Warrior");
        warriorButton.setBackground(Color.black);
        warriorButton.setForeground(Color.WHITE);
        warriorButton.setFont(normalFont);
        warriorButton.setFocusPainted(false);
        warriorButton.addActionListener(handler);
        warriorButton.setActionCommand("warrior");

        //hover
        warriorButton.addMouseListener( new MouseAdapter(){
            public void mouseEntered(MouseEvent evt) {
                warriorButton.setBackground(Color.blue);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                warriorButton.setBackground(Color.black);
            }
        });
        //selezione del bottone
        warriorButton.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return Color.darkGray;
            }
        });
        mainCharacterSelectionPanel.add(warriorButton);
        mainCharacterButtonPanel.add(warriorButton);

    }


    /*
        Metodo che gestice il bottone archerButton
        pagina: StartScreen
    */
    private void setArcherButton(){
        archerButton = new JRadioButton("Archer");
        archerButton.setBackground(Color.black);
        archerButton.setForeground(Color.WHITE);
        archerButton.setFont(normalFont);
        archerButton.setFocusPainted(false);
        archerButton.addActionListener(handler);
        archerButton.setActionCommand("archer");
        //hover
        archerButton.addMouseListener( new MouseAdapter(){
            public void mouseEntered(MouseEvent evt) {
                archerButton.setBackground(Color.blue);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                archerButton.setBackground(Color.black);
            }
        });
        //selezione del bottone
        archerButton.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return Color.darkGray;
            }
        });
        mainCharacterSelectionPanel.add(archerButton);
        mainCharacterButtonPanel.add(archerButton);

    }


    /*
        Metodo che gestice il bottone thiefButton
        pagina: StartScreen
    */
    private void setThiefButton(){
        thiefButton = new JRadioButton("Thief");
        thiefButton.setBackground(Color.black);
        thiefButton.setForeground(Color.WHITE);
        thiefButton.setFont(normalFont);
        thiefButton.setFocusPainted(false);
        thiefButton.addActionListener(handler);
        thiefButton.setActionCommand("thief");

        //hover
        thiefButton.addMouseListener( new MouseAdapter(){
            public void mouseEntered(MouseEvent evt) {
                thiefButton.setBackground(Color.blue);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                thiefButton.setBackground(Color.black);
            }
        });
        //selezione del bottone
        thiefButton.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return Color.darkGray;
            }
        });
        mainCharacterSelectionPanel.add(thiefButton);
        mainCharacterButtonPanel.add(thiefButton);

    }


    /*
        Metodo che gestice il bottone exitButton
        pagina: menuScreen
     */
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


    /*
        Metodo che gestice il bottone loadButton
        pagina: menuScreen
     */
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


    /*
        Metodo che gestisce il textField per inserimento di comandi da parte di utente
        pagina: StartScreen
     */
    private void setTextField(){
        mainTextFieldPanel = new JPanel();
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


    /*
    * Metodo per la visualizzazione della mappa
    * pagina: gameScreen
    * */
    private void setMapPanel(){
        mapPanel = new JPanel();
        mapPanel.setBounds(130,30,300,300);
        mapPanel.setBackground(Color.black);
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        mapPanel.setLayout(null);

        //metodi per segnalare le posizioni disponibili
        setSouthLabel();
        setNorthLabel();
        setWestLabel();
        setEastLabel();

        window.add(mapPanel);
    }


    /*
    Metodo per segnalare la posizione S
    pagina: gameScreen
     */
    private void setSouthLabel(){
        southLabel = new JLabel("S");
        southLabel.setForeground(Color.white);
        southLabel.setBackground(Color.black);
        southLabel.setFont(normalFont);
        southLabel.setBounds(145,140,mapPanel.getWidth(),mapPanel.getHeight() );
        mapPanel.add(southLabel);
    }


    /*
       Metodo per segnalare la posizione N
       pagina: gameScreen
    */
    private void setNorthLabel(){
        northLabel = new JLabel("N");
        northLabel.setForeground(Color.white);
        northLabel.setBackground(Color.black);
        northLabel.setFont(normalFont);
        northLabel.setBounds(145,0,50,50);
        mapPanel.add(northLabel);
    }


    /*
       Metodo per segnalare la posizione W
       pagina: gameScreen
    */
    private void setWestLabel(){
        westLabel = new JLabel("W");
        westLabel.setForeground(Color.white);
        westLabel.setBackground(Color.black);
        westLabel.setFont(normalFont);
        westLabel.setBounds(0,130,50,50 );
        mapPanel.add(westLabel);
    }

    /*
       Metodo per segnalare la posizione E
       pagina: gameScreen
    */
    private void setEastLabel(){
        eastLabel = new JLabel("E");
        eastLabel.setForeground(Color.white);
        eastLabel.setBackground(Color.black);
        eastLabel.setFont(normalFont);
        eastLabel.setBounds(280,130,50,50 );
        mapPanel.add(eastLabel);
    }


    /*
    Metodo per la gestione del commandTextField, che permette all'utente di giocare inserendo comandi testuali
    pagina: gameScreen
     */
    private void setCommandPanel(){
        commandPanel = new JPanel();
        commandPanel.setBounds(-10, 350,560, 100);
        commandPanel.setBackground(Color.black);
        commandPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        commandLabel = new JLabel("Enter the command: ");
        commandLabel.setBounds(0,350,100,50 );
        commandLabel.setBorder(new EmptyBorder(10,10,0,0));
        commandLabel.setBackground(Color.black);
        commandLabel.setForeground(Color.white);
        commandLabel.setFont(normalFont);
        commandPanel.add(commandLabel);

        //metodo per la gestione effettiva del commantTextField
        setCommandTextField();


        //visualizzazione gestita in modo migliore tramite GroupLayout
        GroupLayout layout = new GroupLayout(commandPanel);
        commandPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(commandLabel)
                        .addGroup(layout.createSequentialGroup()
                        .addComponent(commandTextField)
                        .addComponent(commandButton)))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addComponent(commandLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(commandTextField)
                .addComponent(commandButton))
        );

        window.add(commandPanel);

    }

    /*
    Metodo per la gestione  commandTextField e commandButton
    pagina: gameScreen
     */
    private void setCommandTextField(){
        Font textFont = new Font("SansSerif", Font.BOLD, 15);
        commandTextField = new JTextField( 20);
        commandTextField.setPreferredSize(new Dimension(50, 30));
        commandTextField.setBackground(Color.darkGray);
        commandTextField.setFont(textFont);
        commandTextField.setForeground(Color.white);

        commandButton = new JButton("Enter");
        commandButton.setBackground(Color.black);
        commandButton.setForeground(Color.white);
        commandButton.setPreferredSize(new Dimension(30, 30));
        commandButton.addActionListener(handler);
        commandButton.setActionCommand("input");
        commandButton.setFocusPainted(false);
        commandButton.setFont(textFont);
        //hover
        commandButton.addMouseListener( new MouseAdapter(){
            public void mouseEntered(MouseEvent evt) {
                commandButton.setBackground(Color.blue);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {commandButton.setBackground(Color.black);
            }
        });
        commandPanel.add(commandTextField);
        commandPanel.add(commandButton);


    }
    /*
    Metodo per la gestione del mainTextPanel, mainTextArea per mostrare a video un messaggio
    pagina: startGame
     */
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
        setTextField();

        mainTextPanel.add(mainTextArea);
    }


    /*
    Metodo che mostra a video i testi durante il gioco, gestisce i componenti messageTextPanel,messageTextArea
    pagina: gameScreen
     */
    private void setMessageTextPanel(){
        messageTextPanel = new JPanel();
        messageTextPanel.setBackground(Color.black);
        messageTextPanel.setBounds(-10, 450, 820, 150);
        messageTextPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));


        messageTextArea = new JTextArea("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
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
    /*
        Metodo per la gestione di interfaccia completa della pagina startScreen
     */
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

        //setta i bottoni warriorButton, archerButton, thiefButton
        setMainCharacterButtonPanel();

        startGameLabel = new JLabel("Press START to begin the game.");
        startGameLabel.setBorder(new EmptyBorder(10,0,0,0));
        startGameLabel.setBounds(150, 450, 400, 50);
        startGameLabel.setBackground(Color.black);
        startGameLabel.setForeground(Color.white);
        startGameLabel.setFont(normalFont);

        //metodi per la gestione di invio comandi testuali da parte di utente e bottone startButton
        setStartGameButton();
        setCommandPanel();
        window.add(mainCharacterSelectionPanel);

    }
    /*
    Metodo per la gestione di interfaccia completa della pagina gameScreen
    */
    private void setGameScreenPanel(){
        statPanel = new JPanel();
        statPanel.setBounds(550, -5, 300, 455 );
        statPanel.setBackground(Color.black);
        statPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        statPanel.setLayout(new GridLayout(11,1));
        statLabel  = new JLabel("Stats: ");
        statLabel.setBorder(new EmptyBorder(10,10,0,0));
        statLabel.setForeground(Color.white);
        statLabel.setFont(normalFont);
        statPanel.add(statLabel);
        //metodi che vanno ad impostare tutti i dati del giocatore e li mostrano a video
        setNameLabel();
        setCharacterLabel();
        setHpLabel();
        setInventoryWeight();
        setPotionLabel();
        setWeaponLabel();
        setMoneyLabel();
        setRoomNumberLabel();
        setMessageTextPanel();
        setCounterLoadLabel();
        setMapPanel();
        window.add(statPanel);



    }

    /*
    metodo che mostra il nome del giocatore
    pagina: gameScreen
     */
    private void setNameLabel(){
        nameLabel  = new JLabel("Name: ");
        nameLabel.setForeground(Color.white);
        nameLabel.setFont(normalFont);
        nameLabel.setBorder(new EmptyBorder(0,10,0,0));
        statPanel.add(nameLabel);
    }

    /*
   metodo che mostra il personaggio scelto dal giocatore
   pagina: gameScreen
    */
    private void setCharacterLabel(){
        characterLabel  = new JLabel("Character: ");
        characterLabel.setForeground(Color.white);
        characterLabel.setFont(normalFont);
        characterLabel.setBorder(new EmptyBorder(0,10,0,0));
        statPanel.add(characterLabel);
    }

    /*
       metodo che mostra la vita del giocatore
       pagina: gameScreen
    */
    private void setHpLabel(){
        hpLabel  = new JLabel("HP: ");
        hpLabel.setForeground(Color.white);
        hpLabel.setFont(normalFont);
        hpLabel.setBorder(new EmptyBorder(0,10,0,0));
        statPanel.add(hpLabel);
    }

    /*
       metodo che mostra il peso dell'inventario del giocatore
       pagina: gameScreen
    */
    private void setInventoryWeight(){
        inventoryWeight  = new JLabel("Weight: ");
        inventoryWeight.setForeground(Color.white);
        inventoryWeight.setFont(normalFont);
        inventoryWeight.setBorder(new EmptyBorder(0,10,0,0));
        statPanel.add(inventoryWeight);
    }

    /*
       metodo che mostra il numero di pozioni del giocatore
       pagina: gameScreen
    */
    private void setPotionLabel(){
        potionLabel  = new JLabel("Potion: ");
        potionLabel.setForeground(Color.white);
        potionLabel.setFont(normalFont);
        potionLabel.setBorder(new EmptyBorder(0,10,0,0));
        statPanel.add(potionLabel);
    }

    /*
        metodo che mostra l'arma del giocatore
        pagina: gameScreen
    */
    private void setWeaponLabel(){
        weaponLabel  = new JLabel("Weapon: ");
        weaponLabel.setForeground(Color.white);
        weaponLabel.setFont(normalFont);
        weaponLabel.setBorder(new EmptyBorder(0,10,0,0));
        statPanel.add(weaponLabel);
    }

    /*
       metodo che mostra i soldi del giocatore
       pagina: gameScreen
    */
    private void setMoneyLabel(){
        moneyLabel  = new JLabel("Money: ");
        moneyLabel.setForeground(Color.white);
        moneyLabel.setFont(normalFont);
        moneyLabel.setBorder(new EmptyBorder(0,10,0,0));
        statPanel.add(moneyLabel);
    }

    /*
       metodo che mostra la stanza in cui si trova il giocatore
       pagina: gameScreen
    */
    private void setRoomNumberLabel(){
        roomNumberLabel  = new JLabel("Room: ");
        roomNumberLabel.setForeground(Color.white);
        roomNumberLabel.setFont(normalFont);
        roomNumberLabel.setBorder(new EmptyBorder(0,10,0,0));
        statPanel.add(roomNumberLabel);
    }

    private void setCounterLoadLabel(){
        counterLoadLabel = new JLabel();
        counterLoadLabel.setBorder(new EmptyBorder(0,10,0,0));
        counterLoadLabel.setFont(normalFont);
        counterLoadLabel.setForeground(Color.WHITE);
        statPanel.add(counterLoadLabel);
    }
    /*
       metodo che gestisce la pagina di load
    */
    private void setLoadMessagePanel(){
        loadMessagePanel = new JPanel();
        loadMessagePanel.setBounds(200, 50, 400, 400);
        loadMessagePanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        loadMessagePanel.setBackground(Color.black);
        loadMessagePanel.setLayout(new GridLayout(4,1));

        //metodi che vanno ad impostare il contenuto degli slot di salvataggio
        setLoadLabel1();
        setLoadLabel2();
        setLoadLabel3();
        setLoadLabel4();

        //metodo che gestice il commandLoadTextField per inserimento testuale da parte di utente
        setCommandLoadTextField();

        // metodo per la gestione del loadMessageButton
        setLoadMessageButton();
        window.add(loadMessagePanel);
        window.add(loadTextFieldPanel);
    }
    /*
        Metodo che gestisce il loadMessageButton per inviare i dati al choiceHandler
        pagina: loadScreen
     */
    private void setLoadMessageButton() {
        loadMessageButton = new JButton("Enter");
        loadMessageButton.setForeground(Color.white);
        loadMessageButton.setBackground(Color.black);
        loadMessageButton.setFont(normalFont);
        loadMessageButton.setFocusPainted(false);
        loadMessageButton.addActionListener(handler);
        loadMessageButton.setActionCommand("backToMenu");

        loadTextFieldPanel.add(loadMessageButton);


    }
    /*
       Metodo che gestisce il commandLoadTextField per fare inserire ad utente i comandi
       pagina: loadScreen
    */
    private void setCommandLoadTextField(){
        Font textFont = new Font("SansSerif", Font.BOLD, 15);
        loadTextFieldPanel =  new JPanel();
        loadTextFieldPanel.setBackground(Color.black);
        loadTextFieldPanel.setLayout(new GridLayout(1, 2));
        loadTextFieldPanel.setBounds(200, 450,400, 50);

        commandLoadTextField = new JTextField( 30);
        commandLoadTextField.setBounds(200, 480, 400, 30);
        commandLoadTextField.setBackground(Color.darkGray);
        commandLoadTextField.setFont(textFont);
        commandLoadTextField.setForeground(Color.white);
        loadTextFieldPanel.add(commandLoadTextField);
    }

    /*
    metodo per la gestione dello slot di salvataggio 1
    pagina: loadScreen
     */
    private void setLoadLabel1(){

        loadLabel1 = new JLabel();
        fileName = "Filesave 1.txt";
        fileLoad = new File("FileDownload/" + fileName);
        if(fileLoad.exists()){
            loadLabel1.setText("Save slot 1");
        }
        loadLabel1.setBounds(300, 150, 400, 100);
        loadLabel1.setBackground(Color.black);
        loadLabel1.setForeground(Color.white);
        loadLabel1.setFont(normalFont);
        loadLabel1.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        loadMessagePanel.add(loadLabel1);


    }

    /*
      metodo per la gestione dello slot di salvataggio 2
      pagina: loadScreen
       */
    private void setLoadLabel2(){

        loadLabel2 = new JLabel();
        fileName = "Filesave 2.txt";
        fileLoad = new File("FileDownload/" + fileName);
        if(fileLoad.exists()){
            loadLabel2.setText("Save slot 2");
        }
        loadLabel2.setBounds(300, 150, 400, 100);
        loadLabel2.setBackground(Color.black);
        loadLabel2.setForeground(Color.white);
        loadLabel2.setFont(normalFont);

        loadLabel2.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        loadMessagePanel.add(loadLabel2);


    }

    /*
      metodo per la gestione dello slot di salvataggio 3
      pagina: loadScreen
   */
    private void setLoadLabel3(){

        loadLabel3 = new JLabel();
        fileName = "Filesave 3.txt";
        fileLoad = new File("FileDownload/" + fileName);
        if(fileLoad.exists()){
            loadLabel3.setText("Save slot 3");
        }
        loadLabel3.setBounds(300, 150, 400, 100);
        loadLabel3.setBackground(Color.black);
        loadLabel3.setForeground(Color.white);
        loadLabel3.setFont(normalFont);
        loadLabel3.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        loadMessagePanel.add(loadLabel3);


    }

    /*
      metodo per la gestione dello slot di salvataggio 4
      pagina: loadScreen
   */
    public void setLoadLabel4(){

        loadLabel4 = new JLabel();
        fileName = "Filesave 4.txt";
        fileLoad = new File("FileDownload/" + fileName);
        if(fileLoad.exists()){
            loadLabel4.setText("Save slot 4");
        }
        loadLabel4.setBounds(300, 150, 400, 100);
        loadLabel4.setBackground(Color.black);
        loadLabel4.setForeground(Color.white);
        loadLabel4.setFont(normalFont);
        loadLabel4.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        loadMessagePanel.add(loadLabel4);


    }
    /*
      metodo per la gestione degli alert durante il comando "save"
      pagina: gameScreen
       */
    public int setAlertMenu(int value){
        if (value == 1){
            JOptionPane.showMessageDialog(commandPanel, "You have run out of available save slots, use \"save (1-4)\" to overwrite the available save slots", "Confirmation", JOptionPane.WARNING_MESSAGE );
            return -1;
        }

        return JOptionPane.showConfirmDialog(commandPanel, "This slot is already occupied by a previous save, do you want to overwrite?", "Confirmation", JOptionPane.YES_NO_OPTION);

    }


}
