package UI;

import ManageFile.DownloadFile;
import ManageFile.uploadFile;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Objects;


public class choiceHandler implements ActionListener {
    UI userInterfaceHandler;
    uploadFile upload;
    visibilityManager vManager;
    File fileSave;
    PrintWriter printWriter;
    String[] fileNameArray = {"Filesave 1.txt", "Filesave 2.txt", "Filesave 3.txt", "Filesave 4.txt"};
    /*
    Problema che potrebbe capitare in futuro:
    Importare sempre la UI che si sta usando definendola nel costruttore, altrimenti ne viene creata una nuova che
    non contiene niente e da errore
    */
    private static  int counterFile = 0;
    private int counterFileFirstLoad=0;
    public choiceHandler(UI ui) {
        userInterfaceHandler = ui;
        vManager = new visibilityManager(userInterfaceHandler);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String choice = e.getActionCommand();


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

            case "Load":
                vManager.showLoadScreen();
                setLoad();
                break;

            case "backToMenu":
                String inputLoadText = userInterfaceHandler.commandLoadTextField.getText();
                if(!inputLoadText.equals("")){
                    manageLoadTextInput(inputLoadText);
                }
                break;

            case "input":
                String inputText = userInterfaceHandler.commandTextField.getText();
                if(!Objects.equals(inputText, "")){
                    manageTextInput(inputText);
                }

        }
    }

    public void manageTextInput(String textInput){

        switch (textInput){
            case "save":

                File fileLoad;
                if(counterFileFirstLoad < 4 && counterFile < 4){
                    for (int i = counterFile; i<fileNameArray.length+1; i++) {
                        fileLoad = new File("FileDownload/" + fileNameArray[i]);
                        if(fileLoad.exists()){
                            if(userInterfaceHandler.setAlertMenu(0) == 0){
                                setFileSaveOverwrite(fileNameArray[i]);
                                vManager.showMenuScreen();
                                break;
                            }else{
                                vManager.showGameScreen();
                                break;
                            }

                        }else{
                            setFileSave(fileNameArray[i]);

                            break;
                        }

                    }
                    counterFile++;
                }else{
                    userInterfaceHandler.setAlertMenu(1);
                }
                vManager.showMenuScreen();


                break;

            case "back":
                vManager.showMenuScreen();
                break;

            case "save 1":
                setFileSaveOverwrite(fileNameArray[0]);
                vManager.showMenuScreen();

                break;

            case "save 2":
                setFileSaveOverwrite(fileNameArray[1]);
                vManager.showMenuScreen();

                break;

            case "save 3":
                setFileSaveOverwrite(fileNameArray[2]);
                vManager.showMenuScreen();

                break;

            case "save 4":
                setFileSaveOverwrite(fileNameArray[3]);
                vManager.showMenuScreen();

                break;

        }
    }

    private void manageLoadTextInput(String input){

        switch (input){
            case "back":
                vManager.showMenuScreen();
                break;

        }
    }


    private void setFileSave(String fileName){
        try {
            fileSave = new File("FileLoad/" + fileName);
            fileSave.createNewFile();
            printWriter = new PrintWriter(fileSave);
            printWriter.println("Save n. " + counterFile +"\n");
            printWriter.close();
            upload = new uploadFile(fileName);
            System.out.println("Nuovo file creato!");
            counterFileFirstLoad++;
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void setFileSaveOverwrite(String name){
        try{
            fileSave = new File("FileLoad/"+name);
            printWriter = new PrintWriter(fileSave);
            printWriter.println("Sovrascitto " + counterFile +"\n");
            printWriter.close();
            upload = new uploadFile(name);
            System.out.println("File sovrascritto");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setLoad(){
        File fileLoad;
        for (String s : fileNameArray) {
            fileLoad = new File("FileDownload/" + s);

            if (!fileLoad.exists()) {
                DownloadFile downloadFIle = new DownloadFile(s, userInterfaceHandler);
            } else {
                counterFileFirstLoad++;
                System.out.println("File " + s + " già presente!");
            }

        }

    }
}


