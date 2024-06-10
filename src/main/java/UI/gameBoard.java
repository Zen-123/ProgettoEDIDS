package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Board.Board;
import Board.Cell;
import Board.reference;
import Player.Item;
import Player.mostro;

// risistemare il codice
//mettere apposto i metodi per togliere righe
//controllare i vari import
//togliere tutte le variabili e classi inutilizzate
//togliere il get symbol utilizzarne uno univoco
//reset parametri popolare
//spostare tutti metodi di gestione nella classe func
//sistemare parametri di gioco dannno difesa e tutto
//utilizzare un get set univoco
//manca da sistemare parte grafica con controlli di input
//file di testo riempirli e cancellarli una volta utilizzati se non li salvi 
//controllo errore e input del file ecc

//ce un errore nella lista dei mostri quando combattono e vengono uccisi

public class gameBoard extends JPanel implements KeyListener {
    public gameBoard(){
        addKeyListener(this);
        this.setFocusable(true);
        makesomething();
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
                        updateMonsterPosition();
                    }
                    break;
                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    if(checkwhatyoubumped(x + 1, y)){
                        reference.currentStanza.cellestanza.get(y).set(x+1,Cell.PLAYER);
                        reference.currentStanza.cellestanza.get(y).set(x,Cell.FREE);
                        reference.player.setX(x + 1);
                        updateMonsterPosition();
                    }
                    break;
                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                    if(checkwhatyoubumped(x - 1, y)){
                        reference.currentStanza.cellestanza.get(y).set(x-1,Cell.PLAYER);
                        reference.currentStanza.cellestanza.get(y).set(x,Cell.FREE);
                        reference.player.setX(x - 1);
                        updateMonsterPosition();
                    }
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    if(checkwhatyoubumped(x, y + 1)){
                        reference.currentStanza.cellestanza.get(y + 1).set(x,Cell.PLAYER);
                        reference.currentStanza.cellestanza.get(y).set(x,Cell.FREE);
                        reference.player.setY(y + 1);
                        updateMonsterPosition();
                    }
                    break;
                default:
                    System.out.println("Tasto non riconosciuto");
            }
            
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
                reference.ui.messageTextArea.setText("Hai raccolto delle monete\n...\n...");
                return true;  
            case 'f':
                if(reference.player.getPeso() <= 95){
                    reference.player.setKey();
                    reference.ui.messageTextArea.setText("O O ... Hai trovato una chiave\n...\n...");
                    return true;
                }else
                    return false;
                
            case 'F':
                if(reference.player.getPeso() <= 95){
                    reference.player.setGoldKey();
                    reference.ui.messageTextArea.setText("O O ... Hai trovato una chiave d'orata\nchissa che cosa aprirà?\n...");
                    return true;
                }else
                    return false;
            case 'H':
                if(reference.player.getPeso() <= 95){
                    reference.player.addPozioni();
                    reference.ui.messageTextArea.setText("Hai trovato una pozione\nHai raccolto la pozione\n...");
                    return true;
                }else
                    return false;
            case 'N':
                if(reference.currentStanza.getDrive_to_N() != 0){ 
                    reference.ui.messageTextArea.setText("Questa è la porta Nord!\nVuoi attraversarla?\nScrivi Nord per entrare altrimenti no ");
                    reference.ui.commandTextField.requestFocus();
                }
                break;
            case 'E':
                if(reference.currentStanza.getDrive_to_E() != 0){
                    reference.ui.messageTextArea.setText("Questa è la porta Est!\nVuoi attraversarla?\nScrivi Est per entrare altrimenti no ");
                    reference.ui.commandTextField.requestFocus();
                }
                break;
            case 'S':
                if(reference.currentStanza.getDrive_to_S() != 0){
                    reference.ui.messageTextArea.setText("Questa è la porta Sud!\nVuoi attraversarla?\nScrivi Sud per entrare altrimenti no ");
                    reference.ui.commandTextField.requestFocus();
                }
                break;
            case 'W':
                if(reference.currentStanza.getDrive_to_W() != 0){
                    reference.ui.messageTextArea.setText("Questa è la porta Ovest!\nVuoi attraversarla?\nScrivi Ovest per entrare altrimenti no ");
                    reference.ui.commandTextField.requestFocus();
                }
                break;
            case 'I':
                for (int i = 0; i < reference.currentStanza.lista_item.size(); i++) {
                    if(x == reference.currentStanza.lista_item.get(i).getX() && y == reference.currentStanza.lista_item.get(i).getY() && reference.curr_stanza == reference.currentStanza.lista_item.get(i).getId_stanza()){
                        //ti sei imbattutto in un item
                        if(reference.currentStanza.lista_item.get(i).isIsSword() ){
                            if(reference.player.isHasSword())
                                reference.ui.messageTextArea.setText("Hai trovato una spada! "+reference.currentStanza.lista_item.get(i).getAttacco_max()+" - "+reference.currentStanza.lista_item.get(i).getAttacco_min()+" \nNe possiedi già una. Vuoi scambiarla?\n Scrivi take per prenderla altrimenti no per lasciarla a terra");
                            else{
                                reference.ui.messageTextArea.setText("Hai trovato una spada!Ti servirà per affrontare i mille pericoli "+reference.currentStanza.lista_item.get(i).getAttacco_max()+" - "+reference.currentStanza.lista_item.get(i).getAttacco_min()+"\nVuoi raccoglierla?\n Scrivi take per prenderla altrimenti no per lasciarla a terra ");
                            }
                            reference.item = reference.currentStanza.lista_item.get(i);
                            reference.ui.commandTextField.requestFocus();
                            return false;
                        }else{
                            if(reference.player.isHasArmour())
                                reference.ui.messageTextArea.setText("Hai trovato una armatura! "+reference.currentStanza.lista_item.get(i).getDifesa()+"\nNe possiedi già una. Vuoi scambiarla?\n Scrivi take per prenderla altrimenti no per lasciarla a terra");
                            else{
                                reference.ui.messageTextArea.setText("Hai trovato una armatura!Ti servirà per affrontare i mille pericoli "+reference.currentStanza.lista_item.get(i).getDifesa()+"\nVuoi raccoglierla?\n Scrivi take per prenderla altrimenti no per lasciarla a terra ");
                            }
                            reference.item = reference.currentStanza.lista_item.get(i);
                            reference.ui.commandTextField.requestFocus();
                            return false;
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
            case 'B':
            case 'M':
                for (int i = 0; i < reference.currentStanza.lista_mostri.size(); i++) {
                    if(reference.currentStanza.lista_mostri.get(i).getX() == x && reference.currentStanza.lista_mostri.get(i).getY() == y){
                        reference.mostro = reference.currentStanza.lista_mostri.get(i);
                        reference.ui.messageTextArea.setText("Hai incontrato un "+reference.mostro.getNome()+"!\nVuoi attaccarlo o scappare?\n"+reference.mostro.getNome()+" danno "+reference.mostro.getDanno_max()+" - "+reference.mostro.getDanno_min()+" difesa "+reference.mostro.getDifesa()+" vita "+reference.mostro.getVita()+"");
                        reference.player.setCanAttack(true);
                    }     
                }
                reference.ui.commandTextField.requestFocus();
                return false;
            case 'G':
                if(reference.player.getGoldkey() == 1){
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

        // stanza vecchia prendi dal file 
        for (int i = 0; i < reference.lista_stanze.size(); i++) {
            if(reference.lista_stanze.get(i).getid() == drive_to){
                reference.currentStanza = new Board(drive_to,true);
                reference.currentStanza.lista_item = reference.lista_stanze.get(i).lista_item;
                reference.currentStanza.lista_mostri = reference.lista_stanze.get(i).lista_mostri;
                reference.alreadybeen = true;
            }   
        }
        //se stanza è nuova prendila dal file
        if(reference.alreadybeen == false){
            if(reference.startGame == false){
                reference.lista_stanze.add(reference.currentStanza);
                reference.startGame = true;
            }
            reference.currentStanza = new Board(drive_to);
            reference.lista_stanze.add(reference.currentStanza);
        }
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
                            reference.player.setY(i);
                            reference.player.setX(j-1); 
                            reference.player.setspawnTo('/');
                            break;
                        case 'G':
                            reference.currentStanza.cellestanza.get(i).set(j-1,Cell.PLAYER);
                            reference.player.setY(i);
                            reference.player.setX(j-1);
                            reference.player.setspawnTo('/');
                            reference.currentStanza.cellestanza.get(i).set(j,Cell.WALL);
                            break;
                        case 'W':
                            reference.currentStanza.cellestanza.get(i).set(j+1,Cell.PLAYER);
                            reference.player.setY(i);
                            reference.player.setX(j+1);
                            reference.player.setspawnTo('/');
                            break;
                        case 'S':
                            reference.currentStanza.cellestanza.get(i-1).set(j,Cell.PLAYER);
                            reference.player.setY(i-1);
                            reference.player.setX(j);   
                            reference.player.setspawnTo('/');
                            break;
                        default:
                            reference.ui.messageTextArea.setText("Oooopsss sembra che qualcosa sia andato storto nel trovare la porta di accesso\n...\n...");
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
        if(reference.currentStanza.lista_mostri.size() > 0){
            for (int i = 0; i < reference.currentStanza.lista_mostri.size(); i++) {
                if(reference.currentStanza.lista_mostri.get(i).getVita() <= 0)
                    reference.currentStanza.lista_mostri.remove(i);
                else{
                    int choose = (int)(Math.random() * 4 + 1);
                    int x = reference.currentStanza.lista_mostri.get(i).getX();
                    int y = reference.currentStanza.lista_mostri.get(i).getY();
                    reference.mostro = reference.currentStanza.lista_mostri.get(i);
                    switch(choose) {
                        case 1:
                        if(reference.currentStanza.cellestanza.get(y).get(x+1) == Cell.WALL){

                        }else{
                            if(reference.currentStanza.cellestanza.get(y).get(x+1) == Cell.FREE){
                                if(reference.currentStanza.cellestanza.get(y).get(x).getSymbol() == 'M')
                                    reference.currentStanza.cellestanza.get(y).set(x+1,Cell.MONSTER);
                                else if(reference.currentStanza.cellestanza.get(y).get(x).getSymbol() == 'B')
                                    reference.currentStanza.cellestanza.get(y).set(x+1,Cell.BOSS);

                                reference.currentStanza.cellestanza.get(y).set(x,Cell.FREE);
                                reference.currentStanza.lista_mostri.get(i).setX(x + 1);
                                break;
                            }
                            else if(reference.currentStanza.cellestanza.get(y).get(x+1) == Cell.PLAYER){
                                reference.ui.messageTextArea.setText("Hai incontrato un "+reference.mostro.getNome()+"!\n...\n...");
                                monsterEncounter(0,0,true);
                                reference.ui.commandTextField.requestFocus();
                                break;
                            } 
                        }      
                        case 2:
                        if(reference.currentStanza.cellestanza.get(y).get(x-1) == Cell.WALL){

                        }
                        else{
                            if(reference.currentStanza.cellestanza.get(y).get(x-1) == Cell.FREE){
                                if(reference.currentStanza.cellestanza.get(y).get(x).getSymbol() == 'M')
                                    reference.currentStanza.cellestanza.get(y).set(x-1,Cell.MONSTER);
                                else if(reference.currentStanza.cellestanza.get(y).get(x).getSymbol() == 'B')
                                    reference.currentStanza.cellestanza.get(y).set(x-1,Cell.BOSS);
                                reference.currentStanza.cellestanza.get(y).set(x,Cell.FREE);
                                reference.currentStanza.lista_mostri.get(i).setX(x-1);
                                break;
                            }
                            else if(reference.currentStanza.cellestanza.get(y).get(x-1) == Cell.PLAYER){
                                reference.ui.messageTextArea.setText("Hai incontrato un "+reference.mostro.getNome()+"!\n...\n...");
                                monsterEncounter(0,0,true);
                                reference.ui.commandTextField.requestFocus();
                                break;
                            }
                        }    
                        case 3:
                        if(reference.currentStanza.cellestanza.get(y+1).get(x) == Cell.WALL){

                        }else{
                            if(reference.currentStanza.cellestanza.get(y+1).get(x) == Cell.FREE){
                                if(reference.currentStanza.cellestanza.get(y).get(x).getSymbol() == 'M')
                                    reference.currentStanza.cellestanza.get(y+1).set(x,Cell.MONSTER);
                                else if(reference.currentStanza.cellestanza.get(y).get(x).getSymbol() == 'B')
                                    reference.currentStanza.cellestanza.get(y+1).set(x,Cell.BOSS);
                                reference.currentStanza.cellestanza.get(y).set(x,Cell.FREE);
                                reference.currentStanza.lista_mostri.get(i).setY(y + 1);
                                break;
                            }
                            else if(reference.currentStanza.cellestanza.get(y+1).get(x) == Cell.PLAYER){
                                reference.ui.messageTextArea.setText("Hai incontrato un "+reference.mostro.getNome()+"!\n...\n...");
                                monsterEncounter(0,0,true);
                                reference.ui.commandTextField.requestFocus();
                                break;
                            }  
                        }
                        case 4:
                        if(reference.currentStanza.cellestanza.get(y-1).get(x) == Cell.WALL){

                        }else{
                            if(reference.currentStanza.cellestanza.get(y-1).get(x) == Cell.FREE){
                                if(reference.currentStanza.cellestanza.get(y).get(x).getSymbol() == 'M')
                                    reference.currentStanza.cellestanza.get(y-1).set(x,Cell.MONSTER);
                                else if(reference.currentStanza.cellestanza.get(y).get(x).getSymbol() == 'B')
                                    reference.currentStanza.cellestanza.get(y-1).set(x,Cell.BOSS);
                                reference.currentStanza.cellestanza.get(y).set(x,Cell.FREE);
                                reference.currentStanza.lista_mostri.get(i).setY(y - 1);
                                break;
                            }
                            else if(reference.currentStanza.cellestanza.get(y-1).get(x) == Cell.PLAYER){
                                reference.ui.messageTextArea.setText("Hai incontrato un "+reference.mostro.getNome()+"!\n...\n...");
                                monsterEncounter(0,0,true);
                                reference.ui.commandTextField.requestFocus();
                                break;
                            }
                        }
                            
                    }
                }
                
            }
        }
    }
   public void makesomething(){
    reference.ui.commandTextField.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                switch(reference.ui.commandTextField.getText().toLowerCase()) {
                    case "take":
                    if(reference.item != null){
                        if(reference.item.getNome() == "spada" && reference.player.isHasSword() == true){
                            //allora dovra buttare la sua spada a terra
                            Item spadadaposare = new Item();
                            reference.currentStanza.cellestanza.get(reference.item.getY()).set(reference.item.getX(),Cell.PLAYER);   
                            reference.currentStanza.cellestanza.get(reference.player.getY()).set(reference.player.getX(),Cell.ITEM);
                            reference.player.getSpada().setX(reference.player.getX());
                            reference.player.getSpada().setY(reference.player.getY());
                            spadadaposare = reference.player.getSpada();
                            spadadaposare.setSymbol('I');
                            spadadaposare.setHasTake(false);
                            spadadaposare.setId_stanza(reference.curr_stanza);
                            reference.player.setY(reference.item.getY());
                            reference.player.setX(reference.item.getX());
                            reference.item.setHasTake(true);
                            reference.player.takeItem(reference.item);
                            reference.ui.commandTextField.setText("");  
                            reference.item = new Item();   
                            reference.ui.messageTextArea.setText("...\n...\n...");
                            for (int i = 0; i < reference.currentStanza.lista_item.size(); i++) {
                                if(reference.currentStanza.lista_item.get(i).isHasTake())
                                    reference.currentStanza.lista_item.remove(i);
                            }
                            reference.currentStanza.lista_item.add(spadadaposare);
                            // reference.currentStanza.setSsymbol(spadadaposare.getY(),spadadaposare.getX(),'I');

                        }else if(reference.item.getNome() == "spada" && reference.player.isHasSword() == false){
                            reference.player.takeItem(reference.item);   
                            reference.currentStanza.cellestanza.get(reference.item.getY()).set(reference.item.getX(),Cell.PLAYER);   
                            reference.currentStanza.cellestanza.get(reference.player.getY()).set(reference.player.getX(),Cell.FREE);
                            reference.player.setY(reference.item.getY());
                            reference.player.setX(reference.item.getX());   
                            reference.ui.commandTextField.setText(""); 
                            /* for (int i = 0; i < reference.currentStanza.lista_item.size(); i++) {
                                if(reference.currentStanza.lista_item.get(i).isHasTake())
                                    reference.currentStanza.lista_item.remove(i);
                            }  */
                            reference.item = new Item();   
                            reference.ui.messageTextArea.setText("...\n...\n...");
                        }
                        if(reference.item.getNome() == "armatura" && reference.player.isHasArmour() == true){
                            //allora dovra buttare la sua spada a terra
                            Item armaturadaposare = new Item();
                            reference.currentStanza.cellestanza.get(reference.item.getY()).set(reference.item.getX(),Cell.PLAYER);   
                            reference.currentStanza.cellestanza.get(reference.player.getY()).set(reference.player.getX(),Cell.ITEM);
                            reference.player.getArmour().setX(reference.player.getX());
                            reference.player.getArmour().setY(reference.player.getY());
                            armaturadaposare = reference.player.getArmour();
                            armaturadaposare.setSymbol('I');
                            armaturadaposare.setHasTake(false);
                            armaturadaposare.setId_stanza(reference.curr_stanza);
                            reference.player.setY(reference.item.getY());
                            reference.player.setX(reference.item.getX());
                            reference.item.setHasTake(true);
                            reference.player.takeItem(reference.item);
                            reference.ui.commandTextField.setText("");  
                            reference.item = new Item();   
                            reference.ui.messageTextArea.setText("...\n...\n...");
                            for (int i = 0; i < reference.currentStanza.lista_item.size(); i++) {
                                if(reference.currentStanza.lista_item.get(i).isHasTake())
                                    reference.currentStanza.lista_item.remove(i);
                            }
                            reference.currentStanza.lista_item.add(armaturadaposare);
                            // reference.currentStanza.setSsymbol(armaturadaposare.getY(),armaturadaposare.getX(),'I');
                        }else if(reference.item.getNome() == "armatura" && reference.player.isHasArmour() == false){
                            reference.player.takeItem(reference.item);   
                            reference.currentStanza.cellestanza.get(reference.item.getY()).set(reference.item.getX(),Cell.PLAYER);   
                            reference.currentStanza.cellestanza.get(reference.player.getY()).set(reference.player.getX(),Cell.FREE);
                            reference.player.setY(reference.item.getY());
                            reference.player.setX(reference.item.getX());   
                            reference.ui.commandTextField.setText("");  
                            reference.item = new Item();   
                            reference.ui.messageTextArea.setText("...\n...\n...");
                        }
                        updateMonsterPosition();
                    }else{
                        reference.ui.commandTextField.setText(""); 
                    }
                    reference.ui.gameB.requestFocus();
                    break;
                    case "no": 
                        reference.ui.commandTextField.setText("");
                        reference.item = new Item();
                        reference.ui.messageTextArea.setText("...\n...\n...");
                        reference.ui.gameB.requestFocus();
                    break;
                    case "apri":
                        updateMonsterPosition();
                        reference.ui.gameB.requestFocus();
                    break;
                    case "non aprire":
                        reference.ui.gameB.requestFocus();
                    break;
                    case "pozione":
                        if(reference.player.getNumpozioni() > 0){
                            reference.player.usePozioni();
                            reference.ui.messageTextArea.setText("Ti stai curando\nEra molto dissetante!!\nTi senti molto meglio ora");
                            reference.ui.commandTextField.setText("");
                            updateMonsterPosition();
                        }else{
                            reference.ui.messageTextArea.setText("Hai finito le pozioni\n...\n...");
                            reference.ui.commandTextField.setText("");
                        }
                    break;
                    case "nord":
                        reference.player.setspawnTo('S');
                        reference.filereader.fileToWrite(reference.currentStanza.cellestanza,"src/main/java/Board/Stanzeold/stanza_"+reference.curr_stanza+".txt");
                        changeRoom(reference.currentStanza.getDrive_to_N());
                        reference.ui.gameB.requestFocus();
                        reference.ui.commandTextField.setText("");
                        break;
                    case "sud":
                        reference.player.setspawnTo('N');
                        reference.filereader.fileToWrite(reference.currentStanza.cellestanza,"src/main/java/Board/Stanzeold/stanza_"+reference.curr_stanza+".txt");
                        changeRoom(reference.currentStanza.getDrive_to_S());
                        reference.ui.gameB.requestFocus();
                        reference.ui.commandTextField.setText("");
                        break;
                    case "est":
                        reference.player.setspawnTo('W');
                        reference.filereader.fileToWrite(reference.currentStanza.cellestanza,"src/main/java/Board/Stanzeold/stanza_"+reference.curr_stanza+".txt");                        
                        changeRoom(reference.currentStanza.getDrive_to_E());
                        reference.ui.gameB.requestFocus();
                        reference.ui.commandTextField.setText("");
                        break;
                    case "ovest":
                        reference.player.setspawnTo('E');
                        reference.filereader.fileToWrite(reference.currentStanza.cellestanza,"src/main/java/Board/Stanzeold/stanza_"+reference.curr_stanza+".txt");  
                        changeRoom(reference.currentStanza.getDrive_to_W());
                        reference.ui.gameB.requestFocus();
                        reference.ui.commandTextField.setText("");
                        break;
                    case "attack":
                        if(reference.currentStanza.lista_mostri.size() > 0 && reference.player.canAttack()){
                            int dannoplayer = reference.player.getSpada().getDanno();
                            int difesomonster = reference.mostro.getDifesa();
                            reference.mostro.takeDamage(difesomonster - dannoplayer);
                            if(reference.mostro.getVita() <=0){
                                if(reference.mostro.getSymbol() == 'B'){
                                    reference.ui.messageTextArea.setText("Hai ucciso il BOSS! Hai Vinto!!");
                                    reference.player.setMostri_uccisi();
                                    reference.player.setVita(0);
                                    reference.mostro = new mostro();
                                    reference.player.setCanAttack(false);
                                }else{
                                    reference.player.setMostri_uccisi();
                                    reference.currentStanza.cellestanza.get(reference.mostro.getY()).set(reference.mostro.getX(), Cell.FREE);
                                    reference.ui.gameB.requestFocus();
                                    reference.ui.messageTextArea.setText("Hai sconfitto il "+reference.mostro.getNome());
                                    reference.mostro = new mostro();
                                    reference.player.setCanAttack(false);
                                } 
                            }else{
                                monsterEncounter(dannoplayer,difesomonster,false);
                            }
                            reference.ui.commandTextField.setText("");
                        }else{
                            reference.ui.commandTextField.setText("");
                        }  
                        break;
                    case "run":
                        if(reference.player.canAttack()){
                            reference.ui.gameB.requestFocus();
                            reference.ui.messageTextArea.setText("Hai deciso di scappare via\nDov'è fuggi?Cit.Tarducci\nIl mostro ti starà ancora seguendo?");
                            reference.ui.commandTextField.setText("");
                        }   
                    break;
                    case "look":
                        String temp = "Ti guardi intorno...vedi molte cose sbrillucicare nel buio\n";
                        if(reference.currentStanza.lista_item.size() > 0){
                            //migliorare grafica di stampa
                            for (int i = 0; i < reference.currentStanza.lista_item.size(); i++) {
                                temp = temp+", "+ reference.currentStanza.lista_item.get(i).getNome();
                            }
                            reference.ui.messageTextArea.setText(temp);
                        }
                        reference.ui.gameB.requestFocus();
                        reference.ui.commandTextField.setText("");
                    break;
                    default:
                        reference.ui.commandTextField.setText("");
                }
            }else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                reference.ui.commandTextField.setText("");
                reference.ui.gameB.requestFocus();
                reference.ui.messageTextArea.setText("...\n...\n...");
            }
        }
    });
   }
   public void monsterEncounter(int dannoplayer,int difesomonster, boolean attack_turn){
    if(reference.mostro.getVita() > 0){
        reference.ui.commandTextField.requestFocus();
        int danno = reference.mostro.getDanno();
        int difeso = reference.player.getArmour().getDifesa();
        //boolean serve se attack_turn è true sta attaccando il mostro perchè player si e mosso
        //se false allora entrambi fermi e tocca al giocatore quindi mostri danni fatti da giocatore
        reference.player.setCanAttack(true);
        if(attack_turn)
            reference.ui.messageTextArea.setText(reference.mostro.getNome()+" ti ha inflitto "+danno+" danni, hai bloccato "+difeso+" danni\n");
        else
            reference.ui.messageTextArea.setText(reference.mostro.getNome()+" ti ha inflitto "+danno+" danni, hai bloccato "+difeso+" danni\nHai inflitto "+dannoplayer+" danni il "+reference.mostro.getNome()+" ha bloccato "+difesomonster+" danni\n"+reference.mostro.getNome()+" vita: "+reference.mostro.getVita());
        
        reference.player.takeDamage(difeso - danno);
        if(reference.player.getVita() <= 0)
            reference.ui.messageTextArea.setText("Sei morto! Hai perso!");
    }  
   }
   
}
