package Board;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Player.Player;

public class ReadfileTest {

    private Readfile readfile;

    @BeforeEach
    //creazione oggetto della classe Readfile
    public void setUp() {
        readfile = new Readfile();
    }

    @Test
    //controllo di metodo fileToRead per la lettura di un file temporaneo
    public void testFileToRead() throws IOException {
        // Creazione di un file temporaneo con dati temporanei per lo unit test del metodo
        File tempFile = File.createTempFile("testFileToRead", ".txt");
        FileWriter writer = new FileWriter(tempFile);
        writer.write("line1\nline2\nline3");
        writer.close();

        // chiamata al metodo da testare
        ArrayList<String> result = readfile.fileToRead(tempFile.getAbsolutePath());

        // controllo dei risultati ottenuti leggendo il file
        assertEquals(3, result.size());  //inserite 3 linee nel file, ci aspettiamo una size = 3
        //lettura delle linee di codice inserito
        assertEquals("line1", result.get(0));
        assertEquals("line2", result.get(1));
        assertEquals("line3", result.get(2));

        // cancellazione del file temporaneo
        tempFile.delete();
    }

    @Test
    //test del metodo fileToWrite
    public void testFileToWrite() throws IOException {
        //creazione file temporaneo
        File tempFile = new File("FileLoad/testFile.txt");
        try {


            // preparazione dei dati da inserire nel file
            ArrayList<ArrayList<Cell>> stanza = new ArrayList<>();
            ArrayList<Cell> row1 = new ArrayList<>();
            row1.add(Cell.FREE);
            row1.add(Cell.FREE);
            stanza.add(row1);

            ArrayList<Cell> row2 = new ArrayList<>();
            row2.add(Cell.FREE);
            row2.add(Cell.FREE);
            stanza.add(row2);

            // setting degli oggetti per evitare il lancio di eccezioni
            reference.filereader = new Readfile();
            reference.player = new Player();
            reference.currentStanza = new Board(1);
            reference.currentStanza.ss = new ArrayList<>();
            reference.currentStanza.ss.add("..");
            reference.currentStanza.ss.add("..");

            // metodo da testare
            readfile.fileToWrite(stanza, tempFile.getAbsolutePath());

            // verifica dei risultati ottenuti dopo scrittura su file
            BufferedReader reader = new BufferedReader(new FileReader(tempFile));
            String line1 = reader.readLine();
            String line2 = reader.readLine();

            reader.close();

            //test globale
            assertEquals("..", line1);
            //test singolo
            //assertEquals("..", line1);
            assertEquals("..", line2);
        } finally {
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
        }
    }
}
