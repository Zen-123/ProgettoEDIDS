package Board;

import Player.Item;
import Player.mostro;
import UI.visibilityManager;

/**
 * Classe che gestisce le funzionalità globali del gioco.
 */
public class func{

    private visibilityManager vManager = new visibilityManager(reference.ui);
    /**
     * Costruttore della classe func.
     */
    public func(){
    }
    /**
     * Genera un item casuale e lo aggiunge alla lista della stanza.
     * @param x Coordinata x dell'item.
     * @param y Coordinata y dell'item.
     * @return L'item generato.
     */
    public static Item generateItem(int x, int y){
        int choose = (int)(Math.random() * 99);
        Item a;
        if(choose % 2 == 0){//pari è spada
            a = new Item("spada", (int)(Math.random() * 20) + 6, (int)(Math.random() * 4) + 2, 0,true,reference.curr_stanza,false,20);
            a.setX(x);
            a.setY(y);
            a.setSymbol('I');
        }else{//dispari è un armatura
            a = new Item("armatura", 0, 0, (int)((Math.random() * 20) + 5),false,reference.curr_stanza,false,30);
            a.setX(x);
            a.setY(y);
            a.setSymbol('I');
        }
        return a;
    }
    /**
     * Aggiorna la posizione di tutti i mostri nella stanza corrente in modo randomico.
     */
    public void updateMonsterPosition(){
        if(reference.currentStanza.lista_mostri.size() > 0){
            for (int i = 0; i < reference.currentStanza.lista_mostri.size(); i++) {
                if(reference.currentStanza.lista_mostri.get(i).getVita() <= 0)
                    reference.currentStanza.lista_mostri.remove(i);
                else{
                    int choose = (int)(Math.random() * 4 + 1);
                    int x = reference.currentStanza.lista_mostri.get(i).getX();
                    int y = reference.currentStanza.lista_mostri.get(i).getY();
                    reference.mostro = new mostro();
                    reference.mostro = reference.currentStanza.lista_mostri.get(i);
                    switch(choose){
                        case 1:
                        if(reference.currentStanza.cellestanza.get(y).get(x+1) == Cell.WALL){

                        }else{
                            if(reference.currentStanza.cellestanza.get(y).get(x+1) == Cell.FREE){
                                if(reference.currentStanza.cellestanza.get(y).get(x).getSymbol() == 'M')
                                    reference.currentStanza.cellestanza.get(y).set(x+1,Cell.MONSTER);
                                else if(reference.currentStanza.cellestanza.get(y).get(x).getSymbol() == 'B')
                                    reference.currentStanza.cellestanza.get(y).set(x+1,Cell.BOSS);

                                reference.currentStanza.cellestanza.get(y).set(x,Cell.FREE);
                                reference.currentStanza.lista_mostri.get(i).setCoordinate(x+1, y);
                                break;
                            }
                            else if(reference.currentStanza.cellestanza.get(y).get(x+1) == Cell.PLAYER){
                                reference.ui.messageTextArea.setText("Hai incontrato un "+reference.mostro.getNome()+"!\n...\n...");
                                monsterEncounter(0,reference.mostro,0,true);
                                reference.mostrorun = reference.mostro;
                                reference.player.setCanAttack(false);
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
                                reference.currentStanza.lista_mostri.get(i).setCoordinate(x-1, y);
                                break;
                            }
                            else if(reference.currentStanza.cellestanza.get(y).get(x-1) == Cell.PLAYER){
                                reference.ui.messageTextArea.setText("Hai incontrato un "+reference.mostro.getNome()+"!\n...\n...");
                                monsterEncounter(0,reference.mostro,0,true);
                                reference.mostrorun = reference.mostro;
                                reference.player.setCanAttack(false);
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
                                //useremo il setcoordinate
                                reference.currentStanza.lista_mostri.get(i).setCoordinate(x, y + 1);
                                // reference.currentStanza.lista_mostri.get(i).setY();
                                break;
                            }
                            else if(reference.currentStanza.cellestanza.get(y+1).get(x) == Cell.PLAYER){
                                reference.ui.messageTextArea.setText("Hai incontrato un "+reference.mostro.getNome()+"!\n...\n...");
                                monsterEncounter(0,reference.mostro,0,true);
                                reference.mostrorun = reference.mostro;
                                reference.player.setCanAttack(false);
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
                                reference.currentStanza.lista_mostri.get(i).setCoordinate(x, y - 1);
                                break;
                            }
                            else if(reference.currentStanza.cellestanza.get(y-1).get(x) == Cell.PLAYER){
                                reference.ui.messageTextArea.setText("Hai incontrato un "+reference.mostro.getNome()+"!\n...\n...");
                                monsterEncounter(0,reference.mostro,0,true);
                                reference.mostrorun = reference.mostro;
                                reference.player.setCanAttack(false);
                                reference.ui.commandTextField.requestFocus();
                                break;
                            }
                        }
                        default: i--;
                        //gli fa ripetere la scelta finchè non compie un movement
                        //consuma molte risorse essendo random non mi piace
                    }
                }
            }
        }
    }
    /**
     * Gestisce l'azione di raccolta di un item da parte del giocatore.
     */
    public void takeItem(){
        if((reference.item.getNome().compareTo("spada") == 0) && reference.player.isHasSword()){
            //allora dovra buttare la sua spada a terra
            Item spadadaposare;
            reference.currentStanza.cellestanza.get(reference.item.getY()).set(reference.item.getX(),Cell.PLAYER);
            reference.currentStanza.cellestanza.get(reference.player.getY()).set(reference.player.getX(),Cell.ITEM);
            reference.player.getSpada().setX(reference.player.getX());
            reference.player.getSpada().setY(reference.player.getY());
            spadadaposare = reference.player.getSpada();
            spadadaposare.setSymbol('I');
            spadadaposare.setHasTake(false);
            spadadaposare.setId_stanza(reference.curr_stanza);
            reference.player.setCoordinate(reference.item.getX(), reference.item.getY());
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
        }
        if((reference.item.getNome().compareTo("armatura") == 0) && reference.player.isHasArmour() == true){
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
            reference.player.setCoordinate(reference.item.getX(), reference.item.getY());
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
        }
        reference.ui.gameB.requestFocus();
        updateMonsterPosition();
    }
    /**
     * Gestisce il cambio di stanza e l'aggiornamento dei file.
     * @param drive_to ID della stanza di destinazione.
     */
    public void changeRoomAndWriteToFile(int drive_to){
        reference.filereader.fileToWrite(reference.currentStanza.ss,reference.currentStanza.cellestanza,"src/main/java/Board/Stanzeold/stanza_"+reference.curr_stanza+".txt",false);

         // stanza vecchia prendi dal file
         for (int i = 0; i < reference.lista_stanze.size(); i++) {
            if(reference.lista_stanze.get(i).getid() == drive_to){
                reference.currentStanza = new Board(drive_to,true);
                reference.currentStanza.lista_item = reference.lista_stanze.get(i).lista_item;
                reference.currentStanza.lista_mostri = reference.lista_stanze.get(i).lista_mostri;
                reference.alreadybeen = true;
                reference.ui.messageTextArea.setText("Sei già stato qui...\n...\nMa qualcosa ti sembra cambiato....");
                reference.ui.commandTextField.setText("");
            }
        }
        //se stanza è nuova prendila dal file
        if(reference.alreadybeen == false){
            if(reference.startGame == false){
                reference.lista_stanze.add(reference.currentStanza);
                reference.startGame = true;
                reference.ui.messageTextArea.setText("Una nuova stanza...\n...\nHai la sensazione di non trovarti da solo....");
                reference.ui.commandTextField.setText("");
            }
            reference.currentStanza = new Board(drive_to);
            reference.lista_stanze.add(reference.currentStanza);
            reference.ui.messageTextArea.setText("Una nuova stanza...\n...\nHai la sensazione di non trovarti da solo....");
            reference.ui.commandTextField.setText("");
        }
        reference.alreadybeen = false;
        //serve per mettere il player davanti alla porta della nuova stanza e resetta movimenti
        for (int i = 0; i < reference.currentStanza.getColumn(); i++) {
            for (int j = 0; j < reference.currentStanza.getRow(); j++) {
                if(reference.currentStanza.getSsymbol(j , i) == reference.player.spawnTo()) {
                    switch (reference.player.spawnTo()) {
                        case 'N':
                            reference.currentStanza.cellestanza.get(i+1).set(j,Cell.PLAYER);
                            reference.player.setCoordinate(j, i+1);
                            break;
                        case 'E':
                            reference.currentStanza.cellestanza.get(i).set(j-1,Cell.PLAYER);
                            reference.player.setCoordinate(j-1, i);
                            break;
                        case 'G':
                            reference.currentStanza.cellestanza.get(i).set(j-1,Cell.PLAYER);
                            reference.player.setCoordinate(j-1, i);
                            reference.currentStanza.cellestanza.get(i).set(j,Cell.WALL);
                            break;
                        case 'W':
                            reference.currentStanza.cellestanza.get(i).set(j+1,Cell.PLAYER);
                            reference.player.setCoordinate(j+1, i);
                            break;
                        case 'S':
                            reference.currentStanza.cellestanza.get(i-1).set(j,Cell.PLAYER);
                            reference.player.setCoordinate(j, i-1);
                            break;
                        default:
                            reference.ui.messageTextArea.setText("Oooopsss sembra che qualcosa sia andato storto nel trovare la porta di accesso\n...\n...");
                    }
                    reference.player.setspawnTo('/');
                }
            }
        }
        reference.ui.gameB.requestFocus();

    }
    /**
     * Genera un mostro casuale.
     * @param x Coordinata x del mostro.
     * @param y Coordinata y del mostro.
     * @return Il mostro generato.
     */
    public static mostro generateMonster(int x, int y){
        mostro m;
        int choose = (int)(Math.random() * 5);
        switch(choose) {
            case 0:
                m = new mostro("vampiro",15,5,7,30,reference.curr_stanza);
                break;
            case 1:
                m = new mostro("scheletro",5,1,1,5,reference.curr_stanza);
                break;
            case 2:
                m = new mostro("goblin",7,1,2,10,reference.curr_stanza);
                break;
            case 3:
                m = new mostro("nano",11,3,5,15,reference.curr_stanza);
                break;
            case 4:
                m = new mostro("strega",13,10,3,20,reference.curr_stanza);
                break;
            default:
                m = new mostro();
        }
        m.setX(x);
        m.setY(y);
        m.setSymbol('M');
        return m;
    }
    /**
     * Genera il boss del gioco.
     * @param x Coordinata x del boss.
     * @param y Coordinata y del boss.
     * @return Il boss generato.
     */
    public static mostro generateBoss(int x, int y){
        mostro m;
        m = new mostro("Piovra",30,15,10,50,reference.curr_stanza);
        m.setX(x);
        m.setY(y);
        m.setSymbol('B');
        return m;
    }
    /**
     * Gestisce l'azione di attacco del giocatore.
     */
    public void playerIsAttacking(){
        int dannoplayer = reference.player.getSpada().getDanno();
        int blockprobability;
            if((int)(Math.random()*99) % 2 == 0)
                blockprobability = 0;
            else
                blockprobability = reference.mostrorun.getDifesa();
        reference.mostrorun.takeDamage(blockprobability - dannoplayer);

        if(reference.mostrorun.getVita() <=0){
            if(reference.mostrorun.getSymbol() == 'B'){
                reference.ui.messageTextArea.setText("Hai ucciso il BOSS! Hai Vinto!!");
                reference.player.setMostri_uccisi();
                reference.mostrorun = null;
                reference.player.getSpada().setCanAttack(false);
                reference.player.setCanAttack(false);
                vManager.showWinPanel();
            }else{
                reference.player.setMostri_uccisi();
                reference.currentStanza.cellestanza.get(reference.mostrorun.getY()).set(reference.mostrorun.getX(), Cell.FREE);
                reference.ui.gameB.requestFocus();
                reference.ui.messageTextArea.setText("Hai sconfitto il "+reference.mostrorun.getNome());
                reference.mostrorun = null;
                reference.player.getSpada().setCanAttack(false);
                reference.player.setCanAttack(false);
            }
        }else{
            monsterEncounter(dannoplayer,reference.mostrorun,blockprobability,false);
        }
        reference.ui.commandTextField.setText("");
    }
    /**
     * Gestisce l'azione di fuga del giocatore.
     */
    public void playerIsRunning(){
        if(reference.player.isAttacking() && reference.player.getSpada().CanAttack()){
            //se io incontro mostro e decido di scappare ricevo danni
            reference.ui.messageTextArea.setText("Hai deciso di scappare via\n...\nIl mostro è ancora nelle vicinanze");
            reference.ui.commandTextField.setText("");
            //togliere tutti gli 0 del danno player a monsterencounter
            monsterEncounter(0,reference.mostrorun,0,true);
            reference.mostrorun = null;
            reference.player.setCanAttack(false);
            reference.player.getSpada().setCanAttack(false);
            reference.ui.gameB.requestFocus();
        }else if(reference.player.getSpada().CanAttack()){
            // mostro mi ha incontrato e mi ha gia fatto danni, se scappo non ne ricevo
            reference.ui.messageTextArea.setText("Hai deciso di scappare via\n...\nIl mostro ti starà ancora seguendo?");
            reference.ui.commandTextField.setText("");
            reference.mostrorun = null;
            reference.player.setCanAttack(false);
            reference.player.getSpada().setCanAttack(false);
            reference.ui.gameB.requestFocus();
        }else{
            reference.ui.commandTextField.setText("");
            reference.ui.gameB.requestFocus();
        }
    }

