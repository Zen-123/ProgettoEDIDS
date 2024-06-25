package UI;

import ManageFile.DownloadFile;
import ManageFile.uploadFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * Classe che gestice gli eventi sulla UI, implementa la classe astratta ActionListener per la gestione degli eventi
 */
public class choiceHandler implements ActionListener {
    //variabili private

    //contatore generale per i 4 slot di salvataggio
    private static int counterFile = 0;
    //contatore che va a contare i file già scaricati al primo caricamento del gioco
    private int counterFileFirstLoad = 0;
    private final UI userInterfaceHandler;
    private final visibilityManager vManager;
    private uploadFile upload;
    private File fileSave;
    private PrintWriter printWriter;
    private final String[] fileNameArray = {"Filesave 1.txt", "Filesave 2.txt", "Filesave 3.txt", "Filesave 4.txt"};

    /**
     * Costruttore parametrizzato della classe choiceHandler
     * @param ui oggetto della classe UI utilizzato per modificare varie parti dell'interfaccia a seguito di alcuni eventi
     */
    public choiceHandler(UI ui) {
        userInterfaceHandler = ui;
        vManager = new visibilityManager(userInterfaceHandler);
    }

    /**
     * Override della classe actionPerdormed per la gestione di specifici eventi
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //stringa che contiene la keyword per identificare l'evento
        String choice = e.getActionCommand();

        /*
        Switch usato per la gestione degli eventi dovuti ai bottoni della UI
         */
        switch (choice) {
            /*
                gestisce il bottone: startButton,
                apre il menù di selezione del personaggio
             */
            case "Start":
                vManager.showStartScreen();
                userInterfaceHandler.textField.setText("");
                userInterfaceHandler.textField.setVisible(true);
                break;
            /*
                gestisce il bottone: exitButton,
                termina il software
             */
            case "Exit":
                System.exit(0);
                break;
            /*
                gestisce il bottone: startGameButton
                mostra la schermata di gioco effettiva
             */
            case "Start game":
                vManager.showGameScreen();
                userInterfaceHandler.commandTextField.setText("");
                break;
            /*
                gestisce il bottone: loadButton
                mostra la schermata di caricamento dei salvataggi e scarica eventuali salvataggi presenti sul bucket
             */
            case "Load":
                vManager.showLoadScreen();
                setLoad();
                break;
            /*
                gestisce il bottone: loadMessageButton
                gestisce gli input scritti dall'utente nel textfield del loadScreen
             */
            case "backToMenu":
                //stringa che salva input scritto dall'utente sul commandLoadTextField
                String inputLoadText = userInterfaceHandler.commandLoadTextField.getText();
                if (!inputLoadText.isEmpty()) {
                    manageLoadTextInput(inputLoadText);
                }
                break;
            /*
                gestisce il bottone: commandButton
                gestisce gli input scritti dall'utente sul commandTextField presente nel MainCharacterSelectionPanel
             */
            case "input":
                String inputText = userInterfaceHandler.commandTextField.getText();
                if (!Objects.equals(inputText, "")) {
                    manageTextInput(inputText);
                }
                break;

            case "returnToMainMenu":
                vManager.showMenuScreen();
                break;

        }
    }

    /**
     * metodo che gestisce gli input testuali da parte dell'utente nel commandTextField
     * @param textInput stringa che identifica il comando scritto dall'utente
     */
    public void manageTextInput(String textInput) {

        /*
        Switch che gestisce i vari comandi scritti dall'utente
        "save" permette di salvare la partita attuale e caricare il salvataggio su asw s3
        "exit" permette di tornare alla pagina di menu
        "save 1-4" permettono di salvare la partita in uno specifico slot, usati solo per sovrascrivere altri salvataggi precedenti
         */
        switch (textInput) {
            case "save":

                File fileLoad;
                /*
                if che controlla che i salvataggi scaricati dal bucket prima dell'avvio del gioco
                siano inferiori al numero massimo di slot, inoltre controlla che i salvataggi durante
                la partita non superino i 4.
                 */

                if (counterFileFirstLoad < 4 && counterFile < 4) {
                    //for che scorre i file scaricati da aws
                    for (int i = counterFile; i < fileNameArray.length + 1; i++) {
                        fileLoad = new File("FileDownload/" + fileNameArray[i]);
                        if (fileLoad.exists()) {
                            userInterfaceHandler.counterLoadLabel.setText("Save n. " + (counterFile+1));
                            /*se lo slot salvataggio è già occupato si chiede all'utente se vuole sovrascriverlo
                            * oppure usare un altro slot disponibile */
                            if (userInterfaceHandler.setAlertMenu(0) == 0) {
                                //utente decide tramite l'alert di sovrascrivere il salvataggio precedente
                                setFileSaveOverwrite(fileNameArray[i]);
                                vManager.showMenuScreen();
                                break;
                            } else {
                                //utente decide di non sovrascrivere salvataggio precedente
                                vManager.showGameScreen();
                                break;
                            }

                        } else {
                            /*se lo slot è libero, ovvero non è stato trovato tra i file scaricati da aws un file
                            con il nome cercato, allora viene creato un nuovo file salvataggio
                            * */
                            setFileSave(fileNameArray[i]);
                            break;
                        }
                    }
                    counterFile++;
                } else {
                    //l'utente viene informato che gli slot salvataggio sono finiti tramite un alert
                    userInterfaceHandler.setAlertMenu(1);
                }
                //finite le operazioni si ritorna alla schermata iniziale
                vManager.showMenuScreen();
                break;

            case "exit":
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

            case "win":
                vManager.showWinPanel();
                break;
        }
    }

    /**
     * Metodo che gestice gli input testuali nella pagina di load dei salvataggi
     * @param input stringa di testo che contiene i comandi
     */
    private void manageLoadTextInput(String input) {

        //torna alla pagina iniziale
        if (input.equals("back")) {
            vManager.showMenuScreen();
        }
    }

    /**
     * Metodo che gestice il salvataggio dei dati in un nuovo file
     * @param fileName nome del file di salvataggio
     */
    private void setFileSave(String fileName) {
        try {
            //salvataggio del file sulla cartella FileLoad
            fileSave = new File("FileLoad/" + fileName);
            //creazione del file
            fileSave.createNewFile();
            printWriter = new PrintWriter(fileSave);
            //scrittura sul file
            printWriter.println("Save n. " + counterFile + "\n");
            printWriter.close();
            //caricamento del nuovo file su aws
            upload = new uploadFile(fileName);
            System.out.println("Nuovo file creato!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Metodo che gestisce la sovrascrittura di file già esistenti
     * @param name  nome del file da sovrascrivere
     */
    private void setFileSaveOverwrite(String name) {
        try {

            fileSave = new File("FileLoad/" + name);
            printWriter = new PrintWriter(fileSave);
            //scrittura su file
            printWriter.println("Sovrascitto " + counterFile + "\n");
            printWriter.close();
            //caricamento del file sovrascritto
            upload = new uploadFile(name);
            System.out.println("File sovrascritto");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoco che controlla quali file sono già stati scaricati in precedenza e aggiorna la
     * variabile counterFileFirstLoad se un file è già presente
     */
    public void setLoad() {
        File fileLoad;
        userInterfaceHandler.counterLoadLabel.setText("Save n. " + (counterFile+1));
        //array che scorre i nomi di tutti i file di bucket aws
        for (String s : fileNameArray) {
            fileLoad = new File("FileDownload/" + s);

            //se un file non esiste in locale ma è presente nel bucket viene scaricato
            if (!fileLoad.exists()) {
                DownloadFile downloadFIle = new DownloadFile(s, userInterfaceHandler);
            } else {
                counterFileFirstLoad++;
                System.out.println("File " + s + " già presente!");
            }

        }

    }
}


