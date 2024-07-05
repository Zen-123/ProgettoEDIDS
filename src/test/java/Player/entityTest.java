package Player;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class entityTest {

    private entity testEntity;

    @Before
    //setting di oggetto Entity per evitare lancio di eccezioni
    public void setUp() {
        testEntity = new entity();
    }

    @Test
    //test di metodi setX e getX
    public void testGetAndSetX() {
        testEntity.setX(5);
        assertEquals(5, testEntity.getX());
    }

    @Test
    //test di metodi setY e getY
    public void testGetAndSetY() {
        testEntity.setY(10);
        assertEquals(10, testEntity.getY());
    }

    @Test
    //test di metodi setSymbol e getSymbol
    public void testGetAndSetSymbol() {
        testEntity.setSymbol('A');
        assertEquals('A', testEntity.getSymbol());
    }

    

    @Test
    //test per verificare inserimento di coordinate negative
    public void testNegativeCoordinates() {
        testEntity.setX(-5);
        testEntity.setY(-10);
        assertEquals(-5, testEntity.getX());
        assertEquals(-10, testEntity.getY());
    }


    @Test
    //test per verificare la correttezza di inserimenti multiplici con aggiornamento dei valori
    public void testMultipleSetOperations() {
        testEntity.setX(5);
        testEntity.setY(10);
        testEntity.setSymbol('B');

        assertEquals(5, testEntity.getX());
        assertEquals(10, testEntity.getY());
        assertEquals('B', testEntity.getSymbol());

        testEntity.setX(15);
        testEntity.setY(20);
        testEntity.setSymbol('C');

        assertEquals(15, testEntity.getX());
        assertEquals(20, testEntity.getY());
        assertEquals('C', testEntity.getSymbol());
    }
}