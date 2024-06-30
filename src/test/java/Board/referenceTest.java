package Board;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import Player.Item;
import Player.Player;
import Player.mostro;
import UI.UI;
import java.util.ArrayList;

public class referenceTest {
    @BeforeEach
    //setting degli oggetti usati per la fase di test per evitare lancio di eccezioni
    public void setUp() {
        reference.player = new Player();
        reference.ui = new UI();
        reference.filereader = new Readfile();
        reference.item = new Item();
        reference.mostro = new mostro();
        reference.mostrorun = new mostro();
        reference.lista_stanze = new ArrayList<>();
        reference.curr_stanza = 1;
        reference.currentStanza = new Board(reference.curr_stanza);
        reference.alreadybeen = false;
        reference.startGame = false;
    }

    @Test
    //test per verificare se tutti gli oggetti sono stati inizializzati o meno durante fase di  @setUp
    public void testInitialValues() {
        assertNotNull(reference.player);
        assertNotNull(reference.ui);
        assertNotNull(reference.filereader);
        assertNotNull(reference.item );
        assertNotNull(reference.mostro);
        assertNotNull(reference.mostrorun);
        assertNotNull(reference.lista_stanze);
        assertNotNull(reference.currentStanza);
        assertEquals(1, reference.curr_stanza);
        assertFalse(reference.alreadybeen);
        assertFalse(reference.startGame);

    }

    @Test
    //test per verificare se inizializzazione/assegnazione viene eseguita correttamente
    public void testModifyValues() {
        reference.curr_stanza = 2;

        assertEquals(2, reference.curr_stanza);

    }
}
