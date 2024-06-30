package Player;

public class Item extends entity{
    String nome;
    int attacco_max;
    int attacco_min;
    int difesa;
    boolean isSword;
    int id_stanza;
    boolean hasTake;
    boolean canAttack;

    public Item(){
        this.nome = "";
        this.attacco_max = 0;
        this.attacco_min = 0;
        this.difesa = 0;
        this.isSword = false;
        this.id_stanza = 0;
        this.hasTake = false;
        this.canAttack=false;
    }
    public Item(String nome,int attacco_max, int attacco_min, int difesa,boolean sword,int id,boolean preso){
        this.nome = nome;
        this.attacco_max = attacco_max;
        this.attacco_min = attacco_min;
        this.difesa = difesa;
        this.isSword = sword;
        this.id_stanza = id;
        this.hasTake = preso;
        this.canAttack=false;
    }
    public boolean CanAttack(){
        return this.canAttack;
    }
    public void setCanAttack(boolean can){
        this.canAttack = can;
    }
    public int getDanno(){
        return (int)(Math.random()*(attacco_max - attacco_min) + attacco_min );
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
    public int getId_stanza() {
        return id_stanza;
    }
    public void setId_stanza(int id_stanza) {
        this.id_stanza = id_stanza;
    }
    public boolean isHasTake() {
        return hasTake;
    }
    public void setHasTake(boolean hasTake) {
        this.hasTake = hasTake;
    }
}