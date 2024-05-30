package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UI {
    JFrame window;
    JPanel titleNamePanel, menuButtonPanel,  mainTextPanel, mainTextFieldPanel ;
    JLabel titleNameLabel, hpLabel, hpNumberLabel, weaponLabel, weaponNameLabel;
    JButton startButton, loadButton, exitButton;
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
        titleNamePanel.setBounds(100,50,600,250);
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
        mainTextFieldPanel.setBounds(100, 150, 600, 250);
        mainTextFieldPanel.setBackground(Color.black);
        window.add(mainTextFieldPanel);

        textField = new JTextField("Inserisci i comandi: ", 20);
        textField.setBackground(Color.gray);
        textField.setForeground(Color.white);
        mainTextFieldPanel.add(textField);

    }
    //Metodo che gestisce il bottone load


    private void setMainTextPanel(){
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);
        window.add(mainTextPanel);

        mainTextArea = new JTextArea("testo di esempio");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);
        mainTextPanel.add(mainTextArea);

    }

}
