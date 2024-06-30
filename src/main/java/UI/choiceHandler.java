package UI;
import Board.Board;
import Board.Readfile;
import Board.Cell;
import ManageFile.DownloadFile;
import ManageFile.uploadFile;
import Board.reference;
import Player.Player;
import Player.mostro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Player.Item;

import static UI.UI.gameB;

/**
 * Classe che gestice gli eventi sulla UI, implementa la classe astratta ActionListener per la gestione degli eventi
 */
public class choiceHandler implements ActionListener {
    //variabili private

    //contatore generale per i 4 slot di salvataggio
    public static int counterFile = 0;
    //contatore che va a contare i file già scaricati al primo caricamento del gioco
    public int counterFileFirstLoad = 0;
    private final UI userInterfaceHandler;
    visibilityManager vManager;
    uploadFile upload;
    private File fileSave;

    private PrintWriter printWriter;
    private final String[] fileNameArray = {"Filesave 1.txt", "Filesave 2.txt", "Filesave 3.txt", "Filesave 4.txt"};
    public boolean reloadFile = false;

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
                setNewGame();
                vManager.checkLoad = false;
                vManager.showStartScreen();


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
                userInterfaceHandler.commandTextField.setText("");
                vManager.showGameScreen();
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
            /*
            case "input":
                String inputText = userInterfaceHandler.commandTextField.getText();
                if (!Objects.equals(inputText, "")) {
                    manageTextInput(inputText);
                }
                break;*/

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
        String temp;
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
    void manageLoadTextInput(String input) {
        File fileExistCheck;
        userInterfaceHandler.commandLoadTextField.setText("");
        //torna alla pagina iniziale
        if (input.toLowerCase().equals("back") || input.toLowerCase().equals("exit")) {
            vManager.showMenuScreen();
        }

        if(input.toLowerCase().equals("slot 1")){
            checkSlotExistence(fileNameArray[0], 1);


        }
        if(input.toLowerCase().equals("slot 2")){
            checkSlotExistence(fileNameArray[1], 2);
        }
        if(input.toLowerCase().equals("slot 3")){
            checkSlotExistence(fileNameArray[2],3);

        }
        if(input.toLowerCase().equals("slot 4")){
            checkSlotExistence(fileNameArray[3],4);
        }

    }

