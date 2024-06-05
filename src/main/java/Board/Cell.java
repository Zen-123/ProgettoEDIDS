package Board;

//classe gestisce singola cella della mia scacchiera
public enum Cell{

    FREE('.'),
    WALL('#'),
    NORD('N'),
    EST('E'),
    SUD('S'),
    OVEST('W'),
    PLAYER('A'),
    ITEM('I'),
    GOLDKEY('F'),
    KEY('f'),
    BOSSROOM('G'),
    PORTA('P'),
    BOSS('B'),
    POZIONE('H'),
    COIN('C'),
    MONSTER('M');

    private char symbol;
    Cell(char symbol){
        this.symbol = symbol;
    }
    public char getSymbol(){
        return symbol;
    }
    public void setSymbol(char s){
        this.symbol = s;
    }
}
