package Player;

public class Item extends entity{
    String nome;
    int attacco_max;
    int attacco_min;
    int difesa;
    boolean isSword;
    
    public Item(String nome,int attacco_max, int attacco_min, int difesa,boolean sword){
        this.nome = nome;
        this.attacco_max = attacco_max;
        this.attacco_min = attacco_min;
        this.difesa = difesa;
        this.isSword = sword;
    }
    public int getDanno(){
        int danno = (int)(Math.random()*(attacco_max - attacco_min) + attacco_min );
        return danno;
    }
    public String getNome(){
        return nome;
    }
    public int getAttacco_max(){
        return attacco_max;
    }
    public int getAttacco_min(){
        return attacco_min;    
    }
    public int getDifesa(){
        return difesa;
    }
    public boolean isIsSword() {
        return isSword;
    }
}
