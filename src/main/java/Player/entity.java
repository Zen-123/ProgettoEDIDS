package Player;
//classe x gestire le entita che possono muoversi all'interno del gioco player,monster
public class entity {
    protected static int x;
    protected static int y;
    protected static char symbol;

    /* public entity(){
        this.x = 0;
        this.y = 0;
        this.symbol = ' ';
    } */
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public char getSymbol() {
        return symbol;
    }
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setCoordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
}
