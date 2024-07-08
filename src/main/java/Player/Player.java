package Player;


/**
 * Classe che rappresenta un giocatore nel gioco, estende la classe entity.
 */
public class Player extends entity{
    private String nome;
    private int vita;
    private int peso;
    private String category;
    private int num_pozioni;
    private int monete;
    private int mostri_uccisi;
    private Item spada;
    private Item armatura;
    private boolean hasSword;
    private boolean hasArmour;
    private int key;
    private int goldkey;
    private char spawnInto;
    private boolean canAttack;
    private int id;
    /**
     * Costruttore di default per la classe Player.
     * Inizializza il giocatore con valori predefiniti.
     */
    public Player(){
        this.nome = " ";
        this.category = " ";
        this.spada = new Item();
        this.vita = 100;
        this.armatura = new Item();
        this.hasSword = true;
        this.hasArmour = false;
        this.peso = 0;
        this.monete = 0;
        this.mostri_uccisi = 0;
        this.key = 0;
        this.goldkey = 0;
        this.num_pozioni = 0;
    }
    /**
     * Restituisce il danno massimo della spada del giocatore.
     * @return Il danno massimo della spada.
     */
    public int getDannoMaxSpada() {
        return spada.getAttacco_max();
    }

    /**
     * Restituisce il danno minimo della spada del giocatore.
     * @return Il danno minimo della spada.
     */
    public int getDannoMinSpada() {
        return spada.getAttacco_min();
    }
    /**
     * Restituisce il nome del giocatore.
     * @return Il nome del giocatore.
     */
    public String getNome(){
        return nome;
    }
    /**
     * Aggiunge una spada all'inventario del giocatore.
     * @param item L'oggetto spada da aggiungere.
     */
    public void addSpada(Item item){
        item.setCanAttack(false);
        this.peso += item.getPeso();
        this.spada = item;
        this.hasSword = true;
    }
    /**
     * Aggiunge un'armatura all'inventario del giocatore.
     * @param item L'oggetto armatura da aggiungere.
     */
    public void addArmour(Item item){
        item.setCanAttack(false);
        this.peso += item.getPeso();
        this.armatura = item;
        this.hasArmour = true;
    }
    /**
     * Rimuove una chiave dall'inventario del giocatore.
     */
    public void removeKey(){
        this.key -= 1;
        this.peso -= 5;
    }
    /**
     * Imposta il carattere di spawn del giocatore.
     * @param s Il carattere di spawn.
     */
    public void setspawnTo(char s){
        this.spawnInto = s;
    }
    /**
     * Restituisce il carattere di spawn del giocatore.
     * @return Il carattere di spawn.
     */
    public char spawnTo(){
        return this.spawnInto;
    }
    /**
     * Rimuove una chiave d'oro dall'inventario del giocatore.
     */
    public void removeGoldKey(){
        this.goldkey -= 1;
        this.peso -= 10;
    }
    /**
     * Restituisce l'oggetto spada del giocatore.
     * @return L'oggetto spada.
     */
    public Item getSpada(){
        return spada;
    }
    /**
     * Restituisce il nome della spada del giocatore.
     * @return Il nome della spada.
     */
    public String getSpadaName(){
        return spada.nome;
    }
    /**
     * Restituisce il nome dell'armatura del giocatore.
     * @return Il nome dell'armatura.
     */
    public String getArmourName(){
        return armatura.nome;
    }
    /**
     * Imposta il nome della spada del giocatore.
     * @param name Il nuovo nome della spada.
     */
    public void setSpadaName(String name){
        spada.nome = name;
    }
    /**
     * Imposta il nome dell'armatura del giocatore.
     * @param name Il nuovo nome dell'armatura.
     */
    public void setArmourName(String name){
        armatura.nome = name;
    }
    /**
     * Imposta il danno massimo della spada del giocatore.
     * @param damage Il nuovo valore di danno massimo.
     */
    public void setMaxDamage(int damage){
        spada.attacco_max = damage;
    }
    /**
     * Imposta il danno minimo della spada del giocatore.
     * @param damage Il nuovo valore di danno minimo.
     */
    public void setMinDamage(int damage){
        spada.attacco_min = damage;
    }
    /**
     * Restituisce l'oggetto armatura del giocatore.
     * @return L'oggetto armatura.
     */
    public Item getArmour(){
        return armatura;
    }
    /**
     * Restituisce i punti vita attuali del giocatore.
     * @return I punti vita attuali.
     */
    public int getVita() {
        return vita;
    }
    /**
     * Restituisce il peso totale dell'inventario del giocatore.
     * @return Il peso totale dell'inventario.
     */
    public int getPeso() {
        return this.peso;
    }
    /**
     * Imposta il peso totale dell'inventario del giocatore.
     * @param peso Il nuovo peso totale.
     */
    public void setPeso(int peso){
        this.peso = peso;
    }
    /**
     * Restituisce il numero di pozioni possedute dal giocatore.
     * @return Il numero di pozioni.
     */
    public int getNumpozioni() {
        return num_pozioni;
    }
    /**
     * Aggiunge una pozione all'inventario del giocatore.
     */
    public void addPozioni(){
        num_pozioni++;
        this.peso += 5;
    }

