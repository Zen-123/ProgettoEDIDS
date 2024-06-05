package Board;

import Player.Item;
//classe x gestire metodi globali per il funzionamento del gicoo
public class func{
    
    public func(){
        
    }
    //genera un item e lo aggiunge alla lista della stanza, chiamato in fase di lettura del file
    public static Item generateItem(int x, int y){
        int choose = (int)(Math.random() * 7);
        Item a;
        if(choose % 2 == 0){//pari è spada
            a = new Item("spada", (int)(Math.random() * 15) + 6, (int)(Math.random() * 5), 0,true);
            a.setX(x);
            a.setY(y);
            a.setSymbol('I');
        }else{//dispari è un armatura
            a = new Item("armatura", 0, 0, (int)((Math.random() * 15) + 2),false);
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
}
