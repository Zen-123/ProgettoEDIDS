package Board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class CellTest {

    @Test
    //test di tutti i simboli presenti nella classe Cell
    void testEnumValues() {
        assertEquals(16, Cell.values().length);
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