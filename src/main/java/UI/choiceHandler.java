package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class choiceHandler implements ActionListener {
    UI userInterfaceHandler;
    uploadFile upload;
    /*
    Problema che potrebbe capitare in futuro:
    Importare sempre la UI che si sta usando definendola nel costruttore, altrimenti ne viene creata una nuova che
    non contiene niente e da errore
    */
    private int counterFile;
    public choiceHandler(UI ui) {
        userInterfaceHandler = ui;
        counterFile = 0;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String choice = e.getActionCommand();
        visibilityManager vManager = new visibilityManager(userInterfaceHandler);
        switch (choice){
            case "Start":
                vManager.showStartScreen();
                break;
            case "Exit":
                System.exit(0);
                break;
            case "Start game":
                vManager.showGameScreen();
                break;
            case "save":
                vManager.showMenuScreen();
                System.out.println("Scrittura su file eseguita");

                try {
                    /*
                    FileWriter fileWriter = new FileWriter("DungeonUnipd/test.txt");
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write("Aggiunta dati utente");

                    bufferedWriter.close();
                    System.out.println("Scrittura su file eseguita");
                    */
                    String fileName = "Filesave " + (++counterFile) + ".txt";
                    File fileSave = new File("FileLoad/" + fileName);
                    if(!fileSave.exists()){
                        fileSave.createNewFile();
                    }
                    PrintWriter printWriter = new PrintWriter(fileSave);
                    printWriter.println("Save n. " + counterFile +"\n");
                    System.out.println("Nuovo file creato!");
                    printWriter.close();

                    upload = new uploadFile(fileName);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }

}


