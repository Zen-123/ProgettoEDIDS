package Board;

/**
 * Enum che rappresenta i diversi tipi di celle presenti nella mappa di gioco.
 * Ogni cella è associata ad un simbolo che la rappresenta nel file di testo.
 */
public enum Cell{
    /** Cella libera. */
    FREE('.'),

    /** Muro. */
    WALL('#'),

    /** Porta Nord. */
    NORD('N'),

    /** Porta Est. */
    EST('E'),

    /** Porta Sud. */
    SUD('S'),

    /** Porta Ovest. */
    OVEST('W'),

    /** Posizione del giocatore. */
    PLAYER('A'),

    /** Oggetto. */
    ITEM('I'),

    /** Chiave d'oro. */
    GOLDKEY('F'),

    /** Chiave normale. */
    KEY('f'),

    /** Entrata della stanza del boss. */
    BOSSROOM('G'),

    /** Porta. */
    PORTA('P'),

    /** Boss. */
    BOSS('B'),

    /** Pozione. */
    POZIONE('H'),

    /** Moneta. */
    COIN('C'),

    /** Mostro. */
    MONSTER('M');

    /** Il simbolo associato alla cella. */

    private char symbol;

    /**
     * Costruttore per l'enum Cell.
     * @param symbol Il carattere simbolo associato alla cella.
     */
    Cell(char symbol){
        this.symbol = symbol;
    }
    /**
     * Restituisce il simbolo associato alla cella.
     * @return Il carattere simbolo della cella.
     */
    public char getSymbol(){
        return symbol;
    }

    /**
     * Imposta un nuovo simbolo per la cella.
     * @param s Il nuovo carattere simbolo da assegnare alla cella.
     */
    public void setSymbol(char s){
        this.symbol = s;
    }
}
