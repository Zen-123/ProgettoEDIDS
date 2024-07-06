package Board;

import java.util.ArrayList;
import Player.Item;
import Player.mostro;
import java.io.File;

/**
 * Rappresenta la struttura e la logica di una stanza del gioco.
 * Gestisce la disposizione delle celle, degli oggetti e dei mostri all'interno della stanza.
 */
public class Board{
    /** Matrice di celle che compongono la stanza. */
    public ArrayList<ArrayList<Cell>> cellestanza;

    /** Lista di stringhe rappresentanti la struttura della stanza. */
    public ArrayList<String> ss;

    /** Lista degli oggetti presenti nella stanza. */
    public ArrayList<Item> lista_item;

    /** Lista dei mostri presenti nella stanza. */
    public ArrayList<mostro> lista_mostri;

    /** Numero di righe della stanza. */
    public int row;

    /** Numero di colonne della stanza. */
    public int column;

    /** Identificativo univoco della stanza. */
    public int ID_Stanza;

    /** Identificativo della stanza a nord. */
    private int drive_to_N;

    /** Identificativo della stanza a est. */
    private int drive_to_E;

    /** Identificativo della stanza a ovest. */
    private int drive_to_W;

    /** Identificativo della stanza a sud. */
    private int drive_to_S;

    /** Identificativo della stanza del boss. */
    private static int drive_to_boss = 0;
    /**
     * Costruttore per creare una nuova stanza.
     * @param id_stanza Identificativo della stanza da creare.
     */
    public Board(int id_stanza){
        this.ss = new ArrayList<String>();
        
        String temp = "src/main/java/Board/Stanze/stanza_"+id_stanza+".txt";
        this.ID_Stanza = id_stanza;
        reference.curr_stanza = id_stanza;
        ss = reference.filereader.fileToRead(temp); 
        this.column = ss.size()-1;
        this.row = ss.get(0).length();
        this.cellestanza = new ArrayList<ArrayList<Cell>>();
        this.lista_item = new ArrayList<Item>();
        this.lista_mostri = new ArrayList<mostro>();
        populateBoard(ss,false);
    }
    /**
     * Costruttore per caricare una stanza già visitata.
     * @param id_stanza Identificativo della stanza da caricare.
     * @param verification Flag che indica se la stanza è già stata visitata.
     */
    public Board(int id_stanza, boolean verification){
        reference.alreadybeen = verification;
        this.ss = new ArrayList<String>();
        this.ID_Stanza = id_stanza;
        String temp = "src/main/java/Board/Stanzeold/stanza_"+id_stanza+".txt";
        reference.curr_stanza = id_stanza;
        ss = reference.filereader.fileToRead(temp); 
        this.column = ss.size()-1;
        this.row = ss.get(0).length();
        this.cellestanza = new ArrayList<ArrayList<Cell>>();
        this.lista_item = new ArrayList<Item>();
        this.lista_mostri = new ArrayList<mostro>();
        populateBoard(ss,true);
    }
    /**
     * Popola la board con celle, oggetti e mostri.
     * @param strings Lista di stringhe che rappresentano la struttura della stanza.
     * @param ver Flag che indica se si sta caricando una stanza già visitata (true) oppure una stanza nuova (false).
     */
    public void populateBoard(ArrayList<String> strings,boolean ver){
        //serve x assegnare alla stanza presente che porta ti porta nella next stanza
        this.setDrive_to_N(strings.get(strings.size()-1).charAt(0));
        this.setDrive_to_E(strings.get(strings.size()-1).charAt(1));
        this.setDrive_to_W(strings.get(strings.size()-1).charAt(2));
        this.setDrive_to_S(strings.get(strings.size()-1).charAt(3));

        //riempimento del array di celle dalla stringa del readfile
        for (int i = 0; i < strings.size(); i++) {
            cellestanza.add(new ArrayList<Cell>());
            char[] array = strings.get(i).toCharArray();
            for (int j = 0; j < array.length; j++) {
                switch(array[j]) {
                    case '#':
                        cellestanza.get(i).add(Cell.WALL);
                    break;
                    case '.':
                        cellestanza.get(i).add(Cell.FREE);
                    break;
                    case 'N':
                        cellestanza.get(i).add(Cell.NORD);
                    break;
                    case 'S':              
                        cellestanza.get(i).add(Cell.SUD);
                    break;
                    case 'W':
                        cellestanza.get(i).add(Cell.OVEST);
                    break;
                    case 'E':
                        cellestanza.get(i).add(Cell.EST);
                    break;
                    case 'G':
                        cellestanza.get(i).add(Cell.BOSSROOM);
                    break;
                    case 'I':
                        if(ver == false){
                            lista_item.add(func.generateItem(j,i));
                        }
                        cellestanza.get(i).add(Cell.ITEM);
                    break;
                    case 'M':
                        if(ver == false){
                            lista_mostri.add(func.generateMonster(j,i));
                        }
                        cellestanza.get(i).add(Cell.MONSTER);
                    break;
                    case 'B':
                        //è il boss
                        lista_mostri.add(func.generateBoss(j,i));
                        cellestanza.get(i).add(Cell.BOSS);
                    break;
                    case 'f':
                        //chiave x porte
                        cellestanza.get(i).add(Cell.KEY);
                    break;
                    case 'F':
                        //chiave oro per porta oro
                        cellestanza.get(i).add(Cell.GOLDKEY);
                    break;
                    case 'P':
                        //trovato porta di una stanza
                        cellestanza.get(i).add(Cell.PORTA);
                    break;
                    case 'H':
                        //trovato porta di una stanza
                        cellestanza.get(i).add(Cell.POZIONE);
                    break;
                    case 'C':
                        //trovato porta di una stanza
                        cellestanza.get(i).add(Cell.COIN);
                    break;
                    case 'A':
                        reference.player.setX(j);
                        reference.player.setY(i);
                        cellestanza.get(i).add(Cell.PLAYER);
                    break;
                    default:
                        break;
                }   
            } 
        }   
    }
    /**
     * Restituisce il simbolo di una cella specifica.
     * @param x Coordinata x della cella.
     * @param y Coordinata y della cella.
     * @return Il carattere che rappresenta il simbolo della cella.
     */
    public char getSsymbol(int x, int y){
        return cellestanza.get(y).get(x).getSymbol();
    }
    /**
     * Imposta il simbolo di una cella specifica.
     * @param x Coordinata x della cella.
     * @param y Coordinata y della cella.
     * @param simbol Il nuovo simbolo da assegnare alla cella.
     */
    public void setSsymbol(int x, int y,char simbol){
        this.cellestanza.get(y).get(x).setSymbol(simbol);
    }

