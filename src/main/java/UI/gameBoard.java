package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Board.Board;
import Board.Cell;
import Board.reference;

public class gameBoard extends JPanel implements KeyListener {
    

    public gameBoard(){
        addKeyListener(this);
        this.setFocusable(true);
    }

    @Override
	protected void paintComponent(Graphics g) {
		//metodi che si richiamano su se stessi per ricolorare ogni volta che ce un evento che avviene
        super.paintComponent(g);
		repaint();
        revalidate();
		
		//Background del map menu e dello stats menu
		g.setColor(Color.BLACK);
        //rettangolo che contiene entrambi i menu stats-map
        g.fillRect(0, 0, 850, 350);
        g.setColor(Color.darkGray); //devo riuscire a stampare il colore 64 64 64
        g.drawRect(0, 0, 550, 349);
		g.drawRect(550, -5, 300, 450);
        //aggiorna ogni volta il menu stats del player
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 15));
		g.drawString("Name: "+reference.player.getNome(), 560, 20);
        g.drawString("Category: "+reference.player.getCategory(), 560, 40);
        g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 15));
		g.drawString("Life: "+reference.player.getVita()+"/ 100", 560, 60);
		g.drawString("Damage: "+reference.player.getSpada().getAttacco_max()+" - "+reference.player.getSpada().getAttacco_min(), 560, 80);
		g.drawString("Defense: "+reference.player.getArmour().getDifesa(), 560, 100);
		g.drawString("Gold: "+reference.player.getMonete(), 560, 120);
		g.drawString("Keys: "+reference.player.getKey(), 560, 140);
        g.drawString("Potions: "+reference.player.getNumpozioni(), 560, 160);
        g.drawString("Inventario: "+reference.player.getPeso()+" / 100", 560, 180);
		g.drawString("Weapon Equipped: "+reference.player.getSpada().getNome(), 560, 200);
		g.drawString("Armor Equipped: "+reference.player.getArmour().getNome(), 560, 220);

		//Floor color e riempimento grafico della mappa
		g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 15));
            int x = 15;
            int y = 15;
            int spacebetween = 15 * (reference.currentStanza.getRow() + 1);
        for (int i = 0; i < reference.currentStanza.getColumn(); i++) {
            for (int j = 0; j < reference.currentStanza.getRow(); j++) {
                g.drawString(""+reference.currentStanza.cellestanza.get(i).get(j).getSymbol(),x, y);
                x += 15;
                if(x == spacebetween)
                    x=15;
            }
            
            y+=25;
        }
    }
    //gestisce key even quando premi le frecce
    @Override
    public void keyPressed(KeyEvent e) {
        if(reference.player.isAlive()){
            int x = reference.player.getX();
            int y = reference.player.getY();
            switch(e.getKeyCode()) {
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP:
                    //QUANDO premi cosa fai
                    if(checkwhatyoubumped(x, y-1)){
                        reference.currentStanza.cellestanza.get(y-1).set(x,Cell.PLAYER);
                        reference.currentStanza.cellestanza.get(y).set(x,Cell.FREE);
                        reference.player.setY(y - 1);

                    }
                    break;
                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    if(checkwhatyoubumped(x + 1, y)){
                        reference.currentStanza.cellestanza.get(y).set(x+1,Cell.PLAYER);
                        reference.currentStanza.cellestanza.get(y).set(x,Cell.FREE);
                        reference.player.setX(x + 1);
                    }
                    break;
                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                    if(checkwhatyoubumped(x - 1, y)){
                        reference.currentStanza.cellestanza.get(y).set(x-1,Cell.PLAYER);
                        reference.currentStanza.cellestanza.get(y).set(x,Cell.FREE);
                        reference.player.setX(x - 1);
                    }
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    if(checkwhatyoubumped(x, y + 1)){
                        reference.currentStanza.cellestanza.get(y + 1).set(x,Cell.PLAYER);
                        reference.currentStanza.cellestanza.get(y).set(x,Cell.FREE);
                        reference.player.setY(y + 1);
                    }
                    break;
                case KeyEvent.VK_END:
                    
                    break;
                default:
                    System.out.println("Tasto non riconosciuto");
            }
            updateMonsterPosition();
        }
    }
    @Override
    public void keyReleased(KeyEvent e){

    }
    @Override
    public void keyTyped(KeyEvent e){

    }
    //controlla nella direzione della casella che vuoi spostarti , manca gestire caso dell'item e del mostro
    public boolean checkwhatyoubumped(int x, int y){
        switch(reference.currentStanza.getSsymbol(x,y)) {
            case '#':
                reference.ui.messageTextArea.setText("Hai trovato un muro\n...\n...");
                return false;
            case '.':
                reference.ui.messageTextArea.setText("...\n...\n...");
                return true;
            case 'C':
                reference.player.setMonete((int)((Math.random() * 15)+3));
                reference.ui.messageTextArea.setText("Hai trovato delle monete\n...\n...");
                return true;  
            case 'f':
                reference.player.setKey();
                reference.ui.messageTextArea.setText("O O ... Hai trovato una chiave\n...\n...");
                return true;
            case 'F':
                reference.player.setGoldKey();
                reference.ui.messageTextArea.setText("O O ... Hai trovato una chiave d'orata\nchissa che cosa aprirà?\n...");
                return true;
            case 'H':
                reference.player.addPozioni();
                reference.ui.messageTextArea.setText("Hai trovato una pozione\n...\n...");
                return true;
            case 'N':
                if(reference.currentStanza.getDrive_to_N() != 0){
                    reference.player.setspawnTo('S');
                    reference.ui.messageTextArea.setText("Questa è la porta Nord!\nVuoi attraversarla?\n Scrivi Nord per entrare altrimenti no ");
                    reference.filereader.fileToWrite(reference.currentStanza.cellestanza,"src/main/java/Board/Stanzeold/stanza_"+reference.curr_stanza+".txt");
                    changeRoom(reference.currentStanza.getDrive_to_N());
                }
                break;
            case 'E':
                if(reference.currentStanza.getDrive_to_E() != 0){
                    reference.player.setspawnTo('W');
                    reference.ui.messageTextArea.setText("Questa è la porta Est!\nVuoi attraversarla?\n Scrivi Est per entrare altrimenti no ");
                    reference.filereader.fileToWrite(reference.currentStanza.cellestanza,"src/main/java/Board/Stanzeold/stanza_"+reference.curr_stanza+".txt");
                    changeRoom(reference.currentStanza.getDrive_to_E());
                }
                break;
            case 'S':
                if(reference.currentStanza.getDrive_to_S() != 0){
                    reference.player.setspawnTo('N');
                    reference.ui.messageTextArea.setText("Questa è la porta Sud!\nVuoi attraversarla?\n Scrivi Sud per entrare altrimenti no ");
                    reference.filereader.fileToWrite(reference.currentStanza.cellestanza,"src/main/java/Board/Stanzeold/stanza_"+reference.curr_stanza+".txt");
                    changeRoom(reference.currentStanza.getDrive_to_S());
                }
                break;
            case 'W':
                if(reference.currentStanza.getDrive_to_W() != 0){
                    reference.player.setspawnTo('E');
                    reference.ui.messageTextArea.setText("Questa è la porta Ovest!\nVuoi attraversarla?\n Scrivi Ovest per entrare altrimenti no ");
                    reference.filereader.fileToWrite(reference.currentStanza.cellestanza,"src/main/java/Board/Stanzeold/stanza_"+reference.curr_stanza+".txt");
                    changeRoom(reference.currentStanza.getDrive_to_W());
                }
                break;
            case 'I':
                for (int i = 0; i < reference.currentStanza.lista_item.size(); i++) {
                    if(x == reference.currentStanza.lista_item.get(i).getX() && y == reference.currentStanza.lista_item.get(i).getY()){
                        //ti sei imbattutto in un item
                        reference.ui.messageTextArea.setText("Hai trovato un oggetto!\nVuoi raccoglierlo?\n Scrivi take per prenderlo altrimenti no ");
                        if(reference.currentStanza.lista_item.get(i).isIsSword()){
                            reference.player.takeItem(reference.currentStanza.lista_item.get(i));
                            return true;
                        }
                        else{
                            reference.player.takeItem(reference.currentStanza.lista_item.get(i));
                            return true;
                        }
                    }
                }    
                return false;
            case 'P':
                if(reference.player.getKey() >=1){
                    reference.ui.messageTextArea.setText("Apri la porta...\n...\n Aperta!");
                    reference.player.removeKey();
                    return true;
                }else{
                    reference.ui.messageTextArea.setText("Questa è una porta!\nTi serve una chiave per aprirla\n ...");
                    return false;
                }
            case 'G':
                if(reference.player.getGoldkey() >=1){
                    reference.player.removeGoldKey();
                    reference.player.setspawnTo('G');
                    reference.ui.messageTextArea.setText("Apri il portone...\n...\n Aperto! Benvenuto nella stanza del Boss!");
                    reference.filereader.fileToWrite(reference.currentStanza.cellestanza,"src/main/java/Board/Stanzeold/stanza_"+reference.curr_stanza+".txt");
                    changeRoom(reference.currentStanza.getDrive_to_boss());                    
                }else{
                    reference.ui.messageTextArea.setText("Che strano portone luminescente...\nNecessiti di qualche tipo di chiave speciale forse...\n ");
                    
                }
                break;
            default:
            reference.ui.messageTextArea.setText("Oooopsss sembra che qualcosa sia andato storto nel gioco\n...\n...");
        }
        return false;
    }
    //metodo che gestisce il cambio di stanza se ci sei gia stato preleva dati dal file StanzeOld
    public void changeRoom(int drive_to){
        resetParameter();
        reference.lista_stanze.add(reference.currentStanza);
        for (int i = 0; i < reference.lista_stanze.size(); i++) {
            if(reference.lista_stanze.get(i).getid() == drive_to){
                reference.currentStanza = new Board(drive_to,reference.alreadybeen);
                
            }   
        }
        //se stanza è nuova prendila dal file
        if(!reference.alreadybeen)
            reference.currentStanza = new Board(drive_to);
        reference.alreadybeen = false;
        //serve per mettere il player davanti alla porta della nuova stanza e resetta movimenti
        for (int i = 0; i < reference.currentStanza.getColumn(); i++) {
            for (int j = 0; j < reference.currentStanza.getRow(); j++) {
                if(reference.currentStanza.getSsymbol(j , i) == reference.player.spawnTo()) {
                    switch (reference.player.spawnTo()) {
                        case 'N':
                            reference.currentStanza.cellestanza.get(i+1).set(j,Cell.PLAYER);
                            reference.player.setY(i+1);
                            reference.player.setX(j);
                            reference.player.setspawnTo('/');
                            break;     
                        case 'E': 
                            reference.currentStanza.cellestanza.get(i).set(j-1,Cell.PLAYER);
                            reference.player.setX(j-1);
                            reference.player.setY(i);
                            
                            reference.player.setspawnTo('/');
                            break;
                        case 'G':
                            reference.currentStanza.cellestanza.get(i).set(j-1,Cell.PLAYER);
                            reference.player.setX(j-1);
                            reference.player.setY(i);
                            reference.player.setspawnTo('/');
                            reference.currentStanza.cellestanza.get(i).set(j,Cell.WALL);
                            break;
                        case 'W':
                            reference.currentStanza.cellestanza.get(i).set(j+1,Cell.PLAYER);
                            reference.player.setX(j+1);
                            reference.player.setY(i);
                            
                            reference.player.setspawnTo('/');
                            break;
                        case 'S':
                            reference.currentStanza.cellestanza.get(i-1).set(j,Cell.PLAYER);
                            reference.player.setY(i-1);
                            reference.player.setX(j);   
                            reference.player.setspawnTo('/');
                            break;
                        default:
                            System.out.println("Porta spawn non trovato");
                    }
                }
            }
        }
    }
    //resetta tutte le variabili
    public void resetParameter(){

    }
    //gestisce movimento mostri e boss
    public void updateMonsterPosition(){

    }
}
