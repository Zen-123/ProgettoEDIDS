package Board;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    //test di tutti i simboli presenti nella classe Cell
    void testEnumValues() {
        assertEquals(16, Cell.values().length);
    }

    @Test
    //test del metodo getSymbol per tutti i simboli presenti nella classe Cell
    void testSymbols() {
        assertEquals('.', Cell.FREE.getSymbol());
        assertEquals('#', Cell.WALL.getSymbol());
        assertEquals('N', Cell.NORD.getSymbol());
        assertEquals('E', Cell.EST.getSymbol());
        assertEquals('S', Cell.SUD.getSymbol());
        assertEquals('W', Cell.OVEST.getSymbol());
        assertEquals('A', Cell.PLAYER.getSymbol());
        assertEquals('I', Cell.ITEM.getSymbol());
        assertEquals('F', Cell.GOLDKEY.getSymbol());
        assertEquals('f', Cell.KEY.getSymbol());
        assertEquals('G', Cell.BOSSROOM.getSymbol());
        assertEquals('P', Cell.PORTA.getSymbol());
        assertEquals('B', Cell.BOSS.getSymbol());
        assertEquals('H', Cell.POZIONE.getSymbol());
        assertEquals('C', Cell.COIN.getSymbol());
        assertEquals('M', Cell.MONSTER.getSymbol());
    }

    @Test
    //test metodo setSymbol
    void testSetSymbol() {
        Cell cell = Cell.FREE;
        cell.setSymbol('X');
        assertEquals('X', cell.getSymbol());
    }

    @Test
    //test assegnazione simboli
    void testValueOf() {
        assertEquals(Cell.FREE, Cell.valueOf("FREE"));
        assertEquals(Cell.WALL, Cell.valueOf("WALL"));
        assertEquals(Cell.PLAYER, Cell.valueOf("PLAYER"));
    }

    @Test
    //test lancio eccezione per simbolo illegale
    void testIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Cell.valueOf("INVALID"));
    }


    @Test
    //test getSymbol
    void testGetSymbol(){
        Cell cell = Cell.PLAYER;
        assertEquals('A', cell.getSymbol());
        cell = Cell.WALL;
        assertEquals('#', cell.getSymbol());
    }
}