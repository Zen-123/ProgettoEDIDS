package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UI {
    JFrame window;
    JPanel titleNamePanel, menuButtonPanel,  mainTextPanel, mainTextFieldPanel, mainCharacterSelectionPanel, mainCharacterButtonPanel;
    JLabel titleNameLabel, mainCharacterSelectionLabel;
    JButton startButton, loadButton, exitButton, warriorButton, archerButton, thiefButton;
    JTextArea mainTextArea;
    JTextField textField;
    Font titleFont = new Font("Serif", Font.PLAIN, 70);
    Font normalFont = new Font("Serif",Font.PLAIN, 26);
    choiceHandler handler = new choiceHandler(this);

    public void createUI(){

        setWindow();
        setTitle();
        setMenuButtonPanel();
        setMainTextPanel();
        setTextField();
        setMainCharacterSelectionPanel();
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
        mainCharacterButtonPanel = new JPanel();
        mainCharacterButtonPanel.setBounds(-200,350, 600, 250);
        mainCharacterButtonPanel.setBackground(Color.black);
        mainCharacterButtonPanel.setLayout(new GridLayout(3,1));
        setWarriorButton();
        setArcherButton();
        setThiefButton();
        mainCharacterSelectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        mainCharacterSelectionPanel.add(mainCharacterButtonPanel);
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

    private void setWarriorButton(){
        warriorButton = new JButton("Warrior");
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
        mainCharacterButtonPanel.add(warriorButton);

    }

    private void setArcherButton(){
        archerButton = new JButton("Archer");
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
        mainCharacterButtonPanel.add(archerButton);

    }

    private void setThiefButton(){
        thiefButton = new JButton("Thief");
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
    //Metodo che gestisce il bottone load


    private void setMainTextPanel(){
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(110, 200, 600, 100);
        mainTextPanel.setBackground(Color.black);
        window.add(mainTextPanel);

        mainTextArea = new JTextArea("Dai un nome al tuo personaggio: ");
        mainTextArea.setBounds(100, 100, 600, 50);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);
        mainTextPanel.add(mainTextArea);
    }

    private void setMainCharacterSelectionPanel(){
        mainCharacterSelectionPanel = new JPanel();
        mainCharacterSelectionPanel.setBounds(100,300, 400, 250);
        mainCharacterSelectionPanel.setBackground(Color.black);

        mainCharacterSelectionLabel = new JLabel("Scegli il tuo personaggio: ");
        mainCharacterSelectionLabel.setBounds(150, 300, 400, 50);
        mainCharacterSelectionLabel.setBackground(Color.black);
        mainCharacterSelectionLabel.setForeground(Color.white);
        mainCharacterSelectionLabel.setFont(normalFont);
        mainCharacterSelectionPanel.add(mainCharacterSelectionLabel);
        setMainCharacterButtonPanel();

        window.add(mainCharacterSelectionPanel);

    }

}
