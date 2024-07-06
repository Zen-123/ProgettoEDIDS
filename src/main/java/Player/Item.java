package Player;

/**
 * Classe che rappresenta un oggetto nel gioco. Estende la classe entity.
 */
public class Item extends entity{
    String nome;
    int attacco_max;
    int attacco_min;
    int difesa;
    boolean isSword;
    int id_stanza;
    int peso;
    boolean hasTake;
    boolean canAttack;
    /**
     * Costruttore di default per la classe Item.
     * Inizializza l'oggetto con valori predefiniti.
     */
    public Item(){
        this.nome = "";
        this.attacco_max = 0;
        this.attacco_min = 0;
        this.difesa = 0;
        this.peso = 0;
        this.isSword = false;
        this.id_stanza = 0;
        this.hasTake = false;
        this.canAttack=false;
    }
    /**
     * Costruttore della classe Item con parametri.
     * @param nome Il nome dell'oggetto.
     * @param attacco_max Il valore massimo di attacco dell'oggetto.
     * @param attacco_min Il valore minimo di attacco dell'oggetto.
     * @param difesa Il valore di difesa dell'oggetto.
     * @param sword Indica se l'oggetto è una spada.
     * @param id L'ID della stanza in cui si trova l'oggetto.
     * @param preso Indica se l'oggetto è stato preso.
     * @param peso Il peso dell'oggetto.
     */
    public Item(String nome,int attacco_max, int attacco_min, int difesa,boolean sword,int id,boolean preso,int peso){
        this.nome = nome;
        this.attacco_max = attacco_max;
        this.attacco_min = attacco_min;
        this.difesa = difesa;
        this.isSword = sword;
        this.id_stanza = id;
        this.hasTake = preso;
        this.canAttack=false;
        this.peso = peso;
    }
    /**
     * Costruttore esteso della classe Item con parametri aggiuntivi per la posizione.
     * @param nome Il nome dell'oggetto.
     * @param attacco_max Il valore massimo di attacco dell'oggetto.
     * @param attacco_min Il valore minimo di attacco dell'oggetto.
     * @param difesa Il valore di difesa dell'oggetto.
     * @param sword Indica se l'oggetto è una spada.
     * @param id L'ID della stanza in cui si trova l'oggetto.
     * @param preso Indica se l'oggetto è stato preso.
     * @param peso Il peso dell'oggetto.
     * @param x La coordinata x dell'oggetto.
     * @param y La coordinata y dell'oggetto.
     * @param symbol Il simbolo che rappresenta l'oggetto.
     */
    public Item(String nome,int attacco_max, int attacco_min, int difesa,boolean sword,int id,boolean preso,int peso,int x, int y , char symbol){
        this.nome = nome;
        this.attacco_max = attacco_max;
        this.attacco_min = attacco_min;
        this.difesa = difesa;
        this.isSword = sword;
        this.id_stanza = id;
        this.hasTake = preso;
        this.canAttack=false;
        this.peso = peso;
        super.x = x;
        super.y = y;
        super.symbol = symbol;
    }
    /**
     * Verifica se l'oggetto può attaccare.
     * @return true se l'oggetto può attaccare, false altrimenti.
     */
    public boolean CanAttack(){
        return this.canAttack;
    }
    /**
     * Imposta la capacità di attacco dell'oggetto.
     * @param can true se l'oggetto può attaccare, false altrimenti.
     */
    public void setCanAttack(boolean can){
        this.canAttack = can;
    }
    /**
     * Calcola e restituisce il danno inflitto dall'oggetto.
     * @return Un valore casuale di danno compreso tra attacco_min e attacco_max.
     */
    public int getDanno(){
        return (int)(Math.random()*(attacco_max - attacco_min) + attacco_min );
    }
    /**
     * Restituisce il nome dell'oggetto.
     * @return Il nome dell'oggetto.
     */
    public String getNome(){
        return nome;
    }
    /**
     * Restituisce il valore massimo di attacco dell'oggetto.
     * @return Il valore massimo di attacco.
     */
    public int getAttacco_max(){
        return attacco_max;
    }
    /**
     * Restituisce il valore minimo di attacco dell'oggetto.
     * @return Il valore minimo di attacco.
     */
    public int getAttacco_min(){
        return attacco_min;
    }
    /**
     * Restituisce il valore di difesa dell'oggetto.
     * @return Il valore di difesa.
     */
    public int getDifesa(){
        return difesa;
    }
    /**
     * Verifica se l'oggetto è una spada.
     * @return true se l'oggetto è una spada, false altrimenti.
     */
    public boolean isIsSword() {
        return isSword;
    }
    /**
     * Restituisce l'ID della stanza in cui si trova l'oggetto.
     * @return L'ID della stanza.
     */
    public int getId_stanza() {
        return id_stanza;
    }
    /**
     * Imposta l'ID della stanza in cui si trova l'oggetto.
     * @param id_stanza Il nuovo ID della stanza.
     */
    public void setId_stanza(int id_stanza) {
        this.id_stanza = id_stanza;
    }
    /**
     * Verifica se l'oggetto è stato preso.
     * @return true se l'oggetto è stato preso, false altrimenti.
     */
    public boolean isHasTake() {
        return hasTake;
    }
    /**
     * Imposta lo stato di preso dell'oggetto.
     * @param hasTake true se l'oggetto è stato preso, false altrimenti.
     */
    public void setHasTake(boolean hasTake) {
        this.hasTake = hasTake;
    }
    /**
     * Restituisce il peso dell'oggetto.
     * @return Il peso dell'oggetto.
     */
    public int getPeso() {
        return peso;
    }
    /**
     * Imposta il peso dell'oggetto.
     * @param peso Il nuovo peso dell'oggetto.
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }
}