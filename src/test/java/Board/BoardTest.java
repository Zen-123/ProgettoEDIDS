package Board;
import static org.junit.jupiter.api.Assertions.*;
import Player.Player;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import UI.UI;

/*
Classe test per la creazione della mappa
 */
public class BoardTest {

    //setting delle path dei file di test usati
    private static final String TEST_DIRECTORY = "src/main/java/Board/Stanze/";
    private static final String TEST_OLD_DIRECTORY = "src/main/java/Board/Stanzeold/";
    //file .txt usati per il testing
    private static final String TEST_FILE = TEST_DIRECTORY + "testStanza.txt";
    private static final String TEST_OLD_FILE = TEST_OLD_DIRECTORY + "testStanza.txt";

    @BeforeAll
    //setting degli oggetti usati per il testing dei metodi della classe
    public static void setUpAll() throws IOException {
        Files.createDirectories(Paths.get(TEST_DIRECTORY));
        Files.createDirectories(Paths.get(TEST_OLD_DIRECTORY));

        //creazione di una stanza di test
        List<String> content = Arrays.asList(
                "#######",
                "#.....#",
                "#.A...#",
                "#.....#",
                "#######",
                "0123"
        );

        //scrittura sui file di testing
        Files.write(Paths.get(TEST_FILE), content);
        Files.write(Paths.get(TEST_OLD_FILE), content);
    }


    @BeforeEach
    //creazione degli oggetti usati durante la fase di test
    public void setUp() {
        reference.filereader = new Readfile();
        reference.player = new Player();
        reference.ui = new UI();
        reference.lista_stanze = new ArrayList<>();
    }

    @Test
    //testing del metodo di costruzione della board con ID della stanza corrente
    public void testConstructorWithId() {
        Board board = new Board(1);
        assertEquals(1, board.getid(), "ID Stanza should be 1");
        assertEquals(5, board.getColumn(), "Column count should be 5");
        assertEquals(5, board.getRow(), "Row count should be 5");
        assertNotNull(board.cellestanza, "cellestanza should be initialized");
        assertFalse(reference.alreadybeen, "alreadybeen should be false"); //stanza non ancora visitata
    }

    @Test
    //testing del metodo di costruzione della board con ID della stanza corrente e valore booleano per stabilire se la stanza è già stata visitata
    public void testConstructorWithIdAndVerification() {
        Board board = new Board(1, true);
        assertEquals(1, board.getid(), "ID Stanza should be 1");
        assertEquals(5, board.getColumn(), "Column count should be 5");
        assertEquals(5, board.getRow(), "Row count should be 5");
        assertNotNull(board.cellestanza, "cellestanza should be initialized");
        assertTrue(reference.alreadybeen, "alreadybeen should be true");
    }

    @Test
    //testing del metodo PopulateBoard()
    public void testPopulateBoard() {
        List<String> content = Arrays.asList(
                "#######",
                "#.....#",
                "#.A...#",
                "#.....#",
                "#######",
                "0123"
        );

        Board board = new Board(1);

        board.populateBoard(new ArrayList<>(content), false);

        assertEquals(Cell.WALL, board.getCella(0, 0), "Cell at (0,0) should be WALL");
        assertEquals(Cell.FREE, board.getCella(1, 1), "Cell at (1,1) should be FREE");
        assertEquals(Cell.PLAYER, board.getCella(3, 3), "Cell at (3,3) should be PLAYER");
        assertEquals(Cell.WALL, board.getCella(4, 0), "Cell at (4,0) should be WALL");

        assertEquals(0, board.getDrive_to_N(), "Drive to N should be 0");
        assertEquals(1, board.getDrive_to_E(), "Drive to E should be 1");
        assertEquals(2, board.getDrive_to_W(), "Drive to W should be 2");
        assertEquals(3, board.getDrive_to_S(), "Drive to S should be 3");
    }

    @Test
    //testing dei metodi setDriveTo e getDriveTo
    public void testSetAndGetDriveTo() {
        Board board = new Board(1);

        board.setDrive_to_N(48);
        board.setDrive_to_E(49);
        board.setDrive_to_W(50);
        board.setDrive_to_S(51);

        assertEquals(0, board.getDrive_to_N(), "Drive to N should be 0");
        assertEquals(1, board.getDrive_to_E(), "Drive to E should be 1");
        assertEquals(2, board.getDrive_to_W(), "Drive to W should be 2");
        assertEquals(3, board.getDrive_to_S(), "Drive to S should be 3");
    }

    @Test
    //testing dei metodi setSymbol e getSymbol
    public void testSetAndGetSymbols() {
        Board board = new Board(1);

        board.setSsymbol(2, 2, 'X');
        assertEquals('X', board.getSsymbol(2, 2), "Symbol at (2,2) should be 'X'");
    }
}
