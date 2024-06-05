package Player;
//classe x gestire le entita che possono muoversi all'interno del gioco player,monster
public class entity {
    private int x;
    private int y;
    private char symbol;

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
    
}
