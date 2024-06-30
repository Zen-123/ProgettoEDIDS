package Board;

import java.util.ArrayList;
import Player.Item;
import Player.mostro;
public class Board{

    public static int getDrive_to_boss() {
        return drive_to_boss;
    }
    public ArrayList<ArrayList<Cell>> cellestanza;
    public ArrayList<String> ss;
    public ArrayList<Item> lista_item;
    public ArrayList<mostro> lista_mostri;
    private int row;
    private int column;
    public int ID_Stanza;
    private int drive_to_N; 
    private int drive_to_E;
    private int drive_to_W; 
    private int drive_to_S;
    private static int drive_to_boss=0;

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
    public Board(int id_stanza, boolean verification){
        reference.alreadybeen = verification;
        this.ss = new ArrayList<String>();
        String temp = "src/main/java/Board/Stanzeold/stanza_"+id_stanza+".txt";
        this.ID_Stanza = id_stanza;
        reference.curr_stanza = id_stanza;
        ss = reference.filereader.fileToRead(temp); 
        this.column = ss.size()-1;
        this.row = ss.get(0).length();
        this.cellestanza = new ArrayList<ArrayList<Cell>>();
        this.lista_item = new ArrayList<Item>();
        this.lista_mostri = new ArrayList<mostro>();
        populateBoard(ss,true);
    }
    //riempie la board=arraylist di celle,che poi verra stampata da printcomponent
    //ver == false allora viene letto da un nuovo file , ver == true controlla file vecchio
    public void populateBoard(ArrayList<String> strings,boolean ver){
        //serve x assegnare alla stanza presente che porta ti porta nella next stanza
        this.setDrive_to_N(strings.get(strings.size()-1).charAt(0));
        this.setDrive_to_E(strings.get(strings.size()-1).charAt(1));
        this.setDrive_to_W(strings.get(strings.size()-1).charAt(2));
        this.setDrive_to_S(strings.get(strings.size()-1).charAt(3));
        // strings.remove(strings.get(strings.size()-1));
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
                        reference.player.setStanza_presente(reference.curr_stanza);
                        cellestanza.get(i).add(Cell.PLAYER);
                    break;
                    default:
                        break;
                }   
            } 
        }   
    }
    public Cell getPortaNord(int x,int y){
        return cellestanza.get(x).get(y);
    }
    public Cell getCella(int x,int y){
        return cellestanza.get(y).get(x);
    }

    public char getSsymbol(int x, int y){
        return cellestanza.get(y).get(x).getSymbol();
    }
    public void setSsymbol(int x, int y,char simbol){
        this.cellestanza.get(y).get(x).setSymbol(simbol);
    }
    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }
    public int getid(){
        return ID_Stanza;
    }
    public int getDrive_to_N() {
        return drive_to_N;
    }
    public int getDrive_to_E() {
        return drive_to_E;
    }
    public int getDrive_to_S() {
        return drive_to_S;
    }
    public int getDrive_to_W() {
        return drive_to_W;
    }
    public void setID_Stanza(int ID_Stanza) {
        this.ID_Stanza = ID_Stanza;
    }
    //metodi che ti dicono in quale stanza deve spostarsi player
    public void setDrive_to_N(int drive_to_N) {
        this.drive_to_N = convertASCIItoNumber(drive_to_N);
    }

    public void setDrive_to_E(int drive_to_E) {
        this.drive_to_E = convertASCIItoNumber(drive_to_E);
    }

    public void setDrive_to_W(int drive_to_W) {
        this.drive_to_W = convertASCIItoNumber(drive_to_W);
    }

    public void setDrive_to_S(int drive_to_S) {
        this.drive_to_S = convertASCIItoNumber(drive_to_S);
    }
    public int convertASCIItoNumber(int asciinumber){
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
                converted = 0;
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


