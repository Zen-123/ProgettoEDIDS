package Player;
/**
 * Classe per gestire le entità che possono muoversi all'interno del gioco, come giocatori e mostri.
 */
public class entity {
    /** Coordinata x dell'entità. */
    protected int x;

    /** Coordinata y dell'entità. */
    protected int y;

    /** Simbolo che rappresenta l'entità. */
    protected char symbol;

    /**
     * Restituisce la coordinata x dell'entità.
     * @return La coordinata x dell'entità.
     */
    public int getX() {
        return x;
    }

    /**
     * Restituisce la coordinata y dell'entità.
     * @return La coordinata y dell'entità.
     */
    public int getY() {
        return y;
    }

    /**
     * Restituisce il simbolo che rappresenta l'entità.
     * @return Il simbolo dell'entità.
     */
    public char getSymbol() {
        return symbol;
    }
    /**
     * Imposta il simbolo che rappresenta l'entità.
     * @param symbol Il nuovo simbolo da assegnare all'entità.
     */
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Imposta la coordinata y dell'entità.
     * @param y La nuova coordinata y da assegnare all'entità.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Imposta la coordinata x dell'entità.
     * @param x La nuova coordinata x da assegnare all'entità.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Imposta sia la coordinata x che la coordinata y dell'entità.
     * @param x La nuova coordinata x da assegnare all'entità.
     * @param y La nuova coordinata y da assegnare all'entità.
     */
    public void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