    /**
     * Usa una pozione per curare il giocatore.
     */
    public void usePozioni(){
        if(this.num_pozioni > 0){
            num_pozioni--;
            if(this.vita > 70){
                this.vita = 100;
                this.peso -= 5;
            }else{
                this.vita += 30;
                this.peso -= 5;
            }   
        }
    }
    /**
     * Verifica se il giocatore sta attaccando.
     * @return true se il giocatore sta attaccando, false altrimenti.
     */
    public boolean isAttacking(){
        return this.canAttack;
    }
    /**
     * Imposta lo stato di attacco del giocatore.
     * @param b true se il giocatore può attaccare, false altrimenti.
     */
    public void setCanAttack(boolean b){
        this.canAttack = b;
    }
    /**
     * Prende un oggetto e lo aggiunge all'inventario del giocatore.
     * @param item L'oggetto da aggiungere all'inventario.
     */
    public void takeItem(Item item){
        if(item != null && item.isSword){
            item.setCanAttack(true);
            this.spada = item;
        }else{
            item.setCanAttack(false);
            this.armatura = item;
        }      
    }
    /**
     * Restituisce il numero di monete possedute dal giocatore.
     * @return Il numero di monete.
     */
    public int getMonete() {
        return monete;
    }
    /**
     * Restituisce il numero di mostri uccisi dal giocatore.
     * @return Il numero di mostri uccisi.
     */
    public int getMostriuccisi() {
        return mostri_uccisi;
    }
    /**
     * Verifica se il giocatore possiede una spada.
     * @return true se il giocatore ha una spada, false altrimenti.
     */
    public boolean isHasSword() {
        return hasSword;
    }
    /**
     * Verifica se il giocatore è vivo.
     * @return true se il giocatore è vivo, false altrimenti.
     */
    public boolean isAlive(){
        if(this.vita <= 0)
            return false;
        else
            return true;    
    }

    /**
     * Imposta le monete del giocatore in fase di load
     * @param money monete da aggiungere trovate nel file durante la fase di load
     */
    public void setMoneteLoad(int money){
        this.monete = money;
    }

    /**
     * Aggiorna le monete possedute dal giocatore
     * @param amount nuove monete da aggiungere
     */
    public void setMonete(int amount) {
        this.monete += amount;
    }

    /**
     * Aggiorna numero di chiavi possedute dal giocatore
     */
    public void setKey(){

        this.key +=1;
        peso += 5;
    }

    /**
     * Imposta il numero di chiavi del giocatore in fase di load.
     * @param key numero di chiavi lette dal file
     */

    public void setKeyLoad(int key){
        if(this.peso == 0){
            this.peso += 5*key;
        }
        this.key = key;

    }

    /**
     * Imposta la goldenKey
     */
    public void setGoldKey(){
        this.goldkey +=1;
        peso += 10;
    }

    /**
     * Aggiorna il numero di posioni del giocatore
     * @param v numero di pozioni
     */
    public void addNum_pozioni(int v) {
        this.peso += (v*5);
        this.num_pozioni = v;
    }

    /**
     * restituisce il numero di chiavi possedute dal giocatore.
     * @return key numero di chiavi possedute dal giocatore.
     */
    public int getKey() {
        return key;
    }

    /**
     * Restituisce il numero di goldenKey possedute dal giocatore.
     * @return goldenKey numero di goldenKey possedute dal giocatore.
     */
    public int getGoldkey() {
        return goldkey;
    }

    /**
     * Imposta il nome del giocatore.
     * @param nome nome del giocatore
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Imposta il numero di pozioni del giocatore.
     * @param pozioni numero di pozioni possedute
     */
    public void setNum_pozioni(int pozioni) {
        this.num_pozioni = pozioni;
    }

    /**
     * Restituisce la categoria del giocatore.
     * @return category la categoria del giocatore
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Imposta la categoria del giocatore.
     * @param category la categoria del giocatore.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Restituisce il flag per verificare se il giocatore ha l'armature
     * @return hasArmour flag che segnala se giocatore possiede un armatura
     */
    public boolean isHasArmour() {
        return hasArmour;
    }

    /**
     * Flag che segnala se il giocatore ha un armatura nell'inventario
     * @param hasArmour
     */
    public void setHasArmour(boolean hasArmour) {
        this.hasArmour = hasArmour;
    }

    /**
     * Imposta la vita del giocatore.
     * @param vita
     */
    public void setVita(int vita) {
        this.vita = vita;
    }

    /**
     * Infligge danno al giocatore.
     * @param danno La quantità di danno da infliggere.
     */
    public void takeDamage(int danno){
        if(danno < 0)
            this.vita += danno; 
    }
    /**
     * Incrementa il contatore dei mostri uccisi.
     */
    public void setMostri_uccisi() {
        this.mostri_uccisi ++;
    }

    /**
     * Imposta il contatore dei mostri uccisi in fase di load dei dati.
     * @param killed
     */
    public void setMostriUccisiLoad(int killed) {
        this.mostri_uccisi = killed;
    }

    /**
     * Inserisce la goldenKey nell'inventario del giocatore.
     * @param goldkey
     */
    public void setGoldkeyAWS(int goldkey) {
        this.goldkey = goldkey;
    }
    /**
     * Restituisce l'ID del giocatore.
     * @return L'ID del giocatore.
     */
    public int getID(){
        return this.id;
    }
    /**
     * Imposta l'ID del giocatore.
     * @param  a L'ID del giocatore.'
     */
    public void setID(int a){
        this.id = a;
    }
}