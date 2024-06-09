package Board;

import Player.Item;
import Player.mostro;
//classe x gestire metodi globali per il funzionamento del gicoo
public class func{
    
    public int pointto_x;
    public int pointto_y;   

    public func(){
        this.pointto_x=0;
        this.pointto_y=0;
        
    }
    //genera un item e lo aggiunge alla lista della stanza, chiamato in fase di lettura del file
    public static Item generateItem(int x, int y){
        int choose = (int)(Math.random() * 7);
        Item a;
        if(choose % 2 == 0){//pari è spada
            a = new Item("spada", (int)(Math.random() * 15) + 6, (int)(Math.random() * 5) + 3, 0,true,reference.curr_stanza,false);
            a.setX(x);
            a.setY(y);
            a.setSymbol('I');
        }else{//dispari è un armatura
            a = new Item("armatura", 0, 0, (int)((Math.random() * 15) + 2),false,reference.curr_stanza,false);
            a.setX(x);
            a.setY(y);
            a.setSymbol('I');
        }
        return a;
    }
    //metodo per spostare mostri, var check per disattivare o attivare metodo
    //se non siamo presenti nella stanza del boss questo metodo è attivo = evitiamo nullp updateBossPos
    public void updateMonsterPos(boolean check){

    }
    //metodo per spostare boss, var check per disattivare o attivare metodo
    //se siamo presenti nella stanza del boss questo metodo è attivo = evitiamo nullp in updateMonsterPos
    public void updateBossPos(boolean check){

    }

    public static mostro generateMonster(int x, int y){
        mostro m;
        int choose = (int)(Math.random() * 5);
        switch(choose) {
            case 0:
                m = new mostro("vampiro",18,5,7,30,reference.curr_stanza);
                break;
            case 1:
                m = new mostro("scheletro",5,1,1,5,reference.curr_stanza);
                break;
            case 2:
                m = new mostro("goblin",8,1,2,10,reference.curr_stanza);
                break;
            case 3:
                m = new mostro("nano",11,3,5,15,reference.curr_stanza);
                break;
            case 4:
                m = new mostro("strega",15,5,3,20,reference.curr_stanza);
                break;
            default:
                m= new mostro(); 
        }
        m.setX(x);
        m.setY(y);
        m.setSymbol('M');
        return m;
    }
    public static mostro generateBoss(int x, int y){
        mostro m;
        m = new mostro("Piovra",20,10,8,5,reference.curr_stanza);
        m.setX(x);
        m.setY(y);
        m.setSymbol('B');
        return m;
    }
    public int getPointto_x() {
        return pointto_x;
    }

    public void setPointto_x(int pointto_x) {
        this.pointto_x = pointto_x;
    }

    public int getPointto_y() {
        return pointto_y;
    }

    public void setPointto_y(int pointto_y) {
        this.pointto_y = pointto_y;
    }
}
