package Player;
//classe mostro 
public class mostro extends entity{

    private String nome;
    private int danno_max;
    private int danno_min;
    private int difesa;
    private int vita;
    private int idstanza;

    public mostro(){}

    public mostro(String nome, int danno_max,int danno_min, int difesa, int vita, int id_stanza){
        this.nome=nome;
        this.difesa=difesa;
        this.danno_max = danno_max;
        this.danno_min = danno_min;
        this.vita = vita;
        this.idstanza= id_stanza;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDanno() {
        return (int)((Math.random() * (danno_max - danno_min)) + danno_min);
    }
    public int getDifesa() {
        return difesa;
    }

    public void setDifesa(int difesa) {
        this.difesa = difesa;
    }

    public int getVita() {
        return vita;
    }

    public void setVita(int vita) {
        this.vita = vita;
    }
    public void takeDamage(int danno){
        if(danno < 0)
            this.vita += danno; 
    }
    public int getIdstanza() {
        return idstanza;
    }

    public void setIdstanza(int idstanza) {
        this.idstanza = idstanza;
    }

    public int getDanno_max() {
        return danno_max;
    }

    public void setDanno_max(int danno_max) {
        this.danno_max = danno_max;
    }

    public int getDanno_min() {
        return danno_min;
    }

    public void setDanno_min(int danno_min) {
        this.danno_min = danno_min;
    }
}