    /**
     * Metodo che gestice il salvataggio dei dati in un nuovo file
     * @param fileName nome del file di salvataggio
     */
    void setFileSave(String fileName) {
        try {
            //salvataggio del file sulla cartella FileLoad
            fileSave = new File("FileLoad/" + fileName);
            //creazione del file
            fileSave.createNewFile();
            printWriter = new PrintWriter(fileSave);
            //scrittura sul file
            printWriter.println(
                    "Player: " + reference.player.getNome() + "\n" +
                    "Health: " + reference.player.getVita()+ "\n" +
                    "Money: " + reference.player.getMonete()+ "\n" +
                    "Monster_killed: " + reference.player.getMostriuccisi()+ "\n" +
                    "Category: " + reference.player.getCategory()+ "\n" +
                    "Weapon: " + reference.player.getSpadaName()+ "\n" +
                    "Potions: " + reference.player.getNumpozioni() + "\n" +
                    "Weight_Inventory: " + reference.player.getPeso() + "\n" +
                    "Armour: " + reference.player.getArmourName() + "\n" +
                    "key: " + reference.player.getGoldkey()+ "\n" +
                    "Max_damage: " + reference.player.getDannoMaxSpada() + "\n" +
                    "Min_damage: " + reference.player.getDannoMinSpada()
            );
            printWriter.close();
            //caricamento del nuovo file su aws
            upload = new uploadFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Metodo che gestisce la sovrascrittura di file già esistenti
     * @param name  nome del file da sovrascrivere
     */
    void setFileSaveOverwrite(String name) {
        try {
            fileSave = new File("FileLoad/" + name);
            printWriter = new PrintWriter(fileSave);
            //scrittura su file
            printWriter.println(
                    "Player: " + reference.player.getNome() + "\n" +
                    "Health: " + reference.player.getVita()+ "\n" +
                    "Money: " + reference.player.getMonete()+ "\n" +
                    "Monster_killed: " + reference.player.getMostriuccisi()+ "\n" +
                    "Category: " + reference.player.getCategory()+ "\n" +
                    "Weapon: " + reference.player.getSpadaName()+ "\n" +
                    "Potions: " + reference.player.getNumpozioni() + "\n" +
                    "Weight_Inventory: " + reference.player.getPeso() + "\n" +
                    "Armour: " + reference.player.getArmourName() + "\n" +
                    "key: " + reference.player.getGoldkey()+ "\n" +
                    "Max_damage: " + reference.player.getDannoMaxSpada() + "\n" +
                    "Min_damage: " + reference.player.getDannoMinSpada() + "\n" +
                    "Current_room: " + reference.currentStanza.ID_Stanza
            );
            printWriter.close();
            //caricamento del file sovrascritto
            upload = new uploadFile(name);
            System.out.println("File sovrascritto");
            reloadFile = true;
            setLoad();
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
        int i=1;
        userInterfaceHandler.counterLoadLabel.setText("Save n. " + (counterFile+1));
        //array che scorre i nomi di tutti i file di bucket aws
        for (String s : fileNameArray) {
            fileLoad = new File("FileDownload/" + s);

            //se un file non esiste in locale ma è presente nel bucket viene scaricato
            if (!fileLoad.exists() || reloadFile) {
                if(reloadFile){
                    fileLoad.delete();
                    reloadFile = false;
                }
                DownloadFile downloadFIle = new DownloadFile(s, userInterfaceHandler);
            } else {

                counterFileFirstLoad++;
                System.out.println("File " + s + " già presente!");
                i++;
            }

        }

        reloadFile = false;

    }
//metodo per salvare i dati ottenuti dai file scaricati dal bucket aws
    public void setupLoad(int k)  {

        try {
            int counter = 1;
            File file = new File("FileLoad/Filesave "+k+".txt");
            Pattern pattern = Pattern.compile(": (.+)");
            BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                //viene letta ogni riga del file e per ogni riga viene settato il valore corrispondente nelle stats del player
                while ((line = br.readLine()) != null) {
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {

                    if(counter == 1)
                        reference.player.setNome(matcher.group(1));
                    else if(counter == 2)
                        reference.player.setVita(Integer.parseInt(matcher.group(1)));
                    else if(counter == 3)
                        reference.player.setMoneteLoad(Integer.parseInt(matcher.group(1)));
                    else if(counter == 4)
                        reference.player.setMostriUccisiLoad(Integer.parseInt(matcher.group(1)));
                    else if(counter == 5)
                        reference.player.setCategory(matcher.group(1));
                    else if(counter == 6)
                        reference.player.setSpadaName(matcher.group(1));
                    else if(counter == 7)
                        reference.player.setNum_pozioni(Integer.parseInt(matcher.group(1)));
                    else if(counter == 8)
                        reference.player.setPeso(Integer.parseInt(matcher.group(1)));
                    else if(counter == 9)
                        reference.player.setArmourName((matcher.group(1)));
                    else if(counter == 10)
                        reference.player.setKeyLoad(Integer.parseInt((matcher.group(1))));
                    else if(counter == 11)
                        reference.player.setMaxDamage(Integer.parseInt((matcher.group(1))));
                    else if(counter == 12)
                        reference.player.setMinDamage(Integer.parseInt((matcher.group(1))));
                    else if(counter == 13){

                        reference.currentStanza = new Board(Integer.parseInt((matcher.group(1))), true);
                        //Forse problema con board parametrizzata con flag
                        //se si usa board senza flag problema: non salva i file stanza_n.txt ma salva le statistiche del giocatore
                        reference.curr_stanza = Integer.parseInt((matcher.group(1)));
                        if( reference.curr_stanza == 2){
                            for (int row = 0; row < reference.currentStanza.cellestanza.size(); row++) {
                                for (int col = 0; col < reference.currentStanza.cellestanza.get(row).size() ; col++) {
                                    Cell cell = reference.currentStanza.cellestanza.get(row).get(col);
                                    if (cell.getSymbol() == 'E' ) {
                                        reference.player.setX(col-1);
                                        reference.player.setY(row);
                                        reference.player.setspawnTo('E');
                                        reference.currentStanza.cellestanza.get(row).set(col-1,Cell.PLAYER);
                                        break;
                                    }
                                }
                            }
                        }else if( reference.curr_stanza == 3){
                            for (int row = 0; row < reference.currentStanza.cellestanza.size(); row++) {
                                for (int col = 0; col < reference.currentStanza.cellestanza.get(row).size() ; col++) {
                                    Cell cell = reference.currentStanza.cellestanza.get(row).get(col);
                                    if (cell.getSymbol() == 'S' ) {
                                        reference.player.setX(col);
                                        reference.player.setY(row-1);
                                        reference.player.setspawnTo('S');
                                        reference.currentStanza.cellestanza.get(row-1).set(col,Cell.PLAYER);

                                        break;
                                    }
                                }
                            }
                        }else if( reference.curr_stanza == 4){
                            for (int row = 0; row < reference.currentStanza.cellestanza.size(); row++) {
                                for (int col = 0; col < reference.currentStanza.cellestanza.get(row).size() ; col++) {
                                    Cell cell = reference.currentStanza.cellestanza.get(row).get(col);
                                    if (cell.getSymbol() == 'E' ) {
                                        reference.player.setX(col-1);
                                        reference.player.setY(row);
                                        reference.player.setspawnTo('E');
                                        reference.currentStanza.cellestanza.get(row).set(col-1,Cell.PLAYER);
                                        break;
                                    }
                                }
                            }
                        }
                        else if( reference.curr_stanza == 5){
                            for (int row = 0; row < reference.currentStanza.cellestanza.size(); row++) {
                                for (int col = 0; col < reference.currentStanza.cellestanza.get(row).size() ; col++) {
                                    Cell cell = reference.currentStanza.cellestanza.get(row).get(col);
                                    if (cell.getSymbol() == 'S' ) {
                                        reference.player.setX(col);
                                        reference.player.setY(row-1);
                                        reference.player.setspawnTo('S');
                                        reference.currentStanza.cellestanza.get(row-1).set(col,Cell.PLAYER);
                                        break;
                                    }
                                }
                            }
                        }else if( reference.curr_stanza == 6){
                            for (int row = 0; row < reference.currentStanza.cellestanza.size(); row++) {
                                for (int col = 0; col < reference.currentStanza.cellestanza.get(row).size() ; col++) {
                                    Cell cell = reference.currentStanza.cellestanza.get(row).get(col);
                                    if (cell.getSymbol() == 'E' ) {
                                        reference.player.setX(col-1);
                                        reference.player.setY(row);
                                        reference.player.setspawnTo('E');
                                        reference.currentStanza.cellestanza.get(row).set(col-1,Cell.PLAYER);
                                        break;
                                    }
                                }
                            }
                        }else if( reference.curr_stanza == 7){
                            for (int row = 0; row < reference.currentStanza.cellestanza.size(); row++) {
                                for (int col = 0; col < reference.currentStanza.cellestanza.get(row).size() ; col++) {
                                    Cell cell = reference.currentStanza.cellestanza.get(row).get(col);
                                    if (cell.getSymbol() == 'E' ) {
                                        reference.player.setX(col-1);
                                        reference.player.setY(row);
                                        reference.player.setspawnTo('E');
                                        reference.currentStanza.cellestanza.get(row).set(col-1,Cell.PLAYER);
                                        break;
                                    }
                                }
                            }
                        }else if( reference.curr_stanza == 8){
                            for (int row = 0; row < reference.currentStanza.cellestanza.size(); row++) {
                                for (int col = 0; col < reference.currentStanza.cellestanza.get(row).size() ; col++) {
                                    Cell cell = reference.currentStanza.cellestanza.get(row).get(col);
                                    if (cell.getSymbol() == 'S' ) {
                                        reference.player.setX(col);
                                        reference.player.setY(row-1);
                                        reference.player.setspawnTo('S');
                                        reference.currentStanza.cellestanza.get(row-1).set(col,Cell.PLAYER);
                                        break;
                                    }
                                }
                            }
                        }else if( reference.curr_stanza == 9){
                            for (int row = 0; row < reference.currentStanza.cellestanza.size(); row++) {
                                for (int col = 0; col < reference.currentStanza.cellestanza.get(row).size() ; col++) {
                                    Cell cell = reference.currentStanza.cellestanza.get(row).get(col);
                                    if (cell.getSymbol() == 'S' ) {
                                        reference.player.setX(col-1);
                                        reference.player.setY(row-1);
                                        reference.player.setspawnTo('S');
                                        reference.currentStanza.cellestanza.get(row-1).set(col-1,Cell.PLAYER);
                                        break;
                                    }
                                }
                            }
                        }else if( reference.curr_stanza == 0){
                            for (int row = 0; row < reference.currentStanza.cellestanza.size(); row++) {
                                for (int col = 0; col < reference.currentStanza.cellestanza.get(row).size() ; col++) {
                                    Cell cell = reference.currentStanza.cellestanza.get(row).get(col);
                                    if (cell.getSymbol() == 'G' ) {
                                        reference.player.setX(col-1);
                                        reference.player.setY(row);
                                        reference.player.setspawnTo('G');
                                        reference.currentStanza.cellestanza.get(row).set(col-1,Cell.PLAYER);
                                        break;
                                    }
                                }
                            }
                        }
                        System.out.println("final room: " + reference.currentStanza.ID_Stanza);


                    }
                    counter++;
                }
            }
        reference.functions.updateMonsterPosition();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void setNewGame(){
        userInterfaceHandler.textField.setText("");
        reference.player = new Player();
        reference.filereader = new Readfile();
        reference.mostrorun = new mostro();
        reference.currentStanza = new Board(1);
    }

    private void checkSlotExistence(String fileName, int i){
        File fileExistCheck = new File("FileLoad/" + fileName);
        try {
            if(fileExistCheck.exists()){
                vManager.checkLoad = true;
                reference.ui.commandTextField.setText("");
                setupLoad(i);
                vManager.showGameScreen();
            }else{
                reference.ui.setAlertNoSaveSlot();
            }

        }catch (Exception e){
            System.out.println("File non trovato!");
            vManager.showMenuScreen();
        }
    }
}