    /**
     * Gestisce l'azione di osservazione della stanza da parte del giocatore.
     */
    public void playerIsLooking(){
        String temp = "Ti guardi intorno...\n";
                        if(reference.currentStanza.lista_item.size() > 0){
                            //migliorare grafica di stampa
                            temp = temp + "{ ";
                            for (int i = 0; i < reference.currentStanza.lista_item.size(); i++) {
                                if((i+1) == reference.currentStanza.lista_item.size())
                                    temp = temp + reference.currentStanza.lista_item.get(i).getNome();

                                temp = temp + reference.currentStanza.lista_item.get(i).getNome() + ", ";
                            }
                            reference.ui.messageTextArea.setText(temp);
                        }else{
                            temp = temp + "\npurtroppo non noti niente di interessante";
                        }
                        reference.functions.updateMonsterPosition();
                        reference.ui.gameB.requestFocus();
                        reference.ui.commandTextField.setText("");
    }
    /**
     * Gestisce l'utilizzo di una pozione da parte del giocatore.
     */
    public void playerUsingPotion(){
        if(reference.player.getNumpozioni() > 0){
            //se è true è il turno del player bevi pozione ma vieni attaccato e torna il tuo turno,non muovi gli altri mostri
            if(reference.player.getSpada().CanAttack() || reference.player.isAttacking()){
                reference.player.usePozioni();
                reference.ui.messageTextArea.setText("Bevi l'intruglio magico\n...\nTi senti molto meglio ora");
                reference.ui.commandTextField.setText("");
                monsterEncounter(0, reference.mostrorun,0, true);
            }else{
                reference.player.usePozioni();
                reference.ui.messageTextArea.setText("Bevi l'intruglio magico\n...\nTi senti molto meglio ora");
                reference.ui.commandTextField.setText("");
                reference.functions.updateMonsterPosition();
            }
            
        }else{
            reference.ui.messageTextArea.setText("Hai finito le pozioni\n...\n...");
            reference.ui.commandTextField.setText("");
        }
    }
    /**
     * Controlla cosa il giocatore ha incontrato nella nuova posizione.
     * @param x Coordinata x della nuova posizione.
     * @param y Coordinata y della nuova posizione.
     * @return true se il giocatore può spostarsi, false altrimenti.
     */
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
                    reference.ui.messageTextArea.setText("Uno strano luccichio ... hai trovato una chiave\n...\n...");
                    return true;
                }else
                    return false;

            case 'F':
                if(reference.player.getPeso() <= 90){
                    reference.player.setGoldKey();
                    reference.ui.messageTextArea.setText("Uno strano luccichio ... hai trovato una chiave d'orata\nchissa che cosa aprirà?\n...");
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
                    reference.ui.messageTextArea.setText("Questa è la porta Nord!\nVuoi attraversarla?\nScrivi [Nord] per entrare altrimenti [no] ");
                    reference.ui.commandTextField.requestFocus();
                }
                break;
            case 'E':
                if(reference.currentStanza.getDrive_to_E() != 0){
                    reference.ui.messageTextArea.setText("Questa è la porta Est!\nVuoi attraversarla?\nScrivi [Est] per entrare altrimenti [no] ");
                    reference.ui.commandTextField.requestFocus();
                }
                break;
            case 'S':
                if(reference.currentStanza.getDrive_to_S() != 0){
                    reference.ui.messageTextArea.setText("Questa è la porta Sud!\nVuoi attraversarla?\nScrivi [Sud] per entrare altrimenti [no] ");
                    reference.ui.commandTextField.requestFocus();
                }
                break;
            case 'W':
                if(reference.currentStanza.getDrive_to_W() != 0){
                    reference.ui.messageTextArea.setText("Questa è la porta Ovest!\nVuoi attraversarla?\nScrivi [Ovest] per entrare altrimenti [no] ");
                    reference.ui.commandTextField.requestFocus();
                }
                break;
            case 'I':
                for (int i = 0; i < reference.currentStanza.lista_item.size(); i++) {
                    if(x == reference.currentStanza.lista_item.get(i).getX() && y == reference.currentStanza.lista_item.get(i).getY() && reference.curr_stanza == reference.currentStanza.lista_item.get(i).getId_stanza()){
                        //ti sei imbattutto in un item
                        if(reference.currentStanza.lista_item.get(i).isIsSword() ){
                            if(reference.player.isHasSword())
                                reference.ui.messageTextArea.setText("Hai trovato una spada! "+reference.currentStanza.lista_item.get(i).getAttacco_max()+" - "+reference.currentStanza.lista_item.get(i).getAttacco_min()+" \nNe possiedi già una. Vuoi scambiarla?\nScrivi [take] per prenderla altrimenti [no] per lasciarla a terra");
                            else{
                                reference.ui.messageTextArea.setText("Hai trovato una spada!Ti servirà per affrontare i mille pericoli "+reference.currentStanza.lista_item.get(i).getAttacco_max()+" - "+reference.currentStanza.lista_item.get(i).getAttacco_min()+"\nVuoi raccoglierla?\nScrivi [take] per prenderla altrimenti [no] per lasciarla a terra ");
                            }
                            reference.item = reference.currentStanza.lista_item.get(i);
                            reference.ui.commandTextField.requestFocus();
                            return false;
                        }else{
                            if(reference.player.isHasArmour())
                                reference.ui.messageTextArea.setText("Hai trovato una armatura! "+reference.currentStanza.lista_item.get(i).getDifesa()+"\nNe possiedi già una. Vuoi scambiarla?\nScrivi [take] per prenderla altrimenti [no] per lasciarla a terra");
                            else{
                                reference.ui.messageTextArea.setText("Hai trovato una armatura!Ti servirà per proteggerti da quelle lancie goblin "+reference.currentStanza.lista_item.get(i).getDifesa()+"\nVuoi raccoglierla?\nScrivi [take] per prenderla altrimenti [no] per lasciarla a terra ");
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
                    reference.ui.messageTextArea.setText("Apri la porta...\n...\nAperta!");
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
                        reference.player.getSpada().setCanAttack(true);
                        reference.player.setCanAttack(true);
                        reference.mostrorun = reference.currentStanza.lista_mostri.get(i);
                    }
                }
                reference.ui.commandTextField.requestFocus();
                return false;
            case 'G':
                if(reference.player.getGoldkey() == 1){
                    reference.player.removeGoldKey();
                    reference.player.setspawnTo('G');
                    reference.ui.messageTextArea.setText("Apri il portone...\n...\nAperto! Benvenuto nella stanza del Boss!");
                    changeRoomAndWriteToFile(reference.currentStanza.getDrive_to_boss());
                    //ridare priorita alla board
                }else{
                    reference.ui.messageTextArea.setText("Che strano portone luminescente...\nHai bisogno di qualche tipo di chiave speciale forse...\n ");
                }
                break;

            default:
                reference.ui.messageTextArea.setText("Oooopsss sembra che qualcosa sia andato storto nel gioco\n...\n...");
        }
        return false;
    }
    /**
     * Gestisce l'incontro con un mostro.
     * @param dannoplayer Danno inflitto dal giocatore.
     * @param monster Mostro incontrato.
     * @param blockprobabilitymonster Probabilità di blocco del mostro.
     * @param attack_turn true se è il turno di attacco del mostro, false altrimenti.
     */
    public void monsterEncounter(int dannoplayer,mostro monster,int blockprobabilitymonster, boolean attack_turn){
        if(monster.getVita() > 0){
            reference.ui.commandTextField.requestFocus();
            int danno = monster.getDanno();
            //boolean serve se attack_turn è true sta attaccando il mostro perchè player si e mosso
            //se false allora entrambi fermi e tocca al giocatore quindi mostri danni fatti da giocatore
            reference.player.getSpada().setCanAttack(true);

            int blockprobability;
            if((int)(Math.random()* 99) % 2 == 0)
                blockprobability = 0;
            else
                blockprobability = reference.player.getArmour().getDifesa();

            if(attack_turn)
                reference.ui.messageTextArea.setText(monster.getNome()+" ti ha inflitto "+danno+" danni, hai bloccato "+blockprobability+" danni\n");
            else
                reference.ui.messageTextArea.setText(monster.getNome()+" ti ha inflitto "+danno+" danni, hai bloccato "+blockprobability+" danni\nHai inflitto "+dannoplayer+" danni il "+monster.getNome()+" ha bloccato "+blockprobabilitymonster+" danni\n"+monster.getNome()+" vita: "+monster.getVita());

            reference.player.takeDamage(blockprobability - danno);

            if(reference.player.getVita() <= 0)
                vManager.showWinPanel();
        }

       }
}
