package UI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Spliterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.stream.events.Characters;

import Board.Board;
import Board.Cell;
import Board.Readfile;
import Board.func;
import Board.reference;
import ManageFile.DownloadFile;
import ManageFile.uploadFile;
import Player.Item;
import Player.Player;
import Player.mostro;

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
    private final String[] fileNameArray = {"Filesave1", "Filesave2", "Filesave3", "Filesave4"};
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
                
                vManager.checkLoad = false;
                vManager.showStartScreen();
                setNewGame();

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
                reference.filereader.ResetDirectory();
                setLoad("Filesave1");
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
            case "returnToMainMenu":
                vManager.showMenuScreen();
                break;
        }
    }

    /**
     * metodo che gestisce gli input testuali da parte dell'utente nel commandTextField
     * @param textInput stringa che identifica il comando scritto dall'utente
     */

    /**
     * Metodo che gestice gli input testuali nella pagina di load dei salvataggi
     * @param input stringa di testo che contiene i comandi
     */
    void manageLoadTextInput(String input) {
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
    //CONTROLA QUI 
    void setFileSavePlayer(String repository) {
        try {
            //salvataggio del file sulla cartella FileLoad, se esiste cartella Filesave2 = filename
            fileSave = new File("FileLoad/"+repository+"/paramplayer.txt");
            //creazione del file
            checkExistingRepository("FileLoad",repository);
            fileSave.createNewFile();
            printWriter = new PrintWriter(fileSave);
            //scrittura sul file
            //manca proprio da salvare l'oggetto del player
            printWriter.println(
                    "Player: " + reference.player.getNome() + "\n" +
                    "Health: " + reference.player.getVita()+ "\n" +
                    "Money: " + reference.player.getMonete()+ "\n" +
                    "Monster_killed: " + reference.player.getMostriuccisi()+ "\n" +
                    "Category: " + reference.player.getCategory()+ "\n" +
                    "Potions: " + reference.player.getNumpozioni() + "\n" +
                    "Weight_Inventory: " + reference.player.getPeso() + "\n" +
                    "key: " + reference.player.getGoldkey()+ "\n" +
                    "Max_damage: " + reference.player.getDannoMaxSpada() + "\n" +
                    "Min_damage: " + reference.player.getDannoMinSpada() + "\n" +
                    "Defense: " + reference.player.getArmour().getDifesa()+ "\n" +
                    "Armour: " + reference.player.getArmourName() + "\n" +
                    "Weapon: " + reference.player.getSpadaName()+ "\n" +
                    "X: " + reference.player.getX() + "\n" +
                    "Y: " + reference.player.getY() + "\n" +
                    "Stanza: " + reference.curr_stanza + "\n" +
                    "Goldkey: " + reference.player.getGoldkey()
            );
            printWriter.close();
            //caricamento del nuovo file su aws
            upload = new uploadFile(repository,"paramplayer.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    void setFileSaveStanze(String repository) {
        try {
            //salvataggio del file sulla cartella FileLoad
            File fileSave;
            //salvo la stanza in cui sono con queste modifiche
            checkExistingRepository("FileLoad",repository);
            reference.filereader.fileToWriteAWS(reference.currentStanza.ss,reference.currentStanza.cellestanza,"src/main/java/Board/Stanzeold/stanza_"+reference.curr_stanza+".txt");
            //creazione del file
            
            File path = new File("src/main/java/Board/Stanzeold/");
            if (!path.exists())
                throw new IllegalArgumentException("La Directory non esiste: ");
            //prendo tutti i file nella cartella stanzeold
            File []allfiles = path.listFiles();
            if(allfiles.length > 0){
                for(int i = 0; i < allfiles.length; i++) {
                    if(allfiles[i].isDirectory()){
                        throw new IllegalArgumentException("E presente una directory illegale: ");
                    }else{
                        //devo copiare contenuto da stanzeold in filesave
                        fileSave = new File("FileLoad/"+repository+"/"+allfiles[i].getName());
                        fileSave.createNewFile();
                        ArrayList<String> ss = new ArrayList<String>();
                        ss = reference.filereader.fileToRead("src/main/java/Board/Stanzeold/"+allfiles[i].getName()); 
                        FileWriter writer = new FileWriter(fileSave);
                        if(ss.size() > 0){
                            for (int j = 0; j < ss.size(); j++) {
                                writer.write(ss.get(j));
                                if((j+1) != ss.size())
                                    writer.write("\n");    
                            }
                        upload = new uploadFile(repository,allfiles[i].getName());
                        }  
                        writer.close();
                    }
                }
            //caricamento del nuovo file su aws
        } 
        } catch (IOException e) {
            fileSave = new File("");
            e.printStackTrace();
        }
    }
    void setFileDaLoadAstanzeOld(String repository) {
        try {
            //salvataggio del file sulla cartella FileLoad
            File fileSave;
            reference.lista_stanze = new ArrayList<>();
            //salvo la stanza in cui sono con queste modifiche
            //creazione del file
            checkExistingRepository("FileLoad",repository);

            File path = new File(repository);
            if (!path.exists())
                throw new IllegalArgumentException("La Directory non esiste: ");
            //prendo tutti i file nella cartella stanzeold
            File []allfiles = path.listFiles();
            if(allfiles.length > 0){
                for(int i = 0; i < allfiles.length; i++) {
                    if(allfiles[i].isDirectory()){
                        throw new IllegalArgumentException("E presente una directory illegale: ");
                    }else if(allfiles[i].getName().compareTo("stanza_") > 0){
                        //devo copiare contenuto da stanzeold in filesave
                        fileSave = new File("src/main/java/Board/Stanzeold/"+allfiles[i].getName());
                        fileSave.createNewFile();
                        ArrayList<String> ss = new ArrayList<String>();
                        ss = reference.filereader.fileToRead(repository+allfiles[i].getName()); 
                        FileWriter writer = new FileWriter(fileSave);
                        if(ss.size() > 0){
                            for (int j = 0; j < ss.size(); j++) {
                                writer.write(ss.get(j));
                                if((j+1) != ss.size())
                                    writer.write("\n");    
                            }
                        }  
                        writer.close();
                        
                        // non mi piace questa conversione pero dovrebbe andare bene
                        if(Board.convertASCIItoNumber((int)(allfiles[i].getName().charAt(7))) != (reference.player.getID())){
                            Board b = new Board(Board.convertASCIItoNumber((int)(allfiles[i].getName().charAt(7))),true);
                            reference.lista_stanze.add(b);
                        }
                            
                    }
                }
                Board playerboard = new Board(reference.player.getID(),true);
                reference.lista_stanze.add(playerboard);
                reference.currentStanza = playerboard;
            //caricamento del nuovo file su aws
        } 
        } catch (IOException e) {
            fileSave = new File("");
            e.printStackTrace();
        }
    }
    void setFileSaveMostri(String repository) {
        try {
            //salvataggio del file sulla cartella FileLoad, se esiste cartella Filesave2 = filename
            fileSave = new File("FileLoad/"+repository+"/listamonster.txt");
            //creazione del file
            checkExistingRepository("FileLoad",repository);
            //controlla che ci siano stanze
            fileSave.createNewFile();
            if(reference.lista_stanze.size() > 0){

                printWriter = new PrintWriter(fileSave);
                for (int i = 0; i < reference.lista_stanze.size(); i++) {
                    //ora controllo che ci siano mostri nella stanza
                    if(reference.lista_stanze.get(i).lista_mostri.size() > 0){
                        //se ci sono procedo con il salvataggio sul file
                        for (int j = 0; j < reference.lista_stanze.get(i).lista_mostri.size(); j++) {
                            mostro mos = reference.lista_stanze.get(i).lista_mostri.get(j);
                            //scrittura sul file
                            //ordine di salvataggio del mostro
                            //nome,health,maxdam,mindam,defense,stanza,x,y,symbol
                            printWriter.println(mos.getNome() + "," + mos.getDanno_max() + "," + mos.getDanno_min() + "," + mos.getDifesa() + "," + mos.getVita() + "," + mos.getIdstanza() + "," + mos.getX() + "," + mos.getY() + "," + mos.getSymbol());
                        }   
                    }
                }
            }
            printWriter.close();
            //caricamento del nuovo file su aws
            upload = new uploadFile(repository,"listamonster.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    void setFileSaveItem(String repository) {
        try {
            //se non esiste folder Fileload la crea e poi controlla se esiste la repository selezionata la crea
            checkExistingRepository("FileLoad",repository);
            //salvataggio del file sulla cartella FileLoad, se esiste cartella Filesave2 = filename
            fileSave = new File("FileLoad/"+repository+"/listaitem.txt");
            //creazione del file

            //controlla che ci siano stanze
            fileSave.createNewFile();
            if(reference.lista_stanze.size() > 0){
                printWriter = new PrintWriter(fileSave);
                for (int i = 0; i < reference.lista_stanze.size(); i++) {
                    //ora controllo che ci siano mostri nella stanza
                    if(reference.lista_stanze.get(i).lista_item.size() > 0){
                        //se ci sono procedo con il salvataggio sul file
                        for (int j = 0; j < reference.lista_stanze.get(i).lista_item.size(); j++) {
                            Item it = reference.lista_stanze.get(i).lista_item.get(j);
                            //scrittura sul file
                            printWriter.println(
                                it.getNome() + "," +
                                it.getAttacco_max() + "," +
                                it.getAttacco_min() + "," +
                                it.getDifesa() + "," +
                                it.isIsSword()+ "," +
                                it.getId_stanza() +"," +
                                it.isHasTake()+ "," +
                                it.getPeso()+ "," +
                                it.getX() + "," +
                                it.getY() + "," +
                                it.getSymbol()
                            );
                        }   
                    }
                }
            }
            printWriter.close();
            //caricamento del nuovo file su aws
            upload = new uploadFile(repository,"listaitem.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * Metodo che gestisce la sovrascrittura di file già esistenti
     * @param name  nome del file da sovrascrivere
     */
    void setFileSavePlayerOverwrite(String repository) {
        try {
            //salvataggio del file sulla cartella FileLoad, se esiste cartella Filesave2 = filename
            fileSave = new File("FileLoad/"+repository+"/paramplayer.txt");
            //creazione del file
            checkExistingRepository("FileLoad",repository);
            fileSave.createNewFile();
            printWriter = new PrintWriter(fileSave);
            //scrittura sul file
            //manca proprio da salvare l'oggetto del player
            printWriter.println(
                    "Player: " + reference.player.getNome() + "\n" +
                    "Health: " + reference.player.getVita()+ "\n" +
                    "Money: " + reference.player.getMonete()+ "\n" +
                    "Monster_killed: " + reference.player.getMostriuccisi()+ "\n" +
                    "Category: " + reference.player.getCategory()+ "\n" +
                    "Potions: " + reference.player.getNumpozioni() + "\n" +
                    "Weight_Inventory: " + reference.player.getPeso() + "\n" +
                    "key: " + reference.player.getGoldkey()+ "\n" +
                    "Max_damage: " + reference.player.getDannoMaxSpada() + "\n" +
                    "Min_damage: " + reference.player.getDannoMinSpada() + "\n" +
                    "Defense: " + reference.player.getArmour().getDifesa()+ "\n" +
                    "Armour: " + reference.player.getArmourName() + "\n" +
                    "Weapon: " + reference.player.getSpadaName()+ "\n" +
                    "X: " + reference.player.getX() + "\n" +
                    "Y: " + reference.player.getY() + "\n" +
                    "Stanza: " + reference.curr_stanza + "\n" +
                    "Goldkey: " + reference.player.getGoldkey()
            );
            printWriter.close();
            //caricamento del nuovo file su aws
            upload = new uploadFile(repository,"paramplayer.txt");
            System.out.println("File sovrascritto");
            reloadFile = true;
            setLoad("paramplayer.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void setFileSaveStanzeOverwrite(String repository) {
        try {
            //salvataggio del file sulla cartella FileLoad
            File fileSave;
            //salvo la stanza in cui sono con queste modifiche
            reference.filereader.fileToWriteAWS(reference.currentStanza.ss, reference.currentStanza.cellestanza,"src/main/java/Board/Stanzeold/stanza_"+reference.curr_stanza+".txt");
            //creazione del file
            checkExistingRepository("FileLoad",repository);
            File path = new File("src/main/java/Board/Stanzeold/");
            if (!path.exists())
                throw new IllegalArgumentException("La Directory non esiste: ");
            //prendo tutti i file nella cartella stanzeold
            File []allfiles = path.listFiles();
            if(allfiles.length > 0){
                for(int i = 0; i < allfiles.length; i++) {
                    if(allfiles[i].isDirectory()){
                        throw new IllegalArgumentException("E presente una directory illegale: ");
                    }else{
                        //devo copiare contenuto da stanzeold in filesave
                        fileSave = new File("FileLoad/"+repository+"/"+allfiles[i].getName());
                        fileSave.createNewFile();
                        ArrayList<String> ss = new ArrayList<String>();
                        ss = reference.filereader.fileToRead("src/main/java/Board/Stanzeold/"+allfiles[i].getName()); 
                        FileWriter writer = new FileWriter(fileSave);
                        if(ss.size() > 0){
                            for (int j = 0; j < ss.size(); j++) {
                                writer.write(ss.get(j));
                                if((j+1) != ss.size())
                                    writer.write("\n");    
                            }
                        upload = new uploadFile(repository,allfiles[i].getName());
                        reloadFile = true;
                        setLoad(allfiles[i].getName());
                        }  
                        writer.close();
                    }
                }
            //caricamento del nuovo file su aws
        } 
        } catch (IOException e) {
            fileSave = new File("");
            e.printStackTrace();
        }
    }
    void setFileSaveMostriOverwrite(String repository) {
        try {
            //salvataggio del file sulla cartella FileLoad, se esiste cartella Filesave2 = filename
            fileSave = new File("FileLoad/"+repository+"/listamonster.txt");
            //creazione del file
            checkExistingRepository("FileLoad",repository);
            //controlla che ci siano stanze
            fileSave.createNewFile();
            if(reference.lista_stanze.size() > 0){

                printWriter = new PrintWriter(fileSave);
                for (int i = 0; i < reference.lista_stanze.size(); i++) {
                    //ora controllo che ci siano mostri nella stanza
                    if(reference.lista_stanze.get(i).lista_mostri.size() > 0){
                        //se ci sono procedo con il salvataggio sul file
                        for (int j = 0; j < reference.lista_stanze.get(i).lista_mostri.size(); j++) {
                            mostro mos = reference.lista_stanze.get(i).lista_mostri.get(j);
                            //scrittura sul file
                            //ordine di salvataggio del mostro
                            //nome,health,maxdam,mindam,defense,stanza,x,y,symbol
                            printWriter.println(mos.getNome() + "," + mos.getDanno_max() + "," + mos.getDanno_min() + "," + mos.getDifesa() + "," + mos.getVita() + "," + mos.getIdstanza() + "," + mos.getX() + "," + mos.getY() + "," + mos.getSymbol());
                        }   
                    }
                }
            }
            printWriter.close();
            //caricamento del nuovo file su aws
            upload = new uploadFile(repository,"listamonster.txt");
            reloadFile = true;
            setLoad("listamonster.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    void setFileSaveItemOverwrite(String repository) {
        try {
            //salvataggio del file sulla cartella FileLoad, se esiste cartella Filesave2 = filename
            fileSave = new File("FileLoad/"+repository+"/listaitem.txt");
            //creazione del file
            checkExistingRepository("FileLoad",repository);
            //controlla che ci siano stanze
            fileSave.createNewFile();
            if(reference.lista_stanze.size() > 0){
                printWriter = new PrintWriter(fileSave);
                for (int i = 0; i < reference.lista_stanze.size(); i++) {
                    //ora controllo che ci siano mostri nella stanza
                    if(reference.lista_stanze.get(i).lista_item.size() > 0){
                        //se ci sono procedo con il salvataggio sul file
                        for (int j = 0; j < reference.lista_stanze.get(i).lista_item.size(); j++) {
                            Item it = reference.lista_stanze.get(i).lista_item.get(j);
                            //scrittura sul file
                            printWriter.println(
                                it.getNome() + "," +
                                it.getAttacco_max() + "," +
                                it.getAttacco_min() + "," +
                                it.getDifesa() + "," +
                                it.isIsSword()+ "," +
                                it.getId_stanza() +"," +
                                it.isHasTake()+ "," +
                                it.getPeso()+ "," +
                                it.getX() + "," +
                                it.getY() + "," +
                                it.getSymbol()
                            );
                        }   
                    }
                }
            }
            printWriter.close();
            //caricamento del nuovo file su aws
            upload = new uploadFile(repository,"listaitem.txt");
            reloadFile = true;
            setLoad("listaitem.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    /**
     * Metoco che controlla quali file sono già stati scaricati in precedenza e aggiorna la
     * variabile counterFileFirstLoad se un file è già presente
     */
    public void setLoad(String name) {

        File fileLoad;
        userInterfaceHandler.counterLoadLabel.setText("Save n. " + (counterFile+1));
        //array che scorre i nomi di tutti i file di bucket aws
        
        for (String s : fileNameArray) {
            fileLoad = new File("FileDownload/"+s+"/"+name);
            checkExistingRepository("FileDownload",s);
            //se un file non esiste in locale ma è presente nel bucket viene scaricato
            if (!fileLoad.exists() || reloadFile) {
                if(reloadFile){
                    fileLoad.delete();
                    reloadFile = false;
                }
                DownloadFile downloadFIle = new DownloadFile(s, userInterfaceHandler);
            } else {
                //dovro fare il controllo tra il numero di file salvati sul bucket e quelli in locale e dici 
                //gia presente o meno
                counterFileFirstLoad++;
            }

        }

        reloadFile = false;

    }
    //metodo che quando schiacci load lui carica tutti i file dal bucket
    /* public void setLoadAll(String name) {
        File fileLoad;
        userInterfaceHandler.counterLoadLabel.setText("Save n. " + (counterFile+1));
        //array che scorre i nomi di tutti i file di bucket aws
        for (String s : fileNameArray) {
            fileLoad = new File("FileDownload/"+s+"/"+name);

            //se un file non esiste in locale ma è presente nel bucket viene scaricato
            if (!fileLoad.exists() || reloadFile) {
                if(reloadFile){
                    fileLoad.delete();
                    reloadFile = false;
                }
                DownloadFile downloadFIle = new DownloadFile(s,name, userInterfaceHandler);
            } else {
                //dovro fare il controllo tra il numero di file salvati sul bucket e quelli in locale e dici 
                //gia presente o meno
                counterFileFirstLoad++;
            }

        }

        reloadFile = false;

    } */
   //
   // Controlla che le repository esistano, se non esistono o subiscono modifiche ne creo di nuove
   //
   public static void checkExistingRepository(String mainrep, String repository){
        File path = new File(mainrep);
        if (!path.exists()){
            path.mkdir();
        }
        File rep = new File(mainrep+"/"+repository);
        if (!rep.exists()){
            rep.mkdir();
        }
   }
//metodo per salvare i dati ottenuti dai file scaricati dal bucket aws
    public void setupLoad(int k)  {
        setNewGame();
        try {
            int counter = 1;
            //carico parametri giocatore
            File fileplayer = new File("FileLoad/Filesave"+k+"/paramplayer.txt");
            Pattern pattern = Pattern.compile(": (.+)");
            BufferedReader br = new BufferedReader(new FileReader(fileplayer));

            File fileitem = new File("FileLoad/Filesave"+k+"/listaitem.txt");
            BufferedReader britem = new BufferedReader(new FileReader(fileitem));

            File filemonster = new File("FileLoad/Filesave"+k+"/listamonster.txt");
            BufferedReader brmonster = new BufferedReader(new FileReader(filemonster));

            String lineplayer;
            int difesaplayer = 0;
            while ((lineplayer = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(lineplayer);
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
                        reference.player.setNum_pozioni(Integer.parseInt(matcher.group(1)));  
                    else if(counter == 7)
                        reference.player.setPeso(0);
                    else if(counter == 8)
                        reference.player.setKeyLoad(Integer.parseInt((matcher.group(1))));
                    else if(counter == 9)
                        reference.player.setMaxDamage(Integer.parseInt((matcher.group(1))));
                    else if(counter == 10)
                        reference.player.setMinDamage(Integer.parseInt((matcher.group(1))));
                    else if(counter == 11)
                        difesaplayer = Integer.parseInt((matcher.group(1)));
                    else if(counter == 12)
                        reference.player.addArmour(new Item("armatura",0,0,difesaplayer,false,reference.curr_stanza,true,30));
                    else if(counter == 13)
                        reference.player.addSpada(new Item("spada", reference.player.getDannoMaxSpada(), reference.player.getDannoMinSpada(), 0, true, reference.curr_stanza, true,20));
                    else if(counter == 14)
                        reference.player.setX(Integer.parseInt((matcher.group(1))));
                    else if(counter == 15)
                        reference.player.setY(Integer.parseInt((matcher.group(1))));
                    else if(counter == 16)
                        reference.player.setID(Integer.parseInt((matcher.group(1))));
                    else if(counter == 17)
                        reference.player.setGoldkeyAWS(Integer.parseInt((matcher.group(1))));
               }
               counter++;
            }
            br.close();
            //lista mostri e lista item uguale a player stesso modus operandi
            File filedirectory = new File("FileLoad/Filesave"+k+"/");
            File[] all_files = filedirectory.listFiles();
            ArrayList<mostro> listamostri = new ArrayList<>();
            ArrayList<Item> listaitem = new ArrayList<>();

            if(all_files.length > 0){
                reference.filereader.ResetDirectory();
                for (int i = 0; i < all_files.length; i++) {
                    
                    if(all_files[i].getName().compareTo("listamonster.txt") == 0){
                        //qui spostiamo riempimento mostri
                        String linemonster;
                        while ((linemonster=brmonster.readLine()) != null) {
                            String[] temp2 = linemonster.split(",");
                            mostro m = new mostro(temp2[0],Integer.parseInt(temp2[1]),Integer.parseInt(temp2[2]),Integer.parseInt(temp2[3]),Integer.parseInt(temp2[4]),Integer.parseInt(temp2[5]),Integer.parseInt(temp2[6]),Integer.parseInt(temp2[7]),temp2[8].charAt(0));
                            listamostri.add(m);
                        }
                    }else if(all_files[i].getName().compareTo("listaitem.txt") == 0){
                        //qui spostiamo riempimento item
                        String lineitem;
                        while ((lineitem=britem.readLine()) != null) {
                            String[] temp2 = lineitem.split(",");
                            Item m = new Item(temp2[0],Integer.parseInt(temp2[1]),Integer.parseInt(temp2[2]),Integer.parseInt(temp2[3]),Boolean.parseBoolean(temp2[4]),Integer.parseInt(temp2[5]),Boolean.parseBoolean(temp2[6]),Integer.parseInt(temp2[7]),Integer.parseInt(temp2[8]),Integer.parseInt(temp2[9]),temp2[10].charAt(0));
                            listaitem.add(m);
                        }
                    }
                }
                britem.close();
                brmonster.close();
                setFileDaLoadAstanzeOld("FileLoad/Filesave"+k+"/");
            }
            if(reference.lista_stanze.size() > 0){
                for (int i = 0; i < reference.lista_stanze.size(); i++) {
                    for (int j = 0; j < listamostri.size(); j++) {
                        if(reference.lista_stanze.get(i).getid() == listamostri.get(j).getIdstanza())
                            reference.lista_stanze.get(i).lista_mostri.add(listamostri.get(j));
                    }
                    for (int j = 0; j < listaitem.size(); j++) {
                        if(reference.lista_stanze.get(i).getid() == listaitem.get(j).getId_stanza())
                            reference.lista_stanze.get(i).lista_item.add(listaitem.get(j));
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void setNewGame(){
        userInterfaceHandler.textField.setText("");
        reference.ui.mainCharacterButtonPanel.clearSelection();
        reference.player = new Player();
        reference.filereader = new Readfile();
        reference.mostrorun = new mostro();
        reference.functions = new func();
        reference.item = new Item();
        reference.currentStanza = new Board(1);
        reference.filereader.ResetDirectory();
    }

    private void checkSlotExistence(String directory, int i){

        checkExistingRepository("FileLoad",directory);
        File fileExistCheck = new File("FileLoad/" + directory);
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