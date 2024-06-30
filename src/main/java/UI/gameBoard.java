package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Board.Cell;
import Board.reference;

public class gameBoard extends JPanel implements KeyListener {
    public gameBoard(){
        addKeyListener(this);
        this.setFocusable(true);
        makesomething();
    }
    //metodo che colora la mappa ogni volta che cè un cambiamento
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
        g.drawString("Monster killed: "+reference.player.getMostriuccisi(), 560, 240);

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
                    if(reference.functions.checkwhatyoubumped(x, y-1)){
                        reference.currentStanza.cellestanza.get(y-1).set(x,Cell.PLAYER);
                        reference.currentStanza.cellestanza.get(y).set(x,Cell.FREE);
                        reference.player.setY(y - 1);
                        reference.functions.updateMonsterPosition();
                    }
                    break;
                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    if(reference.functions.checkwhatyoubumped(x + 1, y)){
                        reference.currentStanza.cellestanza.get(y).set(x+1,Cell.PLAYER);
                        reference.currentStanza.cellestanza.get(y).set(x,Cell.FREE);
                        reference.player.setX(x + 1);
                        reference.functions.updateMonsterPosition();
                    }
                    break;
                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                    if(reference.functions.checkwhatyoubumped(x - 1, y)){
                        reference.currentStanza.cellestanza.get(y).set(x-1,Cell.PLAYER);
                        reference.currentStanza.cellestanza.get(y).set(x,Cell.FREE);
                        reference.player.setX(x - 1);
                        reference.functions.updateMonsterPosition();
                    }
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    if(reference.functions.checkwhatyoubumped(x, y + 1)){
                        reference.currentStanza.cellestanza.get(y + 1).set(x,Cell.PLAYER);
                        reference.currentStanza.cellestanza.get(y).set(x,Cell.FREE);
                        reference.player.setY(y + 1);
                        reference.functions.updateMonsterPosition();
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
    //gestisce movimento mostri e interazioni del player sul commandtext
   public void makesomething(){
    reference.ui.commandTextField.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                switch(reference.ui.commandTextField.getText().toLowerCase()) {
                    case "take":
                    if(reference.item != null){
                        reference.functions.takeItem();  
                    }else{
                        reference.ui.commandTextField.setText(""); 
                    }
                    
                    break;
                    case "no": 
                        if(reference.player.isAttacking() || reference.player.getSpada().CanAttack()){
                            reference.ui.commandTextField.setText("");
                        }
                        else{
                            reference.ui.commandTextField.setText("");
                            reference.item = null;
                            reference.ui.messageTextArea.setText("...\n...\n...");
                            reference.ui.gameB.requestFocus();
                        }
                    break;
                    case "apri":
                    reference.functions.updateMonsterPosition();
                        reference.ui.gameB.requestFocus();
                    break;
                    case "non aprire":
                        reference.ui.gameB.requestFocus();
                    break;
                    case "pozione":
                        reference.functions.playerUsingPotion();
                    break;
                    case "nord":
                        reference.player.setspawnTo('S');
                        reference.functions.changeRoomAndWriteToFile(reference.currentStanza.getDrive_to_N());
                        break;
                    case "sud":
                        reference.player.setspawnTo('N');
                        reference.functions.changeRoomAndWriteToFile(reference.currentStanza.getDrive_to_S());
                        break;
                    case "est":
                        reference.player.setspawnTo('W');
                        reference.functions.changeRoomAndWriteToFile(reference.currentStanza.getDrive_to_E());
                        break;
                    case "ovest":
                        reference.player.setspawnTo('E');
                        reference.functions.changeRoomAndWriteToFile(reference.currentStanza.getDrive_to_W());
                        break;
                    case "attack":
                        if(reference.currentStanza.lista_mostri.size() > 0 && reference.player.getSpada().CanAttack()){
                            reference.functions.playerIsAttacking();
                        }else{
                            reference.ui.gameB.requestFocus();
                            reference.ui.commandTextField.setText("");
                        }  
                        break;
                    case "run":
                        reference.functions.playerIsRunning();
                    break;
                    case "look":
                        reference.functions.playerIsLooking();
                    break;
                    default:
                        reference.ui.commandTextField.setText("");
                }
            }else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                if(reference.player.isAttacking() || reference.player.getSpada().CanAttack()){
                    reference.ui.commandTextField.setText("");
                }else{
                    reference.ui.commandTextField.setText("");
                    reference.ui.gameB.requestFocus();
                    reference.ui.messageTextArea.setText("...\n...\n...");
                }
                
            }
        }
    });
   }
}
