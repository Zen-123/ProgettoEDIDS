package Player;
/**
 * Classe che rappresenta un mostro nel gioco. Estende la classe entity.
 */
public class mostro extends entity{

    private String nome;
    private int danno_max;
    private int danno_min;
    private int difesa;
    private int vita;
    private int idstanza;
    /**
     * Costruttore vuoto per la classe mostro.
     */
    public mostro(){}
    /**
     * Costruttore per la classe mostro.
     * @param nome Il nome del mostro.
     * @param danno_max Il danno massimo che il mostro può infliggere.
     * @param danno_min Il danno minimo che il mostro può infliggere.
     * @param difesa Il valore di difesa del mostro.
     * @param vita I punti vita del mostro.
     * @param id_stanza L'ID della stanza in cui si trova il mostro.
     */
    public mostro(String nome, int danno_max,int danno_min, int difesa, int vita, int id_stanza){
        this.nome=nome;
        this.difesa=difesa;
        this.danno_max = danno_max;
        this.danno_min = danno_min;
        this.vita = vita;
        this.idstanza= id_stanza;
    }
    /**
     * Costruttore esteso per la classe mostro.
     * @param nome Il nome del mostro.
     * @param danno_max Il danno massimo che il mostro può infliggere.
     * @param danno_min Il danno minimo che il mostro può infliggere.
     * @param difesa Il valore di difesa del mostro.
     * @param vita I punti vita del mostro.
     * @param id_stanza L'ID della stanza in cui si trova il mostro.
     * @param x La coordinata x del mostro.
     * @param y La coordinata y del mostro.
     * @param symbol Il simbolo che rappresenta il mostro.
     */
    public mostro(String nome, int danno_max,int danno_min, int difesa, int vita, int id_stanza, int x, int y , char symbol){
        this.nome=nome;
        this.difesa=difesa;
        this.danno_max = danno_max;
        this.danno_min = danno_min;
        this.vita = vita;
        this.idstanza= id_stanza;
        super.x = x;
        super.y = y;
        super.symbol = symbol;
    }
    /**
     * Restituisce il nome del mostro.
     * @return Il nome del mostro.
     */
    public String getNome() {
        return nome;
    }
    /**
     * Imposta il nome del mostro.
     * @param nome Il nuovo nome del mostro.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Calcola e restituisce il danno inflitto dal mostro.
     * @return Un valore casuale di danno compreso tra danno_min e danno_max.
     */
    public int getDanno() {
        return (int)((Math.random() * (danno_max - danno_min)) + danno_min);
    }
    /**
     * Restituisce il valore di difesa del mostro.
     * @return Il valore di difesa del mostro.
     */
    public int getDifesa() {
        return difesa;
    }
    /**
     * Imposta il valore di difesa del mostro.
     * @param difesa Il nuovo valore di difesa.
     */
    public void setDifesa(int difesa) {
        this.difesa = difesa;
    }
    /**
     * Restituisce i punti vita attuali del mostro.
     * @return I punti vita attuali del mostro.
     */
    public int getVita() {
        return vita;
    }
    /**
     * Imposta i punti vita del mostro.
     * @param vita I nuovi punti vita del mostro.
     */
    public void setVita(int vita) {
        this.vita = vita;
    }
    /**
     * Infligge danno al mostro.
     * @param danno La quantità di danno da infliggere.
     */
    public void takeDamage(int danno){
        if(danno < 0)
            this.vita += danno; 
    }
    /**
     * Restituisce l'ID della stanza in cui si trova il mostro.
     * @return L'ID della stanza.
     */
    public int getIdstanza() {
        return idstanza;
    }
    /**
     * Imposta l'ID della stanza in cui si trova il mostro.
     * @param idstanza Il nuovo ID della stanza.
     */
    public void setIdstanza(int idstanza) {
        this.idstanza = idstanza;
    }
    /**
     * Restituisce il danno massimo che il mostro può infliggere.
     * @return Il danno massimo.
     */
    public int getDanno_max() {
        return danno_max;
    }
    /**
     * Imposta il danno massimo che il mostro può infliggere.
     * @param danno_max Il nuovo valore di danno massimo.
     */
    public void setDanno_max(int danno_max) {
        this.danno_max = danno_max;
    }
    /**
     * Restituisce il danno minimo che il mostro può infliggere.
     * @return Il danno minimo.
     */
    public int getDanno_min() {
        return danno_min;
    }
    /**
     * Imposta il danno minimo che il mostro può infliggere.
     * @param danno_min Il nuovo valore di danno minimo.
     */
    public void setDanno_min(int danno_min) {
        this.danno_min = danno_min;
    }
}
