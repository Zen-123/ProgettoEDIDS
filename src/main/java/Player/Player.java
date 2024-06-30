package Player;
import Board.reference;

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
    public int getDannoMaxSpada() {
        return spada.getAttacco_max();
    }
    public int getDannoMinSpada() {
        return spada.getAttacco_min();
    }
    public String getNome(){
        return nome;
    }
    public void addSpada(Item item){
        item.setCanAttack(false);
        this.peso += 20;
        this.spada = item;
    }
    public void addArmour(Item item){
        item.setCanAttack(false);
        this.peso += 30;
        this.armatura = item;
    }
    public void removeKey(){
        this.key -= 1;
        this.peso -= 5;
    }
    public void setspawnTo(char s){
        this.spawnInto = s;
    }
    public char spawnTo(){
        return this.spawnInto;
    }
    public void removeGoldKey(){
        this.goldkey -= 1;
    }
    public Item getSpada(){
        return spada;
    }

    public String getSpadaName(){
        return spada.nome;
    }

    public String getArmourName(){
        return armatura.nome;
    }
    public void setSpadaName(String name){
        spada.nome = name;
    }

    public void setArmourName(String name){
        armatura.nome = name;
    }

    public void setMaxDamage(int damage){
        spada.attacco_max = damage;
    }

    public void setMinDamage(int damage){
        spada.attacco_min = damage;
    }
    public Item getArmour(){
        return armatura;
    }
    public int getVita() {
        return vita;
    }
    public int getPeso() {
        return this.peso;
    }
    public void setPeso(int peso){
        this.peso = peso;
    }
    public int getNumpozioni() {
        return num_pozioni;
    }
    public void addPozioni(){
        num_pozioni++;
        this.peso += 5;
    }

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
    public boolean isAttacking(){
        return this.canAttack;
    }
    public void setCanAttack(boolean b){
        this.canAttack = b;
    }
    public void takeItem(Item item){
        if(item != null && item.isSword){
            item.setCanAttack(true);
            this.spada = item;
        }else{
            item.setCanAttack(false);
            this.armatura = item;
        }      
    }
    public int getMonete() {
        return monete;
    }
    public int getMostriuccisi() {
        return mostri_uccisi;
    }
    public boolean isHasSword() {
        return hasSword;
    }
    public boolean isAlive(){
        if(this.vita <= 0)
            return false;
        else
            return true;    
    }
    public void setMoneteLoad(int money){
        this.monete = money;
    }
    public void setMonete(int amount) {
        this.monete += amount;
    }
    public void setKey(){
        this.key +=1;
        peso += 5;
    }

    public void setKeyLoad(int key){
        if(this.peso == 0){
            this.peso += 5*key;
        }
        this.key = key;

    }
    public void setGoldKey(){
        this.goldkey +=1;
        peso += 10;
    }
    public void addNum_pozioni(int v) {
        this.peso += (v*5);
        this.num_pozioni = v;
    }
    public int getKey() {
        return key;
    }
    public int getGoldkey() {
        return goldkey;
    }  
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setNum_pozioni(int pozioni) {this.num_pozioni = pozioni;}
    public String getCategory() {
        return this.category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public boolean isHasArmour() {
        return hasArmour;
    }
    public void setHasArmour(boolean hasArmour) {
        this.hasArmour = hasArmour;
    }
    public void setVita(int vita) {
        this.vita = vita;
    }
    public void takeDamage(int danno){
        if(danno < 0)
            this.vita += danno; 
    }
    public void setMostri_uccisi() {
        this.mostri_uccisi ++;
    }

    public void setMostriUccisiLoad(int killed) {
        this.mostri_uccisi = killed;
    }

}