    /**
     * Ritorna la riga della stanza
     * @return riga della stanza
     */
    public int getRow(){
        return row;
    }
    /**
     * Ritorna la colonna della stanza
     * @return colonna della stanza
     */
    public int getColumn(){
        return column;
    }

    /**
     * Ritorna l'ID della stanza'
     * @return ID_stanza della stanza
     */
    public int getid(){
        return ID_Stanza;
    }

    /**
     * Ritorna identificatore della stanza a nord
     * @return identificatore stanza a nord
     */
    public int getDrive_to_N() {
        return drive_to_N;
    }
    /**
     * Ritorna identificatore della stanza a est
     * @return identificatore stanza a est
     */
    public int getDrive_to_E() {
        return drive_to_E;
    }
    /**
     * Ritorna identificatore della stanza a sud
     * @return identificatore stanza a sud
     */
    public int getDrive_to_S() {
        return drive_to_S;
    }
    /**
     * Ritorna identificatore della stanza a ovest
     * @return identificatore stanza a ovest
     */
    public int getDrive_to_W() {
        return drive_to_W;
    }
    /**
     * Ritorna identificatore della stanza del boss
     * @return identificatore stanza del boss
     */
    public static int getDrive_to_boss() {
        return drive_to_boss;
    }

    /**
     * Imposta la stanza in cui deve spostarsi il giocatore, in questo caso la stanza a nord
     * @param drive_to_N  identifica la stanza a nord
     */
    public void setDrive_to_N(int drive_to_N) {
        this.drive_to_N = convertASCIItoNumber(drive_to_N);
    }
    /**
     * Imposta la stanza in cui deve spostarsi il giocatore, in questo caso la stanza a est
     * @param drive_to_E  identifica la stanza a est
     */
    public void setDrive_to_E(int drive_to_E) {
        this.drive_to_E = convertASCIItoNumber(drive_to_E);
    }
    /**
     * Imposta la stanza in cui deve spostarsi il giocatore, in questo caso la stanza a ovest
     * @param drive_to_W  identifica la stanza a ovest
     */
    public void setDrive_to_W(int drive_to_W) {
        this.drive_to_W = convertASCIItoNumber(drive_to_W);
    }
    /**
     * Imposta la stanza in cui deve spostarsi il giocatore, in questo caso la stanza a sud
     * @param drive_to_S  identifica la stanza
     */
    public void setDrive_to_S(int drive_to_S) {
        this.drive_to_S = convertASCIItoNumber(drive_to_S);
    }
    /**
     * Converte un carattere ASCII in un numero intero.
     * @param asciinumber Il valore ASCII da convertire.
     * @return Il numero intero corrispondente.
     */
    public static int convertASCIItoNumber(int asciinumber){
        int converted;
        switch(asciinumber) {
            case 48:
                converted=0;
                break;
            case 49:
                converted=1;   
                break;
            case 50:
                converted=2;    
                break;
            case 51:
                converted=3;    
                break;
            case 52:
                converted=4;    
                break;
            case 53:
                converted=5;    
                break;
            case 54:
                converted=6;    
                break;
            case 55:
                converted=7;    
                break;
            case 56:
                converted=8;    
                break;
            case 57:
                converted=9;    
                break;
            default:
                converted = asciinumber;
                break;
        }
        return converted;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setColumn(int column) {
        this.column = column;
    }
